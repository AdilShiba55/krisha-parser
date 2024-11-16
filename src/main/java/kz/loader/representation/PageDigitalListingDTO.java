package kz.loader.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDigitalListingDTO {

    private String section;
    private String category_string;
    private List<String> category;
    private Long categoryId;
    private Integer currentPage;
    private Integer resultCount;
    private Integer pagesCount;
    private String sortBy;

}
