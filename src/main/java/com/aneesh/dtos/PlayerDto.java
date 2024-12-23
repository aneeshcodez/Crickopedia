package com.aneesh.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerDto {
    private String fullname;
    private Character gender;
    private LocalDate dateofbirth;
    // This key is not in the JSON Response
    private int playersAge;


}
