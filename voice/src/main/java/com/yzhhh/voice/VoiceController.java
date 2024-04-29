package com.yzhhh.voice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class VoiceController {


    @Autowired
    VoiceUtil voiceUtil;

    @PostMapping("/admin/getWord")
    public String getWord(MultipartFile file) {
        String path = "D:\\Data\\IdeaProjects\\voice\\src\\main\\resources\\voices\\" + new Date().getTime() + ".wav";
        File localFile = new File(path);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
            System.out.println(file.getOriginalFilename() + " 上传成功");
            // 上传成功，开始解析
            String text = voiceUtil.getWord(path);
            localFile.delete();
            return text;
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
            localFile.delete();
            return "上传失败";
        }
    }
}
