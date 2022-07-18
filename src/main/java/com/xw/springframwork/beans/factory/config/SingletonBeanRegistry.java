package com.xw.springframwork.beans.factory.config;

/*
    注册单例的接口
 */
public interface SingletonBeanRegistry {
    void registrySingletonBean(String beanName,BeanDefinition beanDefinition);
}
