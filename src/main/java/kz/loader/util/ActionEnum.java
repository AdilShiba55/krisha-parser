package kz.loader.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionEnum {
    PRODAZHA("prodazha"),
    ARENDA("arenda");

    private final String name;
}
