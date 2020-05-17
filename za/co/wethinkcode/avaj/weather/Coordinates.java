package za.co.wethinkcode.avaj.weather;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        if (height > 100)
            height = 100;
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }

    public Coordinates add(Coordinates other) {
        return new Coordinates(
            this.longitude + other.longitude,
            this.latitude + other.latitude,
            this.height + other.height
        );
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %d", this.longitude, this.latitude, this.height);
    }
}
