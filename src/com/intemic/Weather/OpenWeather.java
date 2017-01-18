package com.intemic.Weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Anton on 18.01.2017.
 */
public class OpenWeather implements IWeather {
    private String temperature, atmPressure, windSpeed, windDirect, humidity, nameSity;
    private Date dateUpdate;
    private final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    private final String APPID = "495f7755c71feb8503704b3d82f9b0c8";
    private static final int SURGUT_ID = 1490624;

    @Override
    public String getTemperature() {
        return temperature;
    }

    @Override
    public String getAtmPressure() {
        return atmPressure;
    }

    @Override
    public String getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String getWindDirect() {
        return windDirect;
    }

    @Override
    public String getHumidity() {
        return humidity;
    }

    public String getNameSity() {
        return nameSity;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    private String getURL(int id) {
        return WEATHER_URL + "?id=" + id + "&APPID=" + APPID + "&units=metric";
    }

    private void getData(String query) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readValue(query, JsonNode.class); // парсинг текста
            JsonNode mainNode = rootNode.get("main");
            JsonNode windNode = rootNode.get("wind");
            // температура
            temperature = mainNode.get("temp").asText() + "°C";
            // влажность %
            humidity = mainNode.get("humidity").asText();
            // атмосферное давление
            atmPressure = mainNode.get("pressure").asText();
            // сила ветра
            windSpeed = windNode.get("speed").asText() + " м/с";
            // направление ветра
            windDirect = windNode.get("deg").asText();
            // название нас. пункта
            nameSity = rootNode.get("name").asText();

            // выставим время обновления
            dateUpdate = new Date();
        } catch (IOException e) {
            System.out.print("Exception - " + e.getMessage());
        }
    }
    public String toString(){
      return  "Город - " + nameSity + ", Время обновления : " +
              (new SimpleDateFormat("dd.MM.yyyy HH:mm")).format(dateUpdate) + "\n\n" +
              "Текущая температура : " + temperature + "\n" +
              "Влажность : " + humidity + " % " + "\n" +
              "Атмосферное давление : " + atmPressure + " гПа" + "\n" +
              "Сила ветра : " + windSpeed + "\n" +
              "Направление ветра : " + windDirect + "\n";
    }

    OpenWeather(int id) {
        try {
            URL url = new URL(getURL(id));
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            getData(br.readLine());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    OpenWeather(String data) {
        getData(data);
    }

    public static void main(String[] arg) {
        String test = "{\"coord\":{\"lon\":73.42,\"lat\":61.25},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"base\":\"stations\",\"main\":{\"temp\":-12,\"pressure\":1028,\"humidity\":100,\"temp_min\":-12,\"temp_max\":-12},\"visibility\":2800,\"wind\":{\"speed\":3,\"deg\":280},\"clouds\":{\"all\":40},\"dt\":1484719200,\"sys\":{\"type\":1,\"id\":7313,\"message\":0.0054,\"country\":\"RU\",\"sunrise\":1484711891,\"sunset\":1484735765},\"id\":1490624,\"name\":\"Surgut\",\"cod\":200}";
        OpenWeather opw = new OpenWeather(SURGUT_ID);
        System.out.println(opw);
    }
}
