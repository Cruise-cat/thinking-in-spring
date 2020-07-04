package sun.net.www.protocol.x;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 自定义 X Handler 测试
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/4
 */
public class HandlerTest {

    public static void main(String[] args) throws IOException {
        // 等价于 classpath:///META-INF/default.properties
        URL url = new URL("x:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("utf-8")));
    }
}
