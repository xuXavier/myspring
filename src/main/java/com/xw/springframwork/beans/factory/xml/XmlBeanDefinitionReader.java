package com.xw.springframwork.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.sun.javaws.jnl.XMLUtils;
import com.xw.springframwork.beans.factory.BeansException;
import com.xw.springframwork.beans.factory.PropertyValue;
import com.xw.springframwork.beans.factory.config.BeanDefinition;
import com.xw.springframwork.beans.factory.config.BeanReference;
import com.xw.springframwork.beans.factory.support.AbstractBeanDefinitionReader;
import com.xw.springframwork.beans.factory.support.DefinitionBeanRegistry;
import com.xw.springframwork.core.io.Resource;
import com.xw.springframwork.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(DefinitionBeanRegistry definitionBeanRegistry, ResourceLoader resourceLoader) {
        super(definitionBeanRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitionResource(Resource resource) throws BeansException {
        try {
            InputStream inputStream=resource.getInputStream();
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitionResource(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitionResource( resource);
    }

    //获取inputstream后，拿到xml的相关节点信息
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        //通过hutool的xml包拿到xml文件
        Document doc= XmlUtil.readXML(inputStream);
        //先拿到root根节点
        Element root=doc.getDocumentElement();
        //拿到所有的node节点
        NodeList childNodes = root.getChildNodes();

        for(int i=0;i<childNodes.getLength();i++){
            //判断元素
            if(!(childNodes.item(i) instanceof Element)) continue;
            //判断对象
            if("beans".equals(childNodes.item(i).getNodeName())) continue;

            //解析标签
            Element bean= (Element) childNodes.item(i);
            String id=bean.getAttribute("id");
            String name=bean.getAttribute("name");
            String className=bean.getAttribute("class");
            //获取Class对象
            Class<?> clazz=Class.forName(className);
            //优先级id>name
            String beanName= StrUtil.isNotEmpty(id)?id:name;
            //id和name都没有则获取Class的simpleName，其实可以没有
            if(StrUtil.isEmpty(beanName)){
                beanName=StrUtil.lowerFirst(clazz.getSimpleName());
            }
            //定义Bean
            BeanDefinition beanDefinition=new BeanDefinition(clazz);
            //读取属性并填充
            for(int j=0;j<bean.getChildNodes().getLength();j++){
                if(!(bean.getChildNodes().item(j) instanceof Element))continue;
                if(!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                //解析标签：property
                //getChildNodes获取的是全部子节点,在 xml 文档节点前存在 回车,制表符等 都会占用一个节点Node( 如#text )，这一类Node是无法转为Element，
                // 会抛com.sun.org.apache.xerces.internal.dom.DeferredTextImpl cannot be cast to org.w3c.dom.Element。

                Element property = (Element) bean.getChildNodes().item(j);
                String attName=property.getAttribute("name");
                String attValue=property.getAttribute("value");
                String attRef=property.getAttribute("ref");
                //获取属性值：引入对象、值对象
                Object value=StrUtil.isNotEmpty(attRef)?new BeanReference(attRef):attValue;
                //创建属性信息
                PropertyValue propertyValue=new PropertyValue(attName,value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            getRegistry().registyBeanDefinition(beanName,beanDefinition);
        }

    }
}
