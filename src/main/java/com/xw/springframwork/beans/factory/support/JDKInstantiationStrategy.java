package com.xw.springframwork.beans.factory.support;

import com.xw.springframwork.beans.factory.BeansException;
import com.xw.springframwork.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JDKInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instante(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        //构造有参和无参函数
        try {
            if (null == args) {
                return clazz.getDeclaredConstructor().newInstance();
            } else {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }
        } catch (InvocationTargetException |NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new BeansException("Fail to instante "+clazz.getName(),e);
        }
    }
}
