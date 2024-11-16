package kz.loader.util;

import kz.loader.dto.KrishaItemResponse;
import kz.loader.dto.KrishaItemResponseDTO;
import kz.loader.representation.ItemAdvertDTO;
import kz.loader.representation.ItemJsonDTO;

public class UtItem {
    public static String getUrl(Long id) {
        return "https://krisha.kz/a/show/" + id;
    }

    public static String getPhoneUrl() {
        return "https://app.krisha.kz/a/getPhones";
    }

    public static String getPhoneUrl(Long id) {
        return getPhoneUrl() + "?id="  + id;
    }

    public static KrishaItemResponseDTO getKrishaItemResponseDTO(KrishaItemResponse item) {
        ItemJsonDTO mainAdvert = item.getAdvert();
        ItemJsonDTO.ItemAddressDTO address = mainAdvert.getAddress();
        ItemAdvertDTO firstSubAdvert = item.getAdverts().get(0);
        return KrishaItemResponseDTO.builder()
                .id(mainAdvert.getId())
                .title(firstSubAdvert.getTitle())
                .addressTitle(mainAdvert.getAddressTitle())
                .price(mainAdvert.getPrice())
                .priceM2(firstSubAdvert.getPriceM2())
                .daysInLive(firstSubAdvert.getDaysInLive())
                .square(mainAdvert.getSquare())
                .rooms(mainAdvert.getRooms())
                .ownerName(mainAdvert.getOwnerName())
                .complexName(mainAdvert.getComplexName())
                .complexPrefix(mainAdvert.getComplexPrefix())
                .status(mainAdvert.getStatus())
                .sectionAlias(mainAdvert.getSectionAlias())
                .categoryAlias(mainAdvert.getCategoryAlias())
                .coordY(mainAdvert.getMap().getLat())
                .coordX(mainAdvert.getMap().getLon())
                .city(address.getCity())
                .country(address.getCountry())
                .region(address.getRegion())
                .street(address.getStreet())
                .houseNum(address.getHouse_num())
                .description(item.getDescription())
                .photos(mainAdvert.getPhotos())
                .phones(item.getPhones())
                .firstInfoBlock(item.getFirstInfoBlock())
                .secondInfoBlock(item.getSecondInfoBlock())
                .build();

    }
}
