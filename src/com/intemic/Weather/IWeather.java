package com.intemic.Weather;

import java.util.Date;

/**
 * Created by Anton on 18.01.2017.
 */
public interface IWeather {
    // температура
    String getTemperature();

    // атмосферное давление
    String getAtmPressure();

    // сила ветра
    String getWindSpeed();

    // направление ветра
    String getWindDirect();

    // влажность %
    String getHumidity();

    // наименование нас. пункта
    String getNameSity();

   Date getDateUpdate();
}
