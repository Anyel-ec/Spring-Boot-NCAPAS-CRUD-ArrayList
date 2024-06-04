package top.anyel.ncapas.model;

import top.anyel.ncapas.model.enums.AddressType;

public record Address(
        String main_street,
        String secondary_street,
        String n_house,
        String city,
        int postal_code,
        AddressType addressType)
{

}
