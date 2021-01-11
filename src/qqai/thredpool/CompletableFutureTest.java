package qqai.thredpool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 描述：异步回调
 *
 * @author qqai
 * @createTime 2020-09-09 0:15
 */

class ContextLoaderGetResourceFutureTest {
    public static void main(String[] args) throws IOException {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("thredpool/a.properties");
        Properties properties = new Properties();
        properties.load(stream);
        assert stream != null;
        stream.close();
        System.out.println(properties);
    }
}
