package com.iiifi.framework.server.common.common.amq;


/**
 * 消费者抽象类
 */
public abstract class AbstractConsumer<T extends Object> {

    protected abstract void receive(T t);
}
