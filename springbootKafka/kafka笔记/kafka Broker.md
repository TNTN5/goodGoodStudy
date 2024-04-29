# kafka Broker

1、工作流程

​	（1）broker启动后在zk中注册（brokers/ids）

​	（2）controller谁先注册谁说了算，由选举出来的controller监听brokers节点变化并决定broker选举（在isr[]中存活为前提，按照在ar[]中排在前面的优先

​	（3）controller将节点信息上传到zk

​	（4）其他broker的controller从zk同步相关信息

​	（5）假如leader挂了，controller监听到变化，获取isr[],重新选举新的leader

​	（6）更新新的leader和isr

