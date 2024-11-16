package kz.loader.dto;

import kz.loader.util.ActionEnum;
import kz.loader.util.DistrictEnum;
import kz.loader.util.ObjectTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageFilterDTO {

    private ActionEnum action;
    private ObjectTypeEnum objectType;
    private DistrictEnum district;

    public PageFilterDTO(ActionEnum action, ObjectTypeEnum objectType, DistrictEnum district) {
        this.action = action;
        this.objectType = objectType;
        this.district = district;
    }
}
