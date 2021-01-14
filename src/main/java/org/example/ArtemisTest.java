package org.example;

import org.apache.activemq.artemis.jms.client.ActiveMQBytesMessage;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.CountDownLatch;

public class ArtemisTest {

    public static void main(String[] args) throws Exception{
        byte[] bytes = new byte[200000];
        for (int i=0; i<bytes.length; i++){
            bytes[i] = 'z';
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String url = "tcp://169.254.200.228:61616";
        String cred = "admin";
       // ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url,cred,cred);
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("test");
        connection.start();
        Session session = connection.createSession(false,1);
        Destination destination = session.createQueue("Organization.Services.App3.test.service");
        Topic topic = session.createTopic("dur");
        MessageProducer messageProducer = session.createProducer(destination);
        BytesMessage message = session.createBytesMessage();
        message.writeBytes(bytes);
       // messageProducer.send(message);
        session.createConsumer(destination).setMessageListener(message1 -> {
            System.out.println(message1);
        });


        countDownLatch.await();

    }
}
