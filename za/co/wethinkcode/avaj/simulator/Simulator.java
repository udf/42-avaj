package za.co.wethinkcode.avaj.simulator;

import java.io.*;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import za.co.wethinkcode.avaj.simulator.vehicles.AircraftFactory;
import za.co.wethinkcode.avaj.simulator.vehicles.Flyable;

public class Simulator {
    private static int cycles;
    private static final Pattern lineRegex = Pattern.compile("(\\S+) (\\S+) (\\d+) (\\d+) (\\d+)");
    private static AircraftFactory aircraftFactory = new AircraftFactory();
    private static WeatherTower weatherTower = new WeatherTower();
    private static PrintWriter writer;

    private static void ParseLine(String line, int lineNo) {
        if (lineNo == 1) {
            cycles = Integer.parseInt(line);
            if (cycles < 0) {
                throw new NumberFormatException("The number of cycles must be greater than 0");
            }
            return;
        }

        Matcher m = lineRegex.matcher(line);
        if (!m.find()) {
            throw new InputMismatchException("Line does not match required format");
        }
        Flyable aircraft = aircraftFactory.newAircraft(
            m.group(1),
            m.group(2),
            Integer.parseInt(m.group(3)),
            Integer.parseInt(m.group(4)),
            Integer.parseInt(m.group(5))
        );
        aircraft.registerWriter(writer);
        aircraft.registerTower(weatherTower);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Expected filepath as only argument!");
            System.exit(1);
        }
        if (args.length > 1) {
            System.err.println("Too many arguments! Did you forget to put your filepath in quotes?");
            System.exit(1);
        }

        // Open output file
        try {
            writer = new PrintWriter(new File("simulation.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening output file: " + e.getMessage());
            System.exit(2);
        }

        int lineNo = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
            for (String line; (line = reader.readLine()) != null; ) {
                ParseLine(line, lineNo);
                lineNo++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file: " + e.getMessage());
            System.exit(2);
        } catch (Exception e) {
            System.err.println("Error on line" + lineNo + ": " + e.getMessage());
            System.exit(3);
        }

        for (int i = 0; i < cycles; i++) {
            weatherTower.changeWeather();
        }

        writer.close();
    }
}