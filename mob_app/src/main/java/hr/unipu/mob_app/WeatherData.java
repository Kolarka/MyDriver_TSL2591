package hr.unipu.mob_app;


import org.json.JSONException;
import org.json.JSONObject;

public class WeatherData {
    private String temperature;
    private String icon;
    private String city;
    private String weatherType;
    private int condition;

    public  static WeatherData fromJson(JSONObject jsonObject){
        try {
            WeatherData weatherData = new WeatherData();
            weatherData.city= jsonObject.getString("name");
            weatherData.condition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherData.weatherType = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            weatherData.icon= updateWeatherIcon(weatherData.condition);
            double tempResult = jsonObject.getJSONObject("main").getDouble("temp")-273.15;
            int roundedValue=(int)Math.rint(tempResult);
            weatherData.temperature=Integer.toString(roundedValue);
            return weatherData;
        }
        catch (JSONException e){
            e.printStackTrace();
            return  null;
        }
    }

    private static String updateWeatherIcon(int condition) {
        if(condition>=0 && condition<=300)
        {
            return "storm";
        }
        else if(condition>=300 && condition<=500)
        {
            return "rain";
        }
        else if(condition>=500 && condition<=600)
        {
            return "raining";
        }
        else  if(condition>=600 && condition<=700)
        {
            return "snowing";
        }
        else if(condition>=701 && condition<=771)
        {
            return "fog";
        }

        else if(condition>=772 && condition<800)
        {
            return "overcast";
        }
        else if(condition==800)
        {
            return "sun";
        }
        else if(condition>=801 && condition<=804)
        {
            return "overcast";
        }
        else  if(condition>=900 && condition<=902)
        {
            return "thunderstorm";
        }
        if(condition==903)
        {
            return "snownight";
        }
        if(condition==904)
        {
            return "sun";
        }
        if(condition>=905 && condition<=1000)
        {
            return "thunderstorm";
        }

        return "dunno";


    }

    public String getTemperature() {
        return temperature+"°C";
    }

    public String getIcon() {
        return icon;
    }

    public String getCity() {
        return city;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public int getCondition() {
        return condition;
    }
}
