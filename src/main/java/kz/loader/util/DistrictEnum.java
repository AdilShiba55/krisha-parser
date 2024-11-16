package kz.loader.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum DistrictEnum {
    // Районы Алматы
    ALMATY_ALATAUSKY("almaty-alatauskij"),
    ALMATY_ALMALINSKY("almaty-almalinskij"),
    ALMATY_AUEZOVSKY("almaty-aujezovskij"),
    ALMATY_BOSTANDIKSKY("almaty-bostandykskij"),
    ALMATY_ZHETISUSKY("almaty-zhetysuskij"),
    ALMATY_MEDEUSKY("almaty-medeuskij"),
    ALMATY_NAURIZBAYSKY("almaty-nauryzbajskiy"),
    ALMATY_TURKSIBSKY("almaty-turksibskij"),

    // Районы Астаны
    ASTANA_ALMATYNSKY("astana-almatinskij"),
    ASTANA_ESILSKY("astana-esilskij"),
    ASTANA_NURA("astana-nura"),
    ASTANA_SARIARKYNSKY("astana-saryarkinskij"),
    ASTANA_BAIKONUR("r-n-bajkonur"),

    // Города
    ALMATY("almaty"),
    ASTANA("astana"),
    SHYMKENT("shymkent"),
    ABAY_OBLAST("abay-oblast"),
    AKMOLYNSKAYA_OBLAST("akmolinskaja-oblast"),
    AKTYUBINSKAYA_OBLAST("aktjubinskaja-oblast"),
    ALMATINSKAYA_OBLAST("almatinskaja-oblast"),
    ATIRAUSKAYA_OBLAST("atyrauskaja-oblast"),
    VOSTOCHNO_KAZAKHSTANSKAYA_OBLAST("vostochno-kazahstanskaja-oblast"),
    ZHAMBLSKAYA_OBLAST("zhambylskaja-oblast"),
    ZHETISAUSKAYA_OBLAST("jetisyskaya-oblast"),
    ZAPADNO_KAZAKHSTANSKAYA_OBLAST("zapadno-kazahstanskaja-oblast"),
    KARAGANDINSKAYA_OBLAST("karagandinskaja-oblast"),
    KOSTANAYSKAYA_OBLAST("kostanajskaja-oblast"),
    KYZYLORDINSKAYA_OBLAST("kyzylordinskaja-oblast"),
    MANGISTAUSKAYA_OBLAST("mangistauskaja-oblast"),
    PAVLODARSKAYA_OBLAST("pavlodarskaja-oblast"),
    SEVERO_KAZAKHSTANSKAYA_OBLAST("severo-kazahstanskaja-oblast"),
    YUZHNO_KAZAKHSTANSKAYA_OBLAST("juzhno-kazahstanskaja-oblast"),
    ULITAUSKAYA_OBLAST("ulitayskay-oblast");
//    ZARUBEZHNAYA("?das[map.geo_id]=zn", PriorityEnum.LOW);

    private final String name;
}
