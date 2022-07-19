package com.xw.springframwork.core.io;

import cn.hutool.core.util.ClassUtil;

import java.io.*;

public class ClassPathReource implements  Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathReource(String path){
        this.path=path;
        this.classLoader=ClassUtil.getClassLoader();
    }

    //通过构造函数出入path和classLoader
    public ClassPathReource(String path,ClassLoader classLoader){
        this.path=path;
        this.classLoader=classLoader!=null?classLoader:ClassUtil.getClassLoader();
    }


    @Override
    public InputStream getInputStream() throws IOException {
        //通过classLoader获取当前目录下的输出流
        InputStream inputStream=classLoader.getResourceAsStream(path);
        if(null==inputStream){
            throw new FileNotFoundException(this.path+"cannot open because it dost not exit");
        }
        return inputStream;
    }
}
