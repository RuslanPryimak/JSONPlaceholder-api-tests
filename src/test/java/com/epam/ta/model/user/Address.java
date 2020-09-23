package com.epam.ta.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

}
