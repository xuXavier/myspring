package com.xw.springframwork.beans.factory.support;

import com.xw.springframwork.beans.factory.BeansException;
import com.xw.springframwork.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap=new HashMap<>();
    @Override
    BeanDefinition getBeanDefition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }


    @Override
    public void registyBeanDefinition(String beanName,BeanDefinition beanDefinition) {
        try{
            beanDefinitionMap.put(beanName,beanDefinition);
        }catch (Exception e){
            throw new BeansException("beanDefintion注册失败");
        }
    }

}
