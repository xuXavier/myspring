package com.xw.springframwork.beans.factory.support;

import com.xw.springframwork.beans.factory.BeansException;
import com.xw.springframwork.core.io.Resource;
import com.xw.springframwork.core.io.ResourceLoader;

//从xml中读取beanDefintion
public interface BeanDefinitionReader {
    DefinitionBeanRegistry getRegistry();

    ResourceLoader getResourceLoader();


    void loadBeanDefinitionResource(Resource resource) throws BeansException;

    void loadBeanDefinitionResource(String location) throws BeansException;

}
