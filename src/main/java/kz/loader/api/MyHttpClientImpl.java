package kz.loader.api;

import kz.loader.dto.ProxyDTO;
import kz.loader.service.ParsingLogService;
import kz.loader.util.UtTime;
import lombok.SneakyThrows;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class MyHttpClientImpl implements MyHttpClient {

    @Value("${app.krisha.user-agent}")
    private String USER_AGENT;
    @Value("${app.krisha.referrer}")
    private String REFERRER;
    @Value("${app.proxy.enabled}")
    private Boolean proxyEnabled;
    @Value("${app.proxy.url}")
    private String proxyUrl;

    private List<ProxyDTO> proxies;
    private AtomicInteger proxyIndex;

    @PostConstruct
    public void init() {
        if(proxyEnabled) {
            initProxies();
        }
    }

    @Override
    public String getRequest(String url, Map<String, String> params, Map<String, String> headers, boolean withProxy, Proxy proxy) {
        Request.Builder builder = new Request.Builder();
        HttpUrl httpUrl = HttpUrl.get(url)
                .newBuilder()
                .build();
        HttpUrl.Builder httpBuilder = httpUrl.newBuilder();

        if(params != null) {
            for (String key : params.keySet()) {
                String value = params.get(key);
                httpBuilder.addEncodedQueryParameter(key, value);
            }
        }

        if(headers != null) {
            for (String key : headers.keySet()) {
                String value = headers.get(key);
                builder.addHeader(key, value);
            }
        }

        Request request = builder
                .url(httpBuilder.build())
                .build();

        return execute(request, withProxy, proxy);

    }

    @SneakyThrows
    private String execute(Request request, boolean withProxy, Proxy proxy) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.MINUTES);
        if(proxyEnabled && withProxy) {
            builder.proxy(proxy != null ? proxy : changeAndGetProxy());
        }
        OkHttpClient okHttpClient = builder.build();

        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    @Override
    @SneakyThrows
    public Document getJsoupRequest(String url, boolean withProxy, Proxy proxy) {
        Connection connection = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .timeout(UtTime.THREE_MINUTES);
        if(proxyEnabled && withProxy) {
            connection.proxy(proxy != null ? proxy : changeAndGetProxy());
        }

        return connection.get();
    }

    @Override
    public void initProxies() {
        String result = getRequest(proxyUrl, new HashMap<>(), null, false, null);
        this.proxies = Arrays.stream(result.split("\r\n")).map(proxy -> {
            String[] splited = proxy.split(":");
            return new ProxyDTO(splited[0], Integer.parseInt(splited[1]));
        }).collect(Collectors.toList());
        this.proxyIndex = new AtomicInteger(0);
    }

    @Override
    public Proxy changeAndGetProxy() {
        int index = proxyIndex.updateAndGet(i -> (i + 1) % proxies.size());
        ProxyDTO proxy = proxies.get(index);
        System.out.println("PROXY CHANGED TO " + proxy.toString());
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy.getAddress(), proxy.getPort()));
    }
}
