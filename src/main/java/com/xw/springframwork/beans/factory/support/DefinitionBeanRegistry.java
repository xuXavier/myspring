package com.xw.springframwork.beans.factory.support;

import com.xw.springframwork.beans.factory.config.BeanDefinition;

/*
    注册beanDefintion不应该由DefaultListableBeanFactory本身来定义，而是应该去实现一个注册接口
    这体现了类和接口的各司其职
 */
public interface DefinitionBeanRegistry {
    void registyBeanDefinition(String beanName, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String beanName);
}
