package za.co.wethinkcode.avaj.simulator;

import za.co.wethinkcode.avaj.weather.Coordinates;
import za.co.wethinkcode.avaj.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() {
        this.conditionChanged();
    }
}
