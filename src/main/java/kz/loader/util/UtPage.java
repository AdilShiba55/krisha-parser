package kz.loader.util;

import kz.loader.dto.PageFilterDTO;

public class UtPage {
    public static String getFullSearchUrl(Integer pageNum, PageFilterDTO filter) {
        return "https://krisha.kz/" + UtPage.getSearchUrl(filter) + "/?page="+pageNum;
    }
    public static String getSearchUrl(PageFilterDTO filter) {
        String action = filter.getAction().getName();
        String objectType = filter.getObjectType().getName();
        String district = filter.getDistrict().getName();
        return action+"/"+objectType+"/"+district;
    }
}
