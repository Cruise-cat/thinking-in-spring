package sun.net.www.protocol.x;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * 自定义 X 协议 {@link URLStreamHandler} 实现
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/4
 */
public class Handler extends URLStreamHandler{

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new XURLConnection(u);
    }
}
