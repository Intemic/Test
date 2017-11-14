package com.intemic.Weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.jnlp.IntegrationService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Anton on 18.01.2017.
 */
public class OpenWeather extends WeatherAbstract {
    private static final int SURGUT_ID = 1490624;
    private static final String ICON_URL = "http://openweathermap.org/img/w/";
    private final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    private final String CITY_URL = "http://openweathermap.org/help/city_list.txt";
    private final String APPID = "495f7755c71feb8503704b3d82f9b0c8";
    private final String FILE_CITY = "OpenWeather_City.txt";
    //protected Map<>

    public static void main(String[] arg) {
        IWeather iw = new OpenWeather();
        try {
            iw.getData("Surgut");
            System.out.println(iw);
        } catch ( Exception ie){
            System.out.println(ie.getMessage());
        }
//        iw.getData(61.25, 73.416672);
    }

    public final int getIdByName(String name) {
        // скачиваем перечень значений
        int result = -1;

        String shablon = "\\d*\\s" + name; //".*" + name + ".*";
        Matcher m;
        Pattern pt = Pattern.compile(shablon);

        try {
            m = pt.matcher(readFile(FILE_CITY));
            // нашли строку
            if (m.find()) {
                pt = Pattern.compile("\\s");
                String split[] = pt.split(m.group());
                result = new Integer(split[0]);
            }

        } catch (PatternSyntaxException pe) {
            throw new RuntimeException("Не корректный паттерн ");
        } catch (IOException e) {
            // пытаемся вытянуть файл если его еще нет
            try {
                saveFile(FILE_CITY, connect(CITY_URL));
                result = getIdByName(name);
            } catch (Exception ie) {
                throw new RuntimeException("Не удалось найти соответствие города - id ");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            System.out.println("Ид города - " + result);
        }

        if (result == -1)
            throw new RuntimeException("Не удалось найти соответствие города - id ");

        return result;
    }

    public final String getURLById(int id) {
        return WEATHER_URL + "?id=" + id + "&APPID=" + APPID + "&units=metric" + "&lang=ru";
    }

    public final String getURLByCoordinate(double latitude, double longitude) {
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
            } catch (Exception e) {
                weatherIcon = null;
            }

            // выставим время обновления
            dateUpdate = new Date();
        } catch (IOException e) {
            System.out.print("Exception - " + e.getMessage());
        }
    }
}
