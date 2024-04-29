package com.yzhhh.voice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.media.sound.WaveFileReader;
import com.sun.media.sound.WaveFileWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vosk.LogLevel;
import org.vosk.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.vosk.LibVosk;
import org.vosk.Recognizer;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
@Slf4j
@Service
public class VoiceUtil {

    @Value("${leenleda.vosk.model}")
    private String VOSKMODELPATH;

    public String getWord(String filePath) throws IOException, UnsupportedAudioFileException {
        Assert.isTrue(StringUtils.hasLength(VOSKMODELPATH), "无效的VOS模块！");
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        // 转换为16KHZ
        reSamplingAndSave(bytes, filePath);
        File f = new File(filePath);
        RandomAccessFile rdf = null;
        rdf = new RandomAccessFile(f, "r");
        log.info("声音尺寸:{}", toInt(read(rdf, 4, 4)));
        log.info("音频格式:{}", toShort(read(rdf, 20, 2)));
        short track=toShort(read(rdf, 22, 2));
        log.info("1 单声道 2 双声道: {}", track);
        log.info("采样率、音频采样级别 16000 = 16KHz: {}", toInt(read(rdf, 24, 4)));
        log.info("每秒波形的数据量：{}", toShort(read(rdf, 22, 2)));
        log.info("采样帧的大小：{}", toShort(read(rdf, 32, 2)));
        log.info("采样位数：{}", toShort(read(rdf, 34, 2)));
        rdf.close();
        LibVosk.setLogLevel(LogLevel.WARNINGS);

        try (
            Model model = new Model(VOSKMODELPATH);
             InputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(filePath)));
             // 采样率为音频采样率的声道倍数
             Recognizer recognizer = new Recognizer(model, 16000*track)) {

            int nbytes;
            byte[] b = new byte[4096];
            int i = 0;
            while ((nbytes = ais.read(b)) >= 0) {
                i += 1;
                if (recognizer.acceptWaveForm(b, nbytes)) {
//                    System.out.println(recognizer.getResult());
                } else {
//                    System.out.println(recognizer.getPartialResult());
                }
            }
            String result = recognizer.getFinalResult();
            log.info("识别结果：{}", result);
            if (StringUtils.hasLength(result)) {
                JSONObject jsonObject = JSON.parseObject(result);
                return jsonObject.getString("text").replace(" ", "");
            }
            return "";
        }
    }

    public static int toInt(byte[] b) {
        return (((b[3] & 0xff) << 24) + ((b[2] & 0xff) << 16) + ((b[1] & 0xff) << 8) + ((b[0] & 0xff) << 0));
    }

    public static short toShort(byte[] b) {
        return (short) ((b[1] << 8) + (b[0] << 0));
    }


    public static byte[] read(RandomAccessFile rdf, int pos, int length) throws IOException {
        rdf.seek(pos);
        byte result[] = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = rdf.readByte();
        }
        return result;
    }

    public static void reSamplingAndSave(byte[] data, String path) throws IOException, UnsupportedAudioFileException {
        WaveFileReader reader = new WaveFileReader();
        AudioInputStream audioIn = reader.getAudioInputStream(new ByteArrayInputStream(data));

        AudioFormat srcFormat = audioIn.getFormat();
        int targetSampleRate = 16000;

        AudioFormat dstFormat = new AudioFormat(srcFormat.getEncoding(),
                targetSampleRate,
                srcFormat.getSampleSizeInBits(),
                srcFormat.getChannels(),
                srcFormat.getFrameSize(),
                srcFormat.getFrameRate(),
                srcFormat.isBigEndian());

        AudioInputStream convertedIn = AudioSystem.getAudioInputStream(dstFormat, audioIn);
        File file = new File(path);
        WaveFileWriter writer = new WaveFileWriter();
        writer.write(convertedIn, AudioFileFormat.Type.WAVE, file);
    }
}
