package kz.loader.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageCategoryDTO {

    private Long id;
    private Boolean hasPrice;
    private String name;
    private String sectionName;
    private String categoryName;
    private Integer defaultCurrency;

}
