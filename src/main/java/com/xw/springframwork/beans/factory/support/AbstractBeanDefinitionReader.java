package com.xw.springframwork.beans.factory.support;

import com.xw.springframwork.core.io.ResourceLoader;

/*
    定义bean的抽象读取类
 */
public abstract  class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final DefinitionBeanRegistry registry;

    private final ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(DefinitionBeanRegistry definitionBeanRegistry,ResourceLoader resourceLoader){
        this.registry=definitionBeanRegistry;
        this.resourceLoader=resourceLoader;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    @Override
    public DefinitionBeanRegistry getRegistry() {
        return registry;
    }

}
