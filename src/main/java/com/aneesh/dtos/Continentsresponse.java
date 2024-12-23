package com.aneesh.dtos;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Continentsresponse {

    private List<ContinentDto> continents;

}
