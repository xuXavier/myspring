package com.xw.springframwork.beans.factory.support;

import com.xw.springframwork.beans.factory.BeansException;
import com.xw.springframwork.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

public class CglibInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instante(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Enhancer enhancer =new Enhancer();
        //设置被代理对象的Class对象
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        //设置回调函数
        enhancer.setCallback((new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        }));
        if(null==ctor)return enhancer.create();
        return enhancer.create(ctor.getParameterTypes(),args);
    }
}
