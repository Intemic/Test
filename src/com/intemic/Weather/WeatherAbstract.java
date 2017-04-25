package com.intemic.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.intemic.Weather.TEMP_ENUM.*;
import static com.intemic.Weather.PRES_ENUM.*;
import static com.intemic.Weather.WIND_ENUM.*;

/**
 * Created by Anton on 19.01.2017.
 */
public abstract class WeatherAbstract implements IWeather {
    protected double temperature;
    protected int atmPressure, windSpeed;
    protected String windDirect, humidity, nameSity;
    //      sunrise, sunset;
    protected Date sunrise, sunset, dateUpdate;
    protected byte[] weatherIcon;
    protected static LinkedHashMap<Integer, String> wnd = new LinkedHashMap<>();

    // роза ветров
    static {
        wnd.put(360, "C");
        wnd.put(315, "CЗ");
        wnd.put(270, "З");
        wnd.put(225, "ЮЗ");
        wnd.put(180, "Ю");
        wnd.put(135, "ЮВ");
        wnd.put(90, "В");
        wnd.put(45, "СВ");
        wnd.put(0, "C");
    }

    abstract protected String getURLById(int id);

    abstract protected String getURLByCoordinate(double latitude, double longitude);

    @Override
    public String getTemperature(TEMP_ENUM format) {
        switch (format) {
            case TMP_C:
                return new Long(Math.round(temperature)).toString() + " °C";
            case TMP_F:
                return new Double(converDegreesToFahrenheit(temperature)).toString();
            default:
                return "";
        }
    }

    @Override
    public String getAtmPressure(PRES_ENUM format) {
        switch (format) {
            case PRES_MM:
                return new Integer(converAtmPressureMM(atmPressure)).toString() + " мм рт.ст.";
            case PRES_PA:
                return new Integer(atmPressure).toString() + " КПа";
            default:
                return "";
        }
    }

    @Override
    public String getWindSpeed(WIND_ENUM format) {
        double kmh;
        switch (format) {
            case WIND_MC:
                return new Integer(windSpeed).toString() + " м/с";
            case WIND_KH:
                // переводим в км/ч
                kmh = windSpeed * 3.6;
                return new Long(Math.round(kmh)).toString();
            default:
                return "";
        }
    }

    @Override
    public String getWindDirect() {
        return windDirect;
    }

    @Override
    public String getHumidity() {
        return humidity;
    }

    @Override
    public String getNameSity() {
        return nameSity;
    }

    @Override
    public String getSunrise() {
        String result = "";

        try {
            result = new SimpleDateFormat("HH:mm").format(sunrise);
        } catch (NullPointerException np) {
            System.out.println("Sunrise error " + np.getMessage());
        } finally {
            return result;
        }
    }

    @Override
    public String getSunset() {
        String result = "";

        try {
            result = new SimpleDateFormat("HH:mm").format(sunset);
        } catch (NullPointerException np) {
            System.out.println("Sunset error " + np.getMessage());
        } finally {
            return result;
        }
    }

    public String getDateUpdate() {
        String result = "";

        try {
            result = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(dateUpdate);
        } catch (NullPointerException np) {
            System.out.println("DateUpdate error " + np.getMessage());
        } finally {
            return result;
        }
    }

    public byte[] getWeatherIcon() {
        return weatherIcon;
    }

    public String toString() {
        return "Город - " + nameSity + ", Время обновления : " + getDateUpdate() + "\n\n" +
                "Текущая температура : " + getTemperature(TMP_C) + "\n" +
                "Влажность : " + humidity + " % " + "\n" +
                "Атмосферное давление : " + getAtmPressure(PRES_MM) + "\n" +
                "Сила ветра : " + getWindSpeed(WIND_MC) + "\n" +
                "Направление ветра : " + windDirect + "\n" +
                "Восход : " + getSunrise() + "\n" +
                "Закат : " + getSunset() + "\n" +
                "Иконка : " + weatherIcon;
    }

    private double converDegreesToFahrenheit(double value) {
        return value * 5 / 9 + 32;
    }

    protected String convertWindDirect(int direct) {
        int prev = 360;

        for (Map.Entry<Integer, String> ent : wnd.entrySet()) {
            if (ent.getKey() < direct) {
                double centre = ent.getKey() + (prev - ent.getKey()) / 2.0;
                if (direct >= centre)
                    return wnd.get(prev);
                else
                    return ent.getValue();
            }
            prev = ent.getKey();
        }

        return new Integer(direct).toString();
    }

    protected int converAtmPressureMM(int pressure) {
        return new Long(Math.round(pressure * 100 / 133.322)).intValue();
    }

    abstract  void parseData(String query) throws IOException;

    protected String connect(String urlSource) {
        String query = null, line = null;
        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL(urlSource);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            // соберем все в кучу
            while (( line = br.readLine()) != null)
              sb.append(line + "\n");
            query = sb.toString();
            // а это уже печалька
            if (query == "")
              throw new IOException("Не удалось получить ответ");
        } catch (IOException e) {
            throw new RuntimeException("Не удалось установить соединение");
        }

        return query;
    }

    public WeatherAbstract(int id){
        try {
            parseData(connect(getURLById(id)));
        } catch (IOException e){
           throw new RuntimeException(e);
        }
    }

    public WeatherAbstract(double latitude, double longitude) {
        // latitude - широта, longitude - долгота
        try {
            parseData(connect(getURLByCoordinate(latitude, longitude)));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
