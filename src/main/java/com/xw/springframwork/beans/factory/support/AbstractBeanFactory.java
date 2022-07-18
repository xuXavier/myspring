package com.xw.springframwork.beans.factory.support;

import com.xw.springframwork.beans.factory.BeanFactory;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    /*
        当需要bean的时候，就去创建bean对象
     */
    @Override
    public Object getBean(String beanName)  {
        //创建bean时先判断是不是单例bean
        Object singletonBean=getSingletonBean(beanName);
        if(null!=singletonBean){
            return singletonBean;
        }
        //不是单例bean就创建多例
        Object cbean = createBean(beanName,null);
        return cbean;
    }

    @Override
    public Object getBean(String beanName, Object[] args) {
        //创建bean时先判断是不是单例bean
        Object singletonBean=getSingletonBean(beanName);
        if(null!=singletonBean){
            return singletonBean;
        }
        //不是单例bean就创建多例
        Object cbean = createBean(beanName,args);
        return cbean;
    }

    abstract Object createBean(String beanName,Object[] args);
}
