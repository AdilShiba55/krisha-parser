package kz.loader.service;

import kz.loader.dto.KrishaPageResponse;
import kz.loader.dto.PageFilterDTO;

public interface PageService {
    KrishaPageResponse getListData(Integer pageNum, PageFilterDTO filter);
}
