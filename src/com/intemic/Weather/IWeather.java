package com.intemic.Weather;

// Формат вывода температуры
enum TEMP_ENUM {TMP_C, TMP_F};

// Формат вывода давления
enum PRES_ENUM {PRES_MM, PRES_PA};

// Формат вывода силы ветра
enum WIND_ENUM {WIND_MC, WIND_KH};

/**
 * Created by Anton on 18.01.2017.
 */

interface IParser{
  void process(String URL);
}

public interface IWeather {
    // температура
    String getTemperature(TEMP_ENUM format);

    // атмосферное давление
    String getAtmPressure(PRES_ENUM format);

    // сила ветра
    String getWindSpeed(WIND_ENUM format);

    // направление ветра
    String getWindDirect();

    // влажность %
    String getHumidity();

    // восход
    String getSunrise();

    // закат
    String getSunset();

    // наименование нас. пункта
    String getNameSity();

    // время обновления
    String getDateUpdate();

    // иконка погоды
    byte[] getWeatherIcon();

    // получение данных по Id
    void getData(int id);

    // получение данных по наименованию города
    void getData(String name);

    // получение данных по координатам города
    void getData(double latitude, double longitude);

}
