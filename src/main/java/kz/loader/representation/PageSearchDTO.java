package kz.loader.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageSearchDTO {

    private List<Long> ids;
    private Long regionId;
    private Boolean isOnMap;
    private Long userId;
    private PageSearchParametersDTO parameters;
    private String nbTotal;

}
