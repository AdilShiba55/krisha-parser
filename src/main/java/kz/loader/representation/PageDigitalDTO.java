package kz.loader.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDigitalDTO {

    private String version;
    private PageDigitalWebsiteDTO website;
    private PageDigitalListingDTO listing;

}
