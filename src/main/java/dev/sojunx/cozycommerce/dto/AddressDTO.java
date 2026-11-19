package dev.sojunx.cozycommerce.dto;

import dev.sojunx.cozycommerce.domain.Address;

public record AddressDTO(Long id, String phone, String addressLine, String district, String city, Boolean isDefault) {
    public static AddressDTO from(Address entity) {
        return new AddressDTO(entity.getId(), entity.getPhone(), entity.getAddressLine(), entity.getDistrict(), entity.getCity(), entity.getIsDefault());
    }
}
