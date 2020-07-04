package com.cruise.thinking.in.spring.resource.springx;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 自定义 springx 协议
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/4
 */
public class Handler extends sun.net.www.protocol.x.Handler {

    /**
     * -Djava.protocol.handler.pkgs=com.cruise.thinking.in.spring.resource
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        URL url = new URL("springx:///META-INF/production.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("utf-8")));
    }
}
