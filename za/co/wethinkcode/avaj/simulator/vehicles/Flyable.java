package za.co.wethinkcode.avaj.simulator.vehicles;

import java.io.PrintWriter;

import za.co.wethinkcode.avaj.simulator.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);

    void registerWriter(PrintWriter writer);
    String getInfo();
    PrintWriter getWriter();
}
