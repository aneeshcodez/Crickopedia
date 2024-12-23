package com.aneesh;


import com.aneesh.dtos.*;
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


    private static List<ContinentDto> displayContinents(Continentsresponse continentsresponse) {
        // Validate API response
        if (continentsresponse == null || continentsresponse.getContinents() == null || continentsresponse.getContinents().isEmpty()) {
            System.out.println("No continents found");
            return null;
        }

        // Display continents
        System.out.println("Here's the list of continents you can explore:");
        List<ContinentDto> continents = continentsresponse.getContinents();
        for (int i = 0; i < continents.size(); i++) {
            System.out.println((i + 1) + ". " + continents.get(i).getName());
        }
        return continents;

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
    public static int calculateAge(LocalDate birthdate, LocalDate currentDate) {
        if ((birthdate != null) && (currentDate != null)) {

            return Period.between(birthdate, currentDate).getYears();
        } else {
            return 0;
        }
    }




}
