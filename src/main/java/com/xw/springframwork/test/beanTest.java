package com.xw.springframwork.test;

import com.xw.springframwork.beans.factory.BeanFactory;
import com.xw.springframwork.beans.factory.PropertyValue;
import com.xw.springframwork.beans.factory.PropertyValues;
import com.xw.springframwork.beans.factory.config.BeanDefinition;
import com.xw.springframwork.beans.factory.config.BeanReference;
import com.xw.springframwork.beans.factory.support.DefaultListableBeanFactory;
import com.xw.springframwork.beans.factory.xml.XmlBeanDefinitionReader;
import com.xw.springframwork.core.io.DefaultResourceLoader;
import org.junit.Test;

public class beanTest {
    @Test
    public void beanTest() {
        //初始化BeanFactory
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //往容器注入bean
        BeanDefinition beanDefinition=new BeanDefinition(UserDao.class);
        beanFactory.registyBeanDefinition("userDao",beanDefinition);

        //Userservice注入属性
        PropertyValues propertyValues=new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        //注册Userservice bean
        BeanDefinition beanDefinition1=new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registyBeanDefinition("userService",beanDefinition1);

        UserService userService= (UserService) beanFactory.getBean("userService");
        userService.query("001");
//        //注册单例bean
//        //首先往容器里注册beanDefinition对象，也就是bean的class对象
//        beanFactory.registyBeanDefinition("UserSingletonDao",beanDefinition);
//        //再往容器里注入bean的单例对象，这是保存在另外一个bean容器里面
//        beanFactory.registrySingletonBean("UserSingletonDao",beanDefinition);

    }
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory,new DefaultResourceLoader());
        reader.loadBeanDefinitionResource("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        userService.query("001");

    }
}
