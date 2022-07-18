package com.xw.springframwork.test;

import com.xw.springframwork.beans.factory.BeanFactory;
import com.xw.springframwork.beans.factory.config.BeanDefinition;
import com.xw.springframwork.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class beanTest {
    @Test
    public void beanTest() {
        //初始化BeanFactory
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //往容器注入bean
        BeanDefinition beanDefinition=new BeanDefinition(UserDao.class);
        beanFactory.registyBeanDefinition("UserDao",beanDefinition);

        UserDao userDao= (UserDao) beanFactory.getBean("UserDao");
        userDao.query();
        //注册单例bean
        //首先往容器里注册beanDefinition对象，也就是bean的class对象
        beanFactory.registyBeanDefinition("UserSingletonDao",beanDefinition);
        //再往容器里注入bean的单例对象，这是保存在另外一个bean容器里面
        beanFactory.registrySingletonBean("UserSingletonDao",beanDefinition);
        //从容器里获取相关对象，判断是否是同一个对象
        UserDao userDaoSingleton=(UserDao) beanFactory.getSingletonBean("UserDao");
        UserDao userDaoSingleton1=(UserDao) beanFactory.getSingletonBean("UserDao");
        System.out.println(userDaoSingleton1==userDaoSingleton);
    }
}
