package com.cruise.thinking.in.spring.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * {@link Resource} 工具类
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/3
 */
public interface ResourceUtils {

    static String getContent(Resource resource){
        try {
            return getContent(resource,"UTF-8");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    static String getContent(Resource resource,String encoding) throws IOException {
        EncodedResource encodedResource = new EncodedResource(resource, encoding);
        try(Reader reader = encodedResource.getReader()){
            return IOUtils.toString(reader);
        }
    }

}
