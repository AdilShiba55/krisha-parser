package kz.loader.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageSearchParametersDTO {
    private String section;
    private String category;
    private String regionAlias;
}
