#### Run ActiveMQ local using docker
```shell
docker run --name alura-camel-activemq -p 61616:61616 -p 8161:8161 rmohr/activemq:5.13.0
```

#### Before steps:

1. After container up access http://localhost:8161
2. Login: admin and password: admin
3. Get in Queues menu and create net queue: fila.financeiro
4. Get in Topics menu and create new topic: topico.loja

#### ActiveMQ conceps:

- [x] Queued messages ensure delivery the message to one consumer, if have more then 1 consumer, then MOM send one message to one and the other message to another consumer, never the same message for both or more consumers;
- [x] The topics MOM, send the same messages for every subscription that still have activated consuming messages;
- [x] By default, AMQ try send 6x times to the Subscription, if failed, send to queue DLQ (Dead Letter Queue);
- [x] By default Queue messages only send messages to consumers actived.
- [x] By default Topic messages only send messages to Subscription activated, or you can create a durable signature and the MOM will wait Subscriber activate to send message;
- [x] You can configure AMQ for organize tasks in queue based on order priority [0-9] 0 low and 9 high needs;

#### Flow JMS Protocols in ActiveMQ

![](imagens/jms-comp.png)

#### Examples:

##### Queues Consumers

1. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/QueueConsumer.java
   - Simple queue consumer
2. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/QueueConsumerDLQ.java
   - DLQ queue consumer
3. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/QueueConsumerTransaction.java
   - Queue consumer with transaction

##### Queues Producers

1. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/QueueProducer.java
   - Simple queue producer
2. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/QueueProducerWithPriority.java
   - Queue producer with priority

##### Topics

1. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/TopicProducer.java
   - Simple topic publisher
2. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/TopicProducerPublishObj.java
   - Publisher topic with obj serializable
3. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/TopicProducerSelector.java
   - Topic publisher with selector

##### Subscriptions

1. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/TopicConsumer.java
   - Simple topic subscription;
2. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/TopicConsumerObj.java
   - Simple topic subscription with obj
3. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/TopicConsumerStockSelector.java
   - Topic subscription filtering with selector
