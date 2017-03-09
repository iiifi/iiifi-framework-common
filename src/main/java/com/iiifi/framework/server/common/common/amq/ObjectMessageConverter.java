package com.iiifi.framework.server.common.common.amq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class ObjectMessageConverter implements MessageConverter {

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
    	long waitTime=0;
        try {
        	Field field = object.getClass().getDeclaredField("waitTime");
        	field.setAccessible(true);
			waitTime = (long) field.get(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        ObjectMessage objectMessage = session.createObjectMessage();
        if(waitTime>0){
        	objectMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, waitTime);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            byte[] objMessage = bos.toByteArray();// 字节数组输出流转成字节数组
            objectMessage.setObject(objMessage);// 将字节数组填充到消息中作为消息主体
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectMessage;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        Object object = null;
        if (message instanceof ObjectMessage) {
            // 两次强转，获得消息中的主体对象字节数组流
            byte[] obj = (byte[]) ((ObjectMessage) message).getObject();
            // 读取字节数组中为字节数组流
            ByteArrayInputStream bis = new ByteArrayInputStream(obj);
            try {
                // 读字节数组流为对象输出流
                ObjectInputStream ois = new ObjectInputStream(bis);
                // 从对象输出流中取出对象 并强转
                object = ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return object;
    }

}
