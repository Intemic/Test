package com.intemic.Weather;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static com.intemic.Weather.TEMP_ENUM.*;
import static com.intemic.Weather.PRES_ENUM.*;
import static com.intemic.Weather.WIND_ENUM.*;

/**
 * Created by Anton on 19.01.2017.
 */

class ENotConnection extends Exception{
  public  ENotConnection(){
    super("Остутсвует подключение");
  }

  public ENotConnection(String message){
    super("Остутсвует подключение : " + message);
  }
}

class ENotData extends Exception{
  public ENotData(){
    super();
  }

  public ENotData(String message){
    super(message);
  }
};

public abstract class WeatherAbstract implements IWeather {
    private String TEST_URL = "http://www.google.ru/"; //"http://api.openweathermap.org"; //"http://www.google.ru/"; //http://www.ya.ru";
    private Map<String, IAction> items = new LinkedHashMap<String, IAction>();
    protected static LinkedHashMap<Integer, String> wnd = new LinkedHashMap<>();
    protected double temperature;
    protected int atmPressure, windSpeed;
    protected String windDirect, humidity, nameSity;
    //      sunrise, sunset;
    protected Date sunrise, sunset, dateUpdate;
    protected byte[] weatherIcon;

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

    //abstract int getIdByName(String name);

    abstract protected void getActionByName(String name, Map<String, IAction> it);

   // abstract protected String getURLById(int id);

    abstract protected void getActionById(int id, Map<String, IAction> it);

//    abstract protected String getURLByCoordinate(double latitude, double longitude);

    abstract protected void getActionByCoordinate(double latitude, double longitude, Map<String, IAction> it);


    private void run(){
        System.out.println("Источник : " + getClass().getSimpleName());

        // проверим наличие соединения
        try {
            testConnection();
        } catch (ENotConnection ex) {
            throw new RuntimeException(ex.getMessage());
        }

        for (String url : items.keySet())
          if (items.get(url) != null)
              ((IAction)items.get(url)).process(url);
    }

    public final void getData(int id) {
/*        try {
            // проверим соединение
            testConnection();
            parseData(connect(getURLById(id)));
        } catch (Exception e) {
            throw new RuntimeException("Не удалось обновить данные - " + e.getMessage());
        }
*/
      getActionById(id, items);
      run();
    }

    public final void getData(String name) {
       //getData(getIdByName(name));
        getActionByName(name, items);
        run();
    }

    public final void getData(double latitude, double longitude) {
        // latitude - широта, longitude - долгота
/*
        try {
            // проверим соединение
            testConnection();
            parseData(connect(getURLByCoordinate(latitude, longitude)));
        } catch (Exception e) {
            throw new RuntimeException("Не удалось обновить данные - " + e.getMessage());
        }
*/
      getActionByCoordinate(latitude, longitude, items);
      run();
    }

    protected String readFile(String fileName) throws IOException {
        String s = null;

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null)
            sb.append(s + "\n");

        return sb.toString();
    }

    protected void saveFile(String fileName, String data) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(data);
    }

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
        return windDirect != null ? windDirect : "";
    }

    @Override
    public String getHumidity() {
        return humidity != null ? humidity + " % " : "";
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
                "Влажность : " + getHumidity() + "\n" +
                "Атмосферное давление : " + getAtmPressure(PRES_MM) + "\n" +
                "Сила ветра : " + getWindSpeed(WIND_MC) + "\n" +
                "Направление ветра : " + getWindDirect() + "\n" +
                "Восход : " + getSunrise() + "\n" +
                "Закат : " + getSunset() + "\n" +
                "Иконка : " + getWeatherIcon();
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

  //  abstract void parseData(String query) throws IOException;

/*
    protected String connect(String urlSource) throws ENotData {
        String query = null, line = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;

        try{
            URL url = new URL(urlSource);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            // соберем все в кучу
            while ((line = br.readLine()) != null)
                sb.append(line + "\n");
            query = sb.toString();
            // а это уже печалька
            if (query.isEmpty())
                throw new RuntimeException("Нет данных");

        } catch (IOException io) {
            throw new ENotData("Ресурс не доступен : " + io.getMessage());
        } catch (RuntimeException re){
            throw new ENotData(re.getMessage());
        // неизвестная ошибка
        } catch (Exception ex){
            throw new ENotData(ex.getMessage());
        }finally {
           if (br != null)
               try {
                   br.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
        }

        return query;
    }
*/

    // проверяем наличие соединения
    private void testConnection() throws ENotConnection {
        HttpURLConnection urlConnection =null;

        try {
            urlConnection = (HttpURLConnection)new URL(TEST_URL).openConnection();
            urlConnection.setRequestMethod("HEAD");
            urlConnection.setUseCaches(false);
            urlConnection.connect();
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK)
                throw new ENotConnection(urlConnection.getResponseMessage());

            System.out.println("Соединение : OK");
        }catch (ENotConnection en){
            throw new ENotConnection();
        }catch (Exception ex){
          throw new ENotConnection(ex.getMessage());
        }finally {
          if ( urlConnection != null)
            urlConnection.disconnect();
        }
    }


    public static void main (String[] arg){
        Set<WeatherAbstract> st = new LinkedHashSet<>();
        st.add(new OpenWeather());
        st.add(new WunderGroundWheather());

        System.out.println("Вывод данных по имени : ---------------------------------------------");
        for (WeatherAbstract wa : st){
          wa.getData("Surgut");
          System.out.println(wa);
          System.out.println("");
        }

        System.out.println("Вывод данных по координатам : ---------------------------------------------");
        for (WeatherAbstract wa : st){
            wa.getData(61.25, 73.416672);
            System.out.println(wa);
            System.out.println("");
        }

    }
}
