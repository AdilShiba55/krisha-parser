package kz.loader.dto;

import kz.loader.representation.ItemJsonDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class KrishaItemResponseDTO {
    private Long id;
    private String title;
    private String addressTitle;
    private Integer price;
    private Integer priceM2;
    private Boolean hasPrice;
    private Integer daysInLive;
    private Integer square;
    private Integer rooms;
    private String ownerName;
    private String complexName;
    private String complexPrefix;
    private String status;
    private String sectionAlias;
    private String categoryAlias;
    private Double coordY;
    private Double coordX;
    private String city;
    private String country;
    private String region;
    private String street;
    private String houseNum;
    private String description;
    private List<String> photos;
    private List<String> phones;

    private Map<String, String> firstInfoBlock;
    private Map<String, String> secondInfoBlock;
}
