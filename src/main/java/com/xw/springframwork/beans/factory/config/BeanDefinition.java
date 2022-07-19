package com.xw.springframwork.beans.factory.config;

import com.xw.springframwork.beans.factory.BeanFactory;
import com.xw.springframwork.beans.factory.PropertyValues;

import java.util.HashMap;
import java.util.Map;

//Bean的定义
public class BeanDefinition {
    private Class beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(){}

    public BeanDefinition(Class beanClass,PropertyValues propertyValues){
        this.beanClass=beanClass;
        this.propertyValues  = propertyValues;
    }
    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
