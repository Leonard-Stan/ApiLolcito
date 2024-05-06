package org.example;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class RiotBasic {
    private static final String API_KEY = "RGAPI-70cc677c-5c76-4779-a6b6-78f1d6322107";
    private static final String REGION = "euw1";

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://" + REGION + ".api.riotgames.com/lol/summoner/v4/summoners/by-name/SummonerName?api_key=" + API_KEY);

        HttpResponse response = httpClient.execute(request);
        String responseBody = EntityUtils.toString(response.getEntity());

        ObjectMapper mapper = new ObjectMapper();
        Summoner summoner = mapper.readValue(responseBody, Summoner.class);

        System.out.println("Summoner ID: " + summoner.getId());
        System.out.println("Summoner Name: " + summoner.getName());

        httpClient.close();
    }

    private static class Summoner {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
