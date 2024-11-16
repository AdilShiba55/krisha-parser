package kz.loader.service;

import kz.loader.dto.KrishaItemResponse;
import kz.loader.model.KrishaItem;

import java.net.Proxy;
import java.util.List;

public interface ItemService {
    KrishaItemResponse getItemDataWithPhones(Long id);
    KrishaItemResponse getItemData(Long id, Proxy proxy);
    List<String> getPhones(Long id, Proxy proxy);
}
