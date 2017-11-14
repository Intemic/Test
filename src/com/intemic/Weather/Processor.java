package com.intemic.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

interface IAction{
  void process(String url);
}

abstract class Processor implements IAction{
    abstract protected void parseData(String data);

    public static String connect(String urlSource) throws ENotData {
        String query = null, line = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;

        try {
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
        } catch (RuntimeException re) {
            throw new ENotData(re.getMessage());
            // неизвестная ошибка
        } catch (Exception ex) {
            throw new ENotData(ex.getMessage());
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return query;
    }

    public void process(String url) {
        try {
            parseData(connect(url));
        } catch (ENotData exdata) {
           throw new RuntimeException(exdata.getMessage());
        } catch (Exception ex ){
          // пока ничего не делаем
          System.out.println("Не удалось распарсить");
        }
    }

}