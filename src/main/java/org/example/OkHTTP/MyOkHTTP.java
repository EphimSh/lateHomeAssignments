package org.example.OkHTTP;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.ForecastModel.WeatherResponseModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MyOkHTTP {
    private String baseURL;
    private String apiKey;
    private ObjectMapper mapper = new ObjectMapper();
    public void init() throws IOException {
        getURLandAPI();
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(baseURL + "lat=59.93863&lon=30.31413&start=2020-03-28&end=2020-04-04&alt=184")
                .get()
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", "meteostat.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        WeatherResponseModel weatherResponse = mapper.readValue(response.body().string(), WeatherResponseModel.class);
        System.out.println("Saint Petersburg");
        System.out.println("Generated: " + weatherResponse.getMeta().getGenerated());
        System.out.println("Station numbers: " + weatherResponse.getMeta().getStations());
        System.out.println();
        for (WeatherResponseModel.DataObj dataObj : weatherResponse.getData()){
            System.out.printf(
                    "Date: %s" + "\n" +
                    "Avg.temp: %s" + "\n" +
                    "Min.temp: %s" + "\n" +
                    "Max.temp: %s" + "\n" +
                    "Perception: %s" + "\n" +
                    "Snow: %s" + "\n" +
                    "Wind direction: %s" + "\n" +
                    "Wind speed: %s" + "\n" +
                    "Wind speed gust: %s" + "\n" +
                    "Pressure: %s" + "\n" +
                    "Sun: %s" + "\n",
                    dataObj.getDate(),
                    dataObj.getAverageTemperature(),
                    dataObj.getMinTemperature(),
                    dataObj.getMaxTemperature(),
                    dataObj.getPerception(),
                    dataObj.getSnow(),
                    dataObj.getWindDirection(),
                    dataObj.getWindSpeed(),
                    dataObj.getWindSpeedGust(),
                    dataObj.getPressure(),
                    dataObj.getSun());
            System.out.println();
        }




    }

    public void getURLandAPI() throws IOException{
        Properties prop = new Properties();
        InputStream configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        baseURL = prop.getProperty("baseURL1");
        apiKey = prop.getProperty("apiKey1");
    }
}
