package kz.loader.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemJsonDTO {

    private Long id;
    private String storage;
    private String commentsType;
    private Boolean isCommentable;
    private Boolean isCommentableByEveryone;
    private Boolean isOnMap;
    private Boolean hasPrice;
    private Integer price;
    private Boolean hasPackages;
    private String title;
    private String addressTitle;
    private String userType;
    private Integer square;
    private Integer rooms;
    private String ownerName;
    private String status;
    private String complexUrlAlias;
    private String complexName;
    private String complexPrefix;
    private ItemMapDTO map;
    private String sectionAlias;
    private String categoryAlias;
    private Boolean isOwnedByCurrentUser;
    private ItemAddressDTO address;
    private List<ItemPhotoDTO> photos;

    public List<String> getPhotos() {
        return this.photos.stream()
                .map(item -> item.src)
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ItemMapDTO {

        private Double lat;
        private Double lon;
        private Integer zoom;
        private String type;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ItemAddressDTO {

        private String district;
        private String country;
        private String city;
        private String street;
        private String house_num;
        private String corner_street;
        private String region;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ItemPhotoDTO {

        private String src;
        private Integer w;
        private Integer h;
        private String title;
        private String alt;

    }

}
