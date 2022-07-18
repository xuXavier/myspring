package com.xw.springframwork.beans.factory.support;

import com.xw.springframwork.beans.factory.BeanFactory;
import com.xw.springframwork.beans.factory.BeansException;
import com.xw.springframwork.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;


public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private Object bean;

    private InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategy();

    @Override
        //创建单例bean对象
    Object creatSingletonBean(String beanName) {
        return createBean(beanName, null);
    }

    /*
            定义一个获取Class对象的抽象方法
         */
    abstract BeanDefinition getBeanDefition(String beaName);

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    //通过cglib的方式来创建Bean对象
    public Object createBean(String beanName, Object[] args) throws BeansException {
        BeanDefinition beanDefinition = getBeanDefition(beanName);
        Constructor curConstructor=null;
        //获取当前Class对象的所有构造函数
        Constructor<?>[] constructors=beanDefinition.getBeanClass().getDeclaredConstructors();
        for(Constructor ctor:constructors){
            //判断如果构造函数里的某个构造函数长度和传入参数长度相等，就是匹配的构造函数（理论上应该比较参数)
            if(null!=args&&args.length==ctor.getParameterTypes().length) {
                curConstructor = ctor;
                break;
            }
        }
        Object beanObject = instantiationStrategy.instante(beanDefinition, beanName, curConstructor, args);
        return beanObject;
    }

    public abstract void registyBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
