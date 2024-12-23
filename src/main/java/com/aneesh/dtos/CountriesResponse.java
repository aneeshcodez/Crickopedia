package com.aneesh.dtos;


import lombok.Data;

import java.util.List;

@Data
public class CountriesResponse {

    private List<CountryDto> data;

}
