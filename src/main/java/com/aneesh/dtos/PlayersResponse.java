package com.aneesh.dtos;



import lombok.Data;

import java.util.List;

@Data
public class PlayersResponse {

    private List<PlayerDto> data;

}
