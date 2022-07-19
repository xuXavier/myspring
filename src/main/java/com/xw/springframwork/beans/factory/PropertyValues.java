package com.xw.springframwork.beans.factory;

import java.util.ArrayList;
import java.util.List;

/*
    用来保存所有的属性和值
 */
public class PropertyValues {
    private List<PropertyValue> propertyValueList=new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }

    //获取所有的propertyValue，即属性和值
    public PropertyValue[] getPropertyValues(){
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    //通过propertyvalue的属性名获取属性名和值
    public PropertyValue getProertyValue(String propertyName){
        for(PropertyValue pv:propertyValueList){
            if(propertyName.equals(pv.getName())){
                return pv;
            }
        }
        return null;
    }
}
