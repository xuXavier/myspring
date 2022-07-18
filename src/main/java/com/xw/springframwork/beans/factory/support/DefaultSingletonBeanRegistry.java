package com.xw.springframwork.beans.factory.support;

import com.xw.springframwork.beans.factory.BeansException;
import com.xw.springframwork.beans.factory.config.BeanDefinition;
import com.xw.springframwork.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public abstract class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String,Object> singletonBeanMap=new HashMap<>();

    public Object getSingletonBean(String singletonBeanName){
       return singletonBeanMap.get(singletonBeanName);
    }
    @Override
    public void registrySingletonBean(String beanName, BeanDefinition beanDefinition) {
        try{
            //注册单例bean对象，这样每个名字对应一个单例bean
            singletonBeanMap.put(beanName,creatSingletonBean(beanName));
        }catch (Exception e){
            throw new BeansException("注册bean失败",e);
        }
    }
    //抽象方法，具体实现应该是AbstractAutowireCapableBeanFactory实现
     abstract Object creatSingletonBean(String beanName);
}
