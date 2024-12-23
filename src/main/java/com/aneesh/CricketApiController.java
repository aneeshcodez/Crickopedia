package com.aneesh;

import com.aneesh.dtos.Continentsresponse;
import com.aneesh.dtos.CountriesResponse;
import com.aneesh.dtos.PlayerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CricketApiController {

    @GetMapping("/continents")
    public Continentsresponse getContinents() {
        return ExternalCricketApiHelper.getAllContinents();
    }

    @GetMapping("/countries")
    public CountriesResponse getCountriesResponse(@RequestParam(value = "continentId") int continentId) {
        return ExternalCricketApiHelper.getCountries(continentId);
    }

    @GetMapping("/players")
    public List<PlayerDto> getPlayersResponse(
            @RequestParam(value = "countriesId") int countriesId,
            @RequestParam(value = "gender") char gender,
            @RequestParam(value = "age") int age) {
        return ExternalCricketApiHelper.getPlayers(countriesId, gender, age);
    }


}

