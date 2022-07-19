package com.xw.springframwork.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
    基本的文件流获取输入
 */
public class FileSystemResource implements Resource{
    private final File flie;

    private final String path;

    public FileSystemResource(String path){
        this.flie=new File(path);
        this.path=path;
    }

    public FileSystemResource(File file){
        this.path=file.getPath();
        this.flie=file;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.flie);
    }

    public final String getPath(){
        return this.path;
    }
}
