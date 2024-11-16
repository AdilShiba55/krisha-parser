package kz.loader.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemAdvertDTO {

    private Long id;
    private Long tempId;
    private String storage;
    private String url;
    private String title;
    private String titleWithPrice;
    private String uuid;
    private String address;
    private String fullAddress;
    private String city;
    private Integer priceM2;
    private String priceM2Text;
    private Integer daysInLive;
    private String addedAt;
    private String createdAt;
    private String expiresAtText;
    private String deletedAtText;
    private Boolean hasAutoRe;
    private Boolean hasAutoUp;
    private Boolean isAgent;
    private Boolean isHiddenPhone;

    private List<ItemAdvertServiceDTO> services;
    private ItemAdvertCategory category;
    private ItemAdvertOwnerDTO owner;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ItemAdvertServiceDTO {
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ItemAdvertOwnerDTO {

        private Boolean isPro;
        private Boolean isComplex;
        private Boolean isBuilder;
        private Boolean isOwner;
        private String title;
        private Boolean isChecked;
        private Boolean isCurrentUser;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ItemAdvertCategory {
        private Long id;
        private String name;
        private String label;
        private Boolean isDisabled;
    }

}
