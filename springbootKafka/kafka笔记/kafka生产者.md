# kafka生产者

0、jar包

```xml
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>3.0.0</version>
</dependency>
```

1、异步发送（无回调）

```java
//        0.配置
        Properties properties=new Properties();
//        链接集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"ip:port,ip:port");
//        配置key-value序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
//        1.创建kafka生产者对象
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<String, String>(properties);
//        2.发送数据	topic,value
        kafkaProducer.send(new ProducerRecord<String, String>("",""));
//        3.关闭资源
        kafkaProducer.close();
```

2、异步发送（回调）

```java
kafkaProducer.send(new ProducerRecord<String, String>("", ""), new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e==null){
                    System.out.println("主题："+recordMetadata.topic()+"分区："+recordMetadata.partition());
                }
            }
        });
```

3、同步发送

```java
kafkaProducer.send(new ProducerRecord<String, String>("","")).get();
```

4、分区

​	指定分区：

```java
//topic,partition,key,value
kafkaProducer.send(new ProducerRecord<String, String>("",1,"",""));
```

​	按key的hashcode分区

```java
//topic,key,value
kafkaProducer.send(new ProducerRecord<String, String>("","abc",""));
```

​	自定义分区(应用：过滤数据，如脏数据等)

```java
//继承Partitioner接口，重写其方法
public class MyPartition implements Partitioner {}
```

```java
//在实例化生产者之前配置中关联自定义分区策略
properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.zz.kafka.producer.MyPartitioner");
```

5、提高吞吐量

<img src="C:\Users\Apollo\AppData\Roaming\Typora\typora-user-images\image-20220807090459993.png" alt="image-20220807090459993" style="zoom: 50%;" />

```java
//  0、配置
        Properties properties=new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"ip:port,ip:port");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
//      设置缓冲区大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
//        批次大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
//        linger.ms
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
//        压缩
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");
//  1、创建生产者
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<String, String>(properties);
//  2、发送数据
        kafkaProducer.send(new ProducerRecord<String, String>("","helloworld"));
//  3、关闭资源
        kafkaProducer.close();
```

