package kz.loader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProxyDTO {
    private String address;
    private Integer port;

    @Override
    public String toString() {
        return address + ":" + port;
    }
}
