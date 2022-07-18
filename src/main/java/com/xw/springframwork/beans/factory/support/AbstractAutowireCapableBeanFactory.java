package com.xw.springframwork.beans.factory.support;

import com.xw.springframwork.beans.factory.BeanFactory;
import com.xw.springframwork.beans.factory.BeansException;
import com.xw.springframwork.beans.factory.config.BeanDefinition;


public abstract  class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
        private Object bean;

    @Override
    Object creatSingletonBean(String beanName) {
        return createBean(beanName);
    }

    /*
            定义一个获取Class对象的抽象方法
         */
    abstract BeanDefinition getBeanDefition(String beaName);

    public Object createBean(String beanName) throws BeansException {
            BeanDefinition beanDefinition= getBeanDefition(beanName);
        Object beanObject= null;
        try {
            beanObject = beanDefinition.getBean().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("创建Bean失败",e);
        }
        return beanObject;
        }

    public abstract void registyBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
