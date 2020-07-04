package com.cruise.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import java.io.IOException;
import java.io.Reader;

/**
 * 基于字符编码的 {@link FileSystemResourceLoader} 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/3
 * @see FileSystemResourceLoader
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceLoaderDemo {

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir").concat("\\resource\\src\\main\\java\\com\\cruise\\thinking\\in\\spring\\resource\\EncodedFileSystemResourceDemo.java");

        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(path);

        EncodedResource encodedResource = new EncodedResource(resource);
        try(Reader reader = encodedResource.getReader()){
            System.out.println(IOUtils.toString(reader));
        }

    }
}
