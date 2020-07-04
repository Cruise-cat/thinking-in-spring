package com.cruise.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 基于字符编码的 {@link FileSystemResource} 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/3
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir").concat("\\resource\\src\\main\\java\\com\\cruise\\thinking\\in\\spring\\resource\\EncodedFileSystemResourceDemo.java");
        File file = new File(path);

        FileSystemResource fileSystemResource = new FileSystemResource(file);

        EncodedResource encodedResource = new EncodedResource(fileSystemResource);
        try(Reader reader = encodedResource.getReader()){
            System.out.println(IOUtils.toString(reader));
        }

    }
}
