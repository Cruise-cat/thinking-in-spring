package sun.net.www.protocol.x;

import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 自定义 X 协议的 {@link URLConnection} 实现
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/4
 */
public class XURLConnection extends URLConnection{

    private final ClassPathResource classPathResource;

    public XURLConnection(URL url) {
        super(url);
        this.classPathResource = new ClassPathResource(url.getPath());
    }

    @Override
    public void connect() throws IOException {

    }

    @Override
    public InputStream getInputStream() throws IOException {
        return classPathResource.getInputStream();
    }
}
