package com.xw.springframwork.beans.factory.config;

import com.xw.springframwork.beans.factory.BeanFactory;

import java.util.HashMap;
import java.util.Map;

//Bean的定义
public class BeanDefinition {
    private Class beanClass;

    public Class getBean() {
        return beanClass;
    }

    public BeanDefinition(Class bean){
        this.beanClass=bean;
    }
}
