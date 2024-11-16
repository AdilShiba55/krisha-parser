package kz.loader.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoBlockDTO {

    private String cityText;
    private Long wallType;
    private String wallTypeText;
    private Long complex;
    private String complexText;
    private Integer builtYear;
    private Integer floorNum;
    private Integer floorCount;
    private Double area;
    private Double areaLive;
    private Double areaKitchen;
    private Double areaLand;
    private Long condition;
    private String conditionText;
    private Long toiletType;
    private String toiletTypeText;
    private Double ceiling;
    private Long parking;
    private String parkingText;
    private Boolean mortgage;
    private Long balconyType;
    private String balconyTypeText;
    private Boolean isBalconyGlass;
    private Boolean formerDorm;
    private Boolean isStudio;
    private Long sewageType;
    private String sewageTypeText;
    private String houseRoofText;
    private Long electricityType;
    private String electricityTypeText;
    private String fenceText;
    private String irrigationText;
    private Boolean exchange;
    private String dachaComplexName;
    private String phoneText;
    private String securityText;
    private Long furnitureType;
    private String furnitureTypeText;
    private String furnitureText;
    private String rentRenovationText;
    private Long floorType;
    private String floorTypeText;
    private Long waterType;
    private String waterTypeText;
    private String gasTypeText;
    private String facilitiesText;
    private String whoMatchText;
    private Integer toiletCount;
    private String bathroomText;
    private Boolean bail;
    private Integer balconyCount;
    private String windowSideText;
    private Boolean separatedToilet;
    private Integer loggiaCount;
    private String inetTypeText;
    private String doorTypeText;

    private Boolean doorPhone;
    private Boolean security;
    private Boolean concierge;
    private Boolean CCTV; //видеонаблюдение
    private Boolean alarm;
    private Boolean windowGrid;

    private Map<String, String> unknowns = new HashMap<>();
    public void addToUnknowns(String key, String value) {
        this.unknowns.put(key, value);
    }

}
