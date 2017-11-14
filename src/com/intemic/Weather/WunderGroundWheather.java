package com.intemic.Weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anton on 20.01.2017.
 */
public class WunderGroundWheather extends WeatherAbstract {
    private static final int SURGUT_ID = 1490624;
    //    private final String WEATHER_URL = "http://api.wunderground.com/api/76018f06d58ffb68/forecast/lang:RU/q/61.25,73.42.json";
    private final String WEATHER_URL = "http://api.wunderground.com/api/APPID/FEATURE/lang:RU/q/QUERY.json";
    private final String APPID = "76018f06d58ffb68";
    private final String CONDITON = "conditions";
    private final String ASTRONOMY = "astronomy";
//    private final String QUERY = "61.25,73.42";
    //private static final String ICON_URL = "http://openweathermap.org/img/w/";

    public static void main(String[] arg) {
        IWeather iw = new WunderGroundWheather();
        try {
            //iw.getData(61.25, 73.42);
            iw.getData("Surgut");
            System.out.println(iw);
        } catch (Exception io) {
            System.out.println("Не удалось обновить данные");
        }
    }

    //  основные данные
    private class Action extends Processor {
        @Override
        protected void parseData(String data) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readValue(data, JsonNode.class); // парсинг текста
                JsonNode mainNode = rootNode.get("current_observation");
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("hh:mm:ss");

                // температура
                temperature = mainNode.get("temp_c").asDouble();
                // влажность %
                humidity = mainNode.get("relative_humidity").asText().replace("%", "");
                // атмосферное давление
                atmPressure = mainNode.get("pressure_mb").asInt();
                // сила ветра, переводим из миль/час в м/с
                double windMC = mainNode.get("wind_mph").asInt() * 0.44704;
                windSpeed = (int) Math.round(windMC);
                // направление ветра
                windDirect = convertWindDirect(mainNode.get("wind_degrees").asInt());

                // название нас. пункта
                JsonNode locationNode = mainNode.get("display_location");
                nameSity = locationNode.get("city").asText();
                // дата обновления
                //Matcher m = Pattern.compile("\\d{2}:\\d{2}:\\d{2}").matcher(mainNode.get("local_time_rfc822").asText());
                //if (m.find())
                //    dateUpdate = format.parse(m.group());
                dateUpdate = new Date();

                // иконка
                JsonNode image = mainNode.get("image");
                try {
                    weatherIcon = connect(image.get("url").asText()).getBytes();
                } catch (Exception e) {
                    weatherIcon = null;
                }


            } catch (Exception e) {
                System.out.print("Exception - " + e.getMessage());
            }
        }
    }

    private class ActionAstro extends Processor {
        @Override
        protected void parseData(String data) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readValue(data, JsonNode.class); // парсинг текста
                JsonNode mainNode = rootNode.get("sun_phase");
                JsonNode sunriseNode = mainNode.get("sunrise");
                JsonNode sunsetNode = mainNode.get("sunset");
                SimpleDateFormat format = new SimpleDateFormat();
                String hour, minute;

                format.applyPattern("hh:mm");

                // восход солнца
                hour = sunriseNode.get("hour").asText();
                minute = sunriseNode.get("minute").asText();
                sunrise = format.parse(hour + ":" + minute);

                // закат солнца
                hour = sunsetNode.get("hour").asText();
                minute = sunsetNode.get("minute").asText();
                sunset = format.parse(hour + ":" + minute);

            } catch (Exception e) {
                System.out.print("Exception - " + e.getMessage());
            }
        }
    }

/*
    public final String getURLById(int id) {
        return WEATHER_URL.replace("APPID", APPID).replace("FEATURE", CONDITON).replace("QUERY", QUERY);
    }

    public final int getIdByName(String name) {
        throw new RuntimeException("Не реализованно");
    }

    public final String getURLAstroById(int id) {
        return WEATHER_URL.replace("APPID", APPID).replace("FEATURE", ASTRONOMY).replace("QUERY", QUERY);
    }

    public final String getURLByCoordinate(double latitude, double longitude) {
        String query;
        query = new Double(latitude).toString() + "," + new Double(longitude).toString();
        return WEATHER_URL.replace("APPID", APPID).replace("FEATURE", CONDITON).replace("QUERY", query);
    }

    public final String getURLAstroByCoordinate(double latitude, double longitude) {
        String query;
        query = new Double(latitude).toString() + "," + new Double(longitude).toString();
        return WEATHER_URL.replace("APPID", APPID).replace("FEATURE", ASTRONOMY).replace("QUERY", query);
    }


        // выбираем основные данные
        protected void parseData(String query) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readValue(query, JsonNode.class); // парсинг текста
                JsonNode mainNode = rootNode.get("current_observation");
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("hh:mm:ss");

                // температура
                temperature = mainNode.get("temp_c").asDouble();
                // влажность %
                humidity = mainNode.get("relative_humidity").asText().replace("%", "");
                // атмосферное давление
                atmPressure = mainNode.get("pressure_mb").asInt();
                // сила ветра, переводим из миль/час в м/с
                double windMC = mainNode.get("wind_mph").asInt() * 0.44704;
                windSpeed = (int) Math.round(windMC);
                // направление ветра
                windDirect = convertWindDirect(mainNode.get("wind_degrees").asInt());

                // название нас. пункта
                JsonNode locationNode = mainNode.get("display_location");
                nameSity = locationNode.get("city").asText();
                // дата обновления
                //Matcher m = Pattern.compile("\\d{2}:\\d{2}:\\d{2}").matcher(mainNode.get("local_time_rfc822").asText());
                //if (m.find())
                //    dateUpdate = format.parse(m.group());
                dateUpdate = new Date();

                // иконка
                JsonNode image = mainNode.get("image");
                try {
                    weatherIcon = connect(image.get("url").asText()).getBytes();
                } catch (Exception e) {
                    weatherIcon = null;
                }


            } catch (IOException e) {
                System.out.print("Exception - " + e.getMessage());
    //        } catch (ParseException e) {
    //            e.printStackTrace();
            }
        }
*/


    // астрономические данные
/*
    private void ParseDataAstro(String query) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readValue(query, JsonNode.class); // парсинг текста
            JsonNode mainNode = rootNode.get("sun_phase");
            JsonNode sunrise = mainNode.get("sunrise");
            JsonNode sunset = mainNode.get("sunset");
            SimpleDateFormat format = new SimpleDateFormat();
            String hour, minute;

            format.applyPattern("hh:mm");

            // восход солнца
            hour = sunrise.get("hour").asText();
            minute = sunrise.get("minute").asText();
            super.sunrise = format.parse(hour + ":" + minute);

            // закат солнца
            hour = sunset.get("hour").asText();
            minute = sunset.get("minute").asText();
            super.sunset = format.parse(hour + ":" + minute);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

*/

    @Override
    protected void getActionByName(String name, Map<String, IAction> it) {
        String query = null;

        it.clear();
        query = "Russian/" + name;
        // основные данные
        it.put(WEATHER_URL.replace("APPID", APPID).replace("FEATURE", CONDITON).replace("QUERY", query), new Action());
        // астрологические данные
        it.put(WEATHER_URL.replace("APPID", APPID).replace("FEATURE", ASTRONOMY).replace("QUERY", query), new ActionAstro());
    }

    @Override
    protected void getActionById(int id, Map<String, IAction> it) {
        it.clear();
        // return ;
        throw new RuntimeException("Не реализованно");
    }

    @Override
    protected void getActionByCoordinate(double latitude, double longitude, Map<String, IAction> it) {
        String query = null;

        it.clear();
        query = new Double(latitude).toString() + "," + new Double(longitude).toString();
        // основные данные
        it.put(WEATHER_URL.replace("APPID", APPID).replace("FEATURE", CONDITON).replace("QUERY", query), new Action());
        // астрологические данные
        it.put(WEATHER_URL.replace("APPID", APPID).replace("FEATURE", ASTRONOMY).replace("QUERY", query), new ActionAstro());
    }
}
