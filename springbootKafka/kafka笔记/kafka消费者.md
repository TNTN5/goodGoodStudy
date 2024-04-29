# 消费者

消费者组内消费者 group id 相同

消费者组内每一个消费者负责消费不同分区的数据，一个分区只能由一个组内消费者消费

消费者组之间互不影响，消费者组是逻辑上的一个订阅者



## 初始化流程

1、每个consumer都发送JoinGroup请求

2、选出一个consumer做为leader

3、把要消费的topic情况发送给leader消费者

4、消费者leader把消费方案发给coordinator

5、coordinator把消费发难发给各个consumer

6、每个消费者都会和coordinator保持心跳（默认3s），一旦超时，该消费者被移除，并触发再平衡；或者消费者处理消息事件过长（max.poll.interval.ms  5分钟）也会出发再平衡。

首先创建ConsumerNetworkClient 消费者网络连接客户端

​	Fetch.min.bytes 每批次最小抓取大小，默认1字节

​	Fetch.max.wait.ms 一批数据最小值未达到的超时时间，默认500ms

​	Fetch.max.bytes 每批次最大抓取大小，默认50m

​	Max.poll.records 一次拉取数据返回消息的最大条数，默认500条

​	partition.assignment.strategy 消费者分区分配策略（有Range、RoundRobin、Sticky、CooperativeSticky）,默认Range+CooperativeSticky

### 消费一个主题

```javascript
//		0、配置
        Properties properties=new Properties();
//      连接
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"ip:9092,ip:9092");
//      反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
//      1、创建一个消费者，"","hello"
        KafkaConsumer<String,String> kafkaConsumer=new KafkaConsumer<String, String>(properties);
//      2、订阅主题
        ArrayList<String> topics =new ArrayList<String>();
        topics.add("topicName");
        kafkaConsumer.subscribe(topics);
//      3、消费数据
        while (true){
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));

            consumerRecords.forEach(consumerRecord->{
                System.out.println(consumerRecord);
            });
        }
```

### 消费一个分区

```javascript
//      0、配置
        Properties properties=new Properties();
//      连接
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"ip:9092,ip:9092");
//      反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
//      消费者组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test");

//      1、创建一个消费者，"","hello"
        KafkaConsumer<String,String> kafkaConsumer=new KafkaConsumer<String, String>(properties);
//      2、订阅主题对应分区
        ArrayList<TopicPartition> topicPartitions =new ArrayList<>();
        topicPartitions.add(new TopicPartition("topicName",0));
        kafkaConsumer.assign(topicPartitions);
//      3、消费数据
        while (true){
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));

            consumerRecords.forEach(consumerRecord->{
                System.out.println(consumerRecord);
            });
        }
```

