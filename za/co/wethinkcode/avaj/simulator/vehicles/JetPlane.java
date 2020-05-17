package za.co.wethinkcode.avaj.simulator.vehicles;

import java.util.HashMap;

import za.co.wethinkcode.avaj.simulator.WeatherTower;
import za.co.wethinkcode.avaj.weather.Coordinates;
import za.co.wethinkcode.avaj.weather.WeatherProvider.Weather;

public class JetPlane extends Aircraft implements Flyable {
    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.className = "JetPlane";

        this.messages = new HashMap<Weather, String>() {{
            put(Weather.SUN, "I can see the enemy from a mile away.");
            put(Weather.RAIN, "I'm getting wet!");
            put(Weather.FOG, "Let's hope the radars still work.");
            put(Weather.SNOW, "Brace for impact!");
        }};
        this.movementOffsets = new HashMap<Weather, Coordinates>() {{
            put(Weather.SUN, new Coordinates(0, 10, 2));
            put(Weather.RAIN, new Coordinates(0, 5, 0));
            put(Weather.FOG, new Coordinates(0, 1, 0));
            put(Weather.SNOW, new Coordinates(0, 0, -7));
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
