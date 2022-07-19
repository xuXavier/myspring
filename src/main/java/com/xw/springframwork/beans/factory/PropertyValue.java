package com.xw.springframwork.beans.factory;

/*
    用来描述属性和值
 */
public class PropertyValue {
    private String name;

    private Object value;

    public String getName() {
        return name;
    }
    public PropertyValue(){

    }

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }


    public Object getValue() {
        return value;
    }


}
