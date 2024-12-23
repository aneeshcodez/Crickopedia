package com.aneesh.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CountriesResponse {

    private List<CountryDto> data;

    public List<CountryDto> getData() {
        return data;
    }

    public void setData(List<CountryDto> data) {
        this.data = data;
    }
}
