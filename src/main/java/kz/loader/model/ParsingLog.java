package kz.loader.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document("parsing_log")
public class ParsingLog {
    @Id
    private ObjectId id;
    private String url;
    private String message;
    private String type;
    private Boolean success;
    private Date dtCreate;

    public ParsingLog(String url, String message, String type, Boolean success, Date dtCreate) {
        this.url = url;
        this.message = message;
        this.type = type;
        this.success = success;
        this.dtCreate = dtCreate;
    }
}
