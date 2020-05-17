package za.co.wethinkcode.avaj.simulator.vehicles;

import java.io.PrintWriter;
import java.util.HashMap;

import za.co.wethinkcode.avaj.weather.Coordinates;
import za.co.wethinkcode.avaj.simulator.WeatherTower;
import za.co.wethinkcode.avaj.weather.WeatherProvider.Weather;

public abstract class Aircraft {
    private static long idCounter = 0;
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected WeatherTower weatherTower;

    protected HashMap<Weather, String> messages = new HashMap<Weather, String>() {{
        put(Weather.SUN, "SUN message");
        put(Weather.RAIN, "RAIN message");
        put(Weather.FOG, "FOG message");
        put(Weather.SNOW, "SNOW message");
    }};
    protected HashMap<Weather, Coordinates> movementOffsets = new HashMap<Weather, Coordinates>() {{
        put(Weather.SUN, new Coordinates(0, 0, 0));
        put(Weather.RAIN, new Coordinates(0, 0, 0));
        put(Weather.FOG, new Coordinates(0, 0, 0));
        put(Weather.SNOW, new Coordinates(0, 0, 0));
    }};

    protected String className;
    protected PrintWriter writer;
    private static PrintWriter stdoutWriter = new PrintWriter(System.out, true);

    protected Aircraft(String name, Coordinates coordinates) {
        if (coordinates.getLongitude() < 0) {
            throw new IllegalArgumentException("Longitude is less than 0");
        }
        if (coordinates.getLatitude() < 0) {
            throw new IllegalArgumentException("Latitude is less than 0");
        }
        this.id = this.nextId();
        this.name = name;
        this.coordinates = coordinates;
        this.writer = stdoutWriter;
        this.className = "Aircraft";
    }

    private long nextId() {
        return ++Aircraft.idCounter;
    }

    public void updateConditions() {
        Weather weather = Weather.valueOf(weatherTower.getWeather(this.coordinates));
        Coordinates offset = this.movementOffsets.get(weather);

        this.coordinates = this.coordinates.add(offset);
        this.writer.println(this.getInfo() + ": " + messages.get(weather));
    }

    public void registerWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public String getInfo() {
        return this.className + "#" + this.name + "(" + this.id + ")";
    }

    public PrintWriter getWriter() {
        return this.writer;
    }
}
