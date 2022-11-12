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

#### Flow JMS Protocols in ActiveMQ

![](imagens/jms-comp.png)

#### Examples:

1. Consume Simple Queues
   1. https://github.com/itzmatheus/alura-course-java-activemq/blob/main/src/main/java/QueueConsumer.java