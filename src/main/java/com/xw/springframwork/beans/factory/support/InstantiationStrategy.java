package com.xw.springframwork.beans.factory.support;

import com.xw.springframwork.beans.factory.BeansException;
import com.xw.springframwork.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/*
    实例化策略接口
 */
public interface InstantiationStrategy {
    //实例化方法，需要BeanDefinition，也就是Class对象，beanName,Constructor(必要的类信息），args，具体入参信息
    Object instante(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args) throws BeansException;
}
