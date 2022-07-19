package com.xw.springframwork.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource implements Resource {
    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        //获取Url的connection连接
        URLConnection urlConnection = this.url.openConnection();
        return urlConnection.getInputStream();
    }
}
