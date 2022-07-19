package com.xw.springframwork.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader{

    @Override
    public Resource getResource(String location) {
        //首先判断是不是classpath开头的
        if(location.startsWith(CLASS_PATH)){
            return new ClassPathReource(location.substring(CLASS_PATH.length()));
        }else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
