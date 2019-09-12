package com.evgen.service.impl;

import com.evgen.service.MessageService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

    @Override
    public void sendMessage(String stationName) throws JMSException {

        logger.info("In the JMS TOPIC Sender");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        TopicConnection connection = connectionFactory.createTopicConnection();
        connection.start();

        TopicSession session = connection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic1");
        TopicPublisher publisher = session.createPublisher(topic);

        String textMessage = "UPDATING TIMETABLE FOR CITY : " + stationName;
        TextMessage message = session.createTextMessage(textMessage);
        message.setStringProperty("city", stationName);

        publisher.publish(message);
        logger.info("Message published into Topic: " + textMessage);

        publisher.close();
        session.close();
        connection.close();
        logger.info("Resources closed");

    }
}
