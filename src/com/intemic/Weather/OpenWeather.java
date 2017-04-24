package com.intemic.Weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Anton on 18.01.2017.
 */
public class OpenWeather extends WeatherAbstract {
    private final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    private final String APPID = "495f7755c71feb8503704b3d82f9b0c8";
    private static final int SURGUT_ID = 1490624;
    private static final String ICON_URL = "http://openweathermap.org/img/w/";

    private String getURLById(int id) {
        return WEATHER_URL + "?id=" + id + "&APPID=" + APPID + "&units=metric" + "&lang=ru";
    }

    private String getURLByCoordinate(double latitude, double longitude) {
        return WEATHER_URL + "?lat=" + latitude + "&lon=" + longitude + "&APPID=" + APPID + "&units=metric" + "&lang=ru";
    }

    protected void parseData(String query) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readValue(query, JsonNode.class); // парсинг текста
            JsonNode mainNode = rootNode.get("main");
            JsonNode windNode = rootNode.get("wind");
            JsonNode weatherdNode = rootNode.get("weather");
            JsonNode sysNode = rootNode.get("sys");

            // температура
            temperature = mainNode.get("temp").asDouble();
            // влажность %
            humidity = mainNode.get("humidity").asText();
            // атмосферное давление
            atmPressure = mainNode.get("pressure").asInt();
            // сила ветра
            windSpeed = windNode.get("speed").asInt(); //asText() + " м/с";
            // направление ветра
            windDirect = convertWindDirect(windNode.get("deg").asInt());
            // название нас. пункта
            nameSity = rootNode.get("name").asText();
            // восход, нужно перевести в милисекунды
            sunrise = new Date(sysNode.get("sunrise").asLong() * 1000L);
            // закат
            sunset = new Date(sysNode.get("sunset").asLong() * 1000L);
            // иконка
            try {
                weatherIcon = connect(ICON_URL + weatherdNode.elements().next().get("icon").asText() + ".png").getBytes();
            } catch (RuntimeException e) {
                weatherIcon = null;
            }

            // выставим время обновления
            dateUpdate = new Date();
        } catch (IOException e) {
            System.out.print("Exception - " + e.getMessage());
        }
    }

    OpenWeather(int id) {
        parseData(connect(getURLById(SURGUT_ID)));
    }

    OpenWeather(double latitude, double longitude) {
        // latitude - широта, longitude - долгота
        parseData(connect(getURLByCoordinate(latitude, longitude)));
    }

    // для тестирования
    private OpenWeather(String data) {
        parseData(data);
    }

    public static void main(String[] arg) {
        //String test = "{\"coord\":{\"lon\":73.42,\"lat\":61.25},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"base\":\"stations\",\"main\":{\"temp\":-12,\"pressure\":1028,\"humidity\":100,\"temp_min\":-12,\"temp_max\":-12},\"visibility\":2800,\"wind\":{\"speed\":3,\"deg\":280},\"clouds\":{\"all\":40},\"dt\":1484719200,\"sys\":{\"type\":1,\"id\":7313,\"message\":0.0054,\"country\":\"RU\",\"sunrise\":1484711891,\"sunset\":1484735765},\"id\":1490624,\"name\":\"Surgut\",\"cod\":200}";
        IWeather iw = new OpenWeather(SURGUT_ID);
//        IWeather iw = new OpenWeather(61.15, 73.26);
        System.out.println(iw);
    }
}
