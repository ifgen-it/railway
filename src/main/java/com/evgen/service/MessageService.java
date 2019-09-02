package com.evgen.service;

import javax.jms.JMSException;

public interface MessageService {

    void sendMessage(String stationName) throws JMSException;
}
