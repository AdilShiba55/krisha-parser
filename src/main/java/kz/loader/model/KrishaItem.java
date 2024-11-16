package kz.loader.model;

import kz.loader.dto.KrishaItemResponse;
import kz.loader.dto.KrishaItemResponseDTO;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document("item")
@NoArgsConstructor
@AllArgsConstructor
public class KrishaItem {

    private Long id;
    private KrishaItemResponseDTO data;
    private Date dtCreate;
    private Date dtUpdate;
}
