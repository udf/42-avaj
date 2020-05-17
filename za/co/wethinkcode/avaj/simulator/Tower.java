package za.co.wethinkcode.avaj.simulator;
import za.co.wethinkcode.avaj.simulator.vehicles.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        if (observers.contains(flyable))
            return;
        observers.add(flyable);
        flyable.getWriter().println("Tower says: " + flyable.getInfo() + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
        flyable.getWriter().println("Tower says: " + flyable.getInfo() + " unregistered from weather tower.");
    }

    protected void conditionChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}
