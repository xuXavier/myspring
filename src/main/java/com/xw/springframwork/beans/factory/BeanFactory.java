package com.xw.springframwork.beans.factory;

import com.xw.springframwork.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/*
    BeanFactory应该是一个接口，由它提供getBean的抽象方法
 */
public interface BeanFactory {
    public Object getBean(String beanName) throws InstantiationException, IllegalAccessException;
   // public void registerBeanDefinition(String beanName,BeanDefinition beanDefinition);
}
