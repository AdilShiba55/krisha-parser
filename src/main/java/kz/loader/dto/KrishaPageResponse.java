package kz.loader.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.loader.representation.PageCategoryDTO;
import kz.loader.representation.PageDigitalDTO;
import kz.loader.representation.PageSearchDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KrishaPageResponse {
    private PageSearchDTO search;
    private PageCategoryDTO category;
    private String baseHostname;
    private String mobileHostname;
    private String cookieDomain;
    private Boolean isRemoteResourcesEnabled;
    private String action;
    private String photoHost;
    private String appToken;
    private PageDigitalDTO digitalData;
}
