package kz.loader.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document("item_queue")
public class ItemQueue {
    @Id
    private ObjectId id;
    @NotNull
    @Size(min = 1)
    private List<Long> ids;
    @NotNull
    private Date dtCreate;
    @NotNull
    private Integer pageNum;

    public ItemQueue(List<Long> ids, Date dtCreate, Integer pageNum) {
        this.ids = ids;
        this.dtCreate = dtCreate;
        this.pageNum = pageNum;
    }
}
