package kz.loader.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.loader.api.MyHttpClient;
import kz.loader.dto.KrishaItemResponse;
import kz.loader.representation.PhoneDataDTO;
import kz.loader.service.ItemService;
import kz.loader.util.UtItem;
import lombok.SneakyThrows;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Value("${app.krisha.app-id}")
    private String appId;
    @Value("${app.krisha.app-key}")
    private String appKey;

    private final MyHttpClient myHttpClient;
    private final ObjectMapper objectMapper;

    public ItemServiceImpl(MyHttpClient myHttpClient, ObjectMapper objectMapper) {
        this.myHttpClient = myHttpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public KrishaItemResponse getItemDataWithPhones(Long id) {
        Proxy proxy = myHttpClient.changeAndGetProxy();
        KrishaItemResponse response = getItemData(id, null);
        List<String> phones = getPhones(id, null);
        response.setPhones(phones);
        return response;
    }

    @Override
    @SneakyThrows
    public KrishaItemResponse getItemData(Long id, Proxy proxy) {
        String url = UtItem.getUrl(id);
        Document document = myHttpClient.getJsoupRequest(url, true, proxy);

        String itemDataStr = getItemDataStr(document);
        KrishaItemResponse krishaItem = objectMapper.readValue(itemDataStr, new TypeReference<>() {});
        String description = getDescription(document);
        krishaItem.setDescription(description);
        Map<String, String> firstInfoBlockMap = getFirstInfoBlockMap(document);
        Map<String, String> secondInfoBlockMap = getSecondInfoBlockMap(document);
        krishaItem.setFirstInfoBlock(firstInfoBlockMap);
        krishaItem.setSecondInfoBlock(secondInfoBlockMap);
        return krishaItem;
    }

    @Override
    @SneakyThrows
    public List<String> getPhones(Long id, Proxy proxy) {
        Map<String, String> params = Map.of(
                "appId", appId,
                "appKey", appKey,
                "id", id.toString()
        );
        String response = myHttpClient.getRequest(UtItem.getPhoneUrl(), params, null, true, proxy);
        PhoneDataDTO result = objectMapper.readValue(response, new TypeReference<>() {});
        return result.getData().getPhones();
    }

    private String getItemDataStr(Document document) {
        Element jsData = document.getElementById("jsdata");
        List<DataNode> childNotes = jsData.dataNodes();
        DataNode childNote = childNotes.get(0);
        String whole = childNote.getWholeData();
        int start = whole.indexOf("{");
        int end = whole.lastIndexOf("}")+1;
        return whole.substring(start, end);
    }

    private String getDescription(Document document) {
        StringBuilder builder = new StringBuilder();
        Elements container = document.getElementsByClass("text");
        if(container.isEmpty()) return null;
        Elements elements = container.get(0).getAllElements();
        for(Element element : elements) {
            builder.append(element.text());
        }
        return builder.toString();
    }

    private Map<String, String> getFirstInfoBlockMap(Document document) {
        Element container = document.getElementsByClass("offer__short-description").get(0);
        Elements items = container.getElementsByClass("offer__info-item");
        Map<String, String> result = new HashMap<>();
        for(Element item : items) {
            String dataName = item.attr("data-name");
            String itemTitle = item.child(0).text();
            String itemValue = item.child(2).text();
            if(!dataName.isEmpty()) {
                result.put(dataName, itemValue);
            }
        }

        return result;
    }

    private Map<String, String> getSecondInfoBlockMap(Document document) {
        Element container1 = document.getElementsByClass("offer__description").get(0);
        Element container2 = container1.getElementsByClass("offer__parameters").get(0);
        Elements items = container2.getElementsByTag("dl");
        Map<String, String> result = new HashMap<>();
        for(Element item : items) {
            Element dt = item.child(0);
            Element dd = item.child(1);
            String dataName = dt.attr("data-name");
            String itemTitle = dt.text();
            String itemValue = dd.text();
            if(!dataName.isEmpty()) {
                result.put(dataName, itemValue);
            }
        }
        return result;
    }
}
