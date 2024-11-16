package kz.loader.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.loader.api.MyHttpClient;
import kz.loader.dto.KrishaPageResponse;
import kz.loader.dto.PageFilterDTO;
import kz.loader.representation.PageDigitalDTO;
import kz.loader.service.PageService;
import kz.loader.util.UtPage;
import lombok.SneakyThrows;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    private final MyHttpClient myHttpClient;
    private final ObjectMapper objectMapper;

    public PageServiceImpl(MyHttpClient myHttpClient, ObjectMapper objectMapper) {
        this.myHttpClient = myHttpClient;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public KrishaPageResponse getListData(Integer pageNum, PageFilterDTO filter) {
        String url = UtPage.getFullSearchUrl(pageNum, filter);
        Document document = myHttpClient.getJsoupRequest(url, true, null);

        String mainDataStr = getMainDataStr(document);
        KrishaPageResponse mainData = objectMapper.readValue(mainDataStr, new TypeReference<>() {});

        String digitalDataStr = getDigitalDataStr(document);
        PageDigitalDTO digitalData = objectMapper.readValue(digitalDataStr, new TypeReference<>() {});

        mainData.setDigitalData(digitalData);
        return mainData;
    }

    private String getMainDataStr(Document document) {
        Element jsData = document.getElementById("jsdata");
        List<DataNode> childNotes = jsData.dataNodes();
        DataNode childNote = childNotes.get(0);
        String whole = childNote.getWholeData();
        int start = whole.indexOf("{");
        int end = whole.lastIndexOf("}")+1;
        return whole.substring(start, end);
    }

    private String getDigitalDataStr(Document document) {
        Elements elements = document.getElementsByTag("script");
        Element fifthScript = elements.get(5);
        List<DataNode> childNotes = fifthScript.dataNodes();
        DataNode childNote = childNotes.get(0);
        String whole = childNote.getWholeData();
        int start = whole.indexOf("{");
        int end = whole.lastIndexOf("}")+1;
        return whole.substring(start, end);
    }

}
