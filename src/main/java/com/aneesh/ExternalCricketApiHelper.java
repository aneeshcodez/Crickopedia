package com.aneesh;


import com.aneesh.dtos.Continentsresponse;
import com.aneesh.dtos.CountriesResponse;
import com.aneesh.dtos.PlayerDto;
import com.aneesh.dtos.PlayersResponse;
import com.aneesh.utilis.OkhttpUtility;
import com.aneesh.utilis.SecretsHelper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public class ExternalCricketApiHelper {
    private static final String continentsDomain = SecretsHelper.getProperty("cricketAPI.continents");
    private static final String countriesDomain = SecretsHelper.getProperty("cricketAPI.countries");
    private static final String playersDomain = SecretsHelper.getProperty("cricketAPI.players");


    // Static method to get all continents
    public static Continentsresponse getAllContinents() {
        try {
            Continentsresponse continentsresponse = OkhttpUtility.getRequest(continentsDomain, Continentsresponse.class);
            return continentsresponse;
        } catch (IOException e) {
            System.out.println("Error fetching continents: " + e.getMessage());
            return null;
        }
    }


    public static CountriesResponse getCountries(int id) {
        try {
            CountriesResponse countriesResponse = OkhttpUtility.getRequest(countriesDomain + id, CountriesResponse.class);
            return countriesResponse;
        } catch (IOException e) {
            System.out.println("Error fetching countries: " + e.getMessage());
            return null;
        }
    }


    public static List<PlayerDto> getPlayers(int countriesId, char gender, int age) {
        try {
            PlayersResponse playersResponse = OkhttpUtility.getRequest(playersDomain + countriesId, PlayersResponse.class);
            List<PlayerDto> filteredPlayers = new ArrayList<>();
            for (PlayerDto player : playersResponse.getData()) {
                if (player.getGender().equals(gender)) {
                    int playerAge = calculateAge(player.getDateofbirth(), LocalDate.now());
                    player.setPlayersAge(playerAge);

                    if (playerAge < age) {

                        filteredPlayers.add(player);
                    }
                }
            }
            return filteredPlayers;
        } catch (IOException e) {
            System.out.println("Error fetching players: " + e.getMessage());
            return null;
        }
    }

    private static int calculateAge(LocalDate birthdate, LocalDate currentDate) {
        if ((birthdate == null) || (currentDate == null)) {
            return 0;
        }
        return Period.between(birthdate, currentDate).getYears();
    }


}
