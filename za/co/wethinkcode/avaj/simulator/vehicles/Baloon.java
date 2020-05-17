package za.co.wethinkcode.avaj.simulator.vehicles;

import java.util.HashMap;

import za.co.wethinkcode.avaj.simulator.WeatherTower;
import za.co.wethinkcode.avaj.weather.Coordinates;
import za.co.wethinkcode.avaj.weather.WeatherProvider.Weather;

public class Baloon extends Aircraft implements Flyable {
    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.className = "Baloon";

        this.messages = new HashMap<Weather, String>() {{
            put(Weather.SUN, "Let's enjoy the good weather and take some pics.");
            put(Weather.RAIN, "When did I fly into Britain?");
            put(Weather.FOG, "I can't see a thing! We're going down!");
            put(Weather.SNOW, "Christmas is early this year.");
        }};
        this.movementOffsets = new HashMap<Weather, Coordinates>() {{
            put(Weather.SUN, new Coordinates(2, 0, 4));
            put(Weather.RAIN, new Coordinates(0, 0, -5));
            put(Weather.FOG, new Coordinates(0, 0, -3));
            put(Weather.SNOW, new Coordinates(0, 0, -15));
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
