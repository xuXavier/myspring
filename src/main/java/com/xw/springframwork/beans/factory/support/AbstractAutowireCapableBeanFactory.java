package com.xw.springframwork.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.xw.springframwork.beans.factory.BeanFactory;
import com.xw.springframwork.beans.factory.BeansException;
import com.xw.springframwork.beans.factory.PropertyValue;
import com.xw.springframwork.beans.factory.PropertyValues;
import com.xw.springframwork.beans.factory.config.BeanDefinition;
import com.xw.springframwork.beans.factory.config.BeanReference;

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
        Object beanObject=null;
        try {
            beanObject  = instantiationStrategy.instante(beanDefinition, beanName, curConstructor, args);
            applyPropertyValues(beanName, beanObject, beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instante of bean fail",e);
        }
        return beanObject;
    }


    //往bean里注入属性
    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition) {
        try {
            //暂时只能从beanDefintion中获取属性和值，所以要在bean对象中直接赋值
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            //遍历所有的属性和值
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                //只要值是引用类型，就可以
                if (value instanceof BeanReference) {
                    //获取所有的依赖对象
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                //通过hutool的工具包BeanUtil来给bean设置属性值
                BeanUtil.setProperty(bean, name, value);
            }

        }catch (Exception e){
            throw new BeansException("Fail to set property"+e);
        }
    }
}
