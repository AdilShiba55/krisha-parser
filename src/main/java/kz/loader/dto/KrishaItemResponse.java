package kz.loader.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.loader.representation.ItemAdvertDTO;
import kz.loader.representation.ItemJsonDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Document("item")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KrishaItemResponse {
    private ItemJsonDTO advert;
    private List<ItemAdvertDTO> adverts;
    private String description;
    private Map<String, String> firstInfoBlock;
    private Map<String, String> secondInfoBlock;
    private List<String> phones;
}
