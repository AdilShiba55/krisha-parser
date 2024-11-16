package kz.loader.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDigitalWebsiteDTO {

    private String type;
    private String section;
    private String language;
    private String currency;
    private String environment;

}
