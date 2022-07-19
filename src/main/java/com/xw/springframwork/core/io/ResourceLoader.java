package com.xw.springframwork.core.io;

public interface ResourceLoader {
    String CLASS_PATH="classpath:";

    Resource getResource(String location);
}
