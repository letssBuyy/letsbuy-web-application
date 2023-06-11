package com.application.letsbuy.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/*
"shipping": {
        "address": {
            "street": "Avenida Brigadeiro Faria Lima",
                    "number": "1384",
                    "complement": "apto 12",
                    "locality": "Pinheiros",
                    "city": "São Paulo",
                    "region_code": "SP",
                    "country": "BRA",
                    "postal_code": "01452002"
        }
    },
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressPagSeguroDto {


    private String street;

    private String number;

    private String complement;

    private String locality;

    private String city;

    @JsonProperty("region_code")
    private String regionCode;

    private String country;

    @JsonProperty("postal_code")
    private String postalCode;
}
