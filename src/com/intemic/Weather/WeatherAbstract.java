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

/**
 * Created by Anton on 19.01.2017.
 */
public abstract class WeatherAbstract implements IWeather {
    protected String temperature, atmPressure, windSpeed, windDirect, humidity, nameSity;
    protected Date dateUpdate;
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

    public byte[] getWeatherIcon() {
      return weatherIcon;
    }

    public String toString() {
        return "Город - " + nameSity + ", Время обновления : " +
                (new SimpleDateFormat("dd.MM.yyyy HH:mm")).format(dateUpdate) + "\n\n" +
                "Текущая температура : " + temperature + "\n" +
                "Влажность : " + humidity + " % " + "\n" +
                "Атмосферное давление : " + atmPressure + "\n" +
                "Сила ветра : " + windSpeed + "\n" +
                "Направление ветра : " + windDirect + "\n" +
                "Иконка : " + weatherIcon;
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

    protected String converAtmPressureMM(int pressure) {
        return new Long(Math.round(pressure * 100 / 133.322)).toString() + " мм рт.ст.";
    }

    protected abstract void getData(String query);

    protected String connect(String urlSource) {
        String query = null;

        try {
            URL url = new URL(urlSource);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            query = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось установить соединение");
        }

        return query;
    }
}
