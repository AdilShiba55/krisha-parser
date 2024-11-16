package kz.loader.dto;

import kz.loader.util.DistrictEnum;
import kz.loader.util.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DistrictPriority {
    private DistrictEnum district;
    private PriorityEnum priority;
}
