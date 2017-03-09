package com.iiifi.framework.server.common.common.amq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;

/**
 * activemq 抽象消息生产者
 */
public abstract class AbstractProducer<T extends Object> {


    protected ActiveMQQueue destination;
    protected JmsTemplate jmsTemplate;

    protected abstract void send(T t);

    public ActiveMQQueue getDestination() {
        return destination;
    }

    public void setDestination(ActiveMQQueue destination) {
        this.destination = destination;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
