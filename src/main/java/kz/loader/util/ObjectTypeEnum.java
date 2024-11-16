package kz.loader.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ObjectTypeEnum {
    // Продажа
    KVARTIRA("kvartiry"), // продажа/аренда
    DOMADACHI("doma-dachi"), // продажа
    DOM("doma"), // аренда
    KOMNATA("komnaty"), // аренда
    DACHA("dachi"), // аренда
    GARAZH("garazhi"), // продажа/аренда
    KOMMERCHESKAYA_NEDVIZHIMOST("kommercheskaya-nedvizhimost"), // продажа/аренда
    PROMBAZA("prombazy"), // продажа/аренда
    PROCHAYA_NEDVIZHIMOST("prochej-nedvizhimosti"), // продажа/аренда
    VOZMU_V_ARENDU("vozmu-v-arendu"), // аренда
    ZARUBEZHNAYA_NEDVIZHIMOST("zarubezhnoj-nedvizhimosti"); // продажа

    private final String name;
}
