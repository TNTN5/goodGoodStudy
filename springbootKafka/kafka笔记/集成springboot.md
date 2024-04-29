# 集成springboot

pom.xml

```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

application.properties

```properties
# 连接kafka集群
spring.kafka.bootstrap-servers=hadoop102:9092,hadoop103:9092

#key 和value 序列化
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

#key 和value 反序列化
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer

#消费者组
spring.kafka.consumer.group-id=zz
```

ProducerController

```java
 	@Autowired
    private KafkaTemplate<String,String> kafka;

    @RequestMapping("/sendData")
    public String data(String msg){
        kafka.send("",msg);
        return "ok";
    }
```

Consumer

```java
@Configuration
public class Consumer {
    @KafkaListener(topics = "tipicName")
    public void consumerTopic(String msg){
        System.out.println(msg);
    }
}
```

