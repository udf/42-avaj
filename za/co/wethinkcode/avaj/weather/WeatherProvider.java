package za.co.wethinkcode.avaj.weather;

import java.lang.Math;

public class WeatherProvider
{
    private static WeatherProvider weatherProvider = new WeatherProvider();
    public enum Weather {
        RAIN, FOG, SUN, SNOW
    };

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int seed = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        Weather[] values = Weather.values();
        return values[Math.abs(seed) % values.length].toString();
    }
}
