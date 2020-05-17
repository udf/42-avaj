package za.co.wethinkcode.avaj.simulator.vehicles;

import java.util.HashMap;

import za.co.wethinkcode.avaj.simulator.WeatherTower;
import za.co.wethinkcode.avaj.weather.Coordinates;
import za.co.wethinkcode.avaj.weather.WeatherProvider.Weather;

public class Helicopter extends Aircraft implements Flyable {
    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.className = "Helicopter";

        this.messages = new HashMap<Weather, String>() {{
            put(Weather.SUN, "Perfect weather for flying.");
            put(Weather.RAIN, "Hasta la vista, baby.");
            put(Weather.FOG, "Everything is all fogged up!");
            put(Weather.SNOW, "GET TO THA CHOPPA!");
        }};
        this.movementOffsets = new HashMap<Weather, Coordinates>() {{
            put(Weather.SUN, new Coordinates(10, 0, 2));
            put(Weather.RAIN, new Coordinates(5, 0, 0));
            put(Weather.FOG, new Coordinates(1, 0, 0));
            put(Weather.SNOW, new Coordinates(0, 0, -12));
        }};
    }

    public void updateConditions() {
        super.updateConditions();
        if (this.coordinates.getHeight() <= 0) {
            this.writer.println(this.getInfo() + ": landing.");
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }
}
