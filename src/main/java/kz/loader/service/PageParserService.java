package kz.loader.service;

import kz.loader.dto.PageFilterDTO;

import java.util.List;
import java.util.concurrent.ExecutorService;

public interface PageParserService {
    void parseIdsAsc(List<PageFilterDTO> filters, Integer threadCount);
    void parseIdsDesc(List<PageFilterDTO> filters, Integer threadCount);
    Integer getPagesCount(PageFilterDTO filter);
}
