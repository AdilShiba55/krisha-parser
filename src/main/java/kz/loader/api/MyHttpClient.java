package kz.loader.api;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.net.Proxy;
import java.util.Map;

public interface MyHttpClient {
    String getRequest(String url, Map<String, String> params, Map<String, String> headers, boolean withProxy, Proxy proxy);
    Document getJsoupRequest(String url, boolean withProxy, Proxy proxy);
    void initProxies();
    Proxy changeAndGetProxy();
}
