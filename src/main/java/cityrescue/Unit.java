package cityrescue;

import cityrescue.enums.IncidentType;

abstract class Unit {
    public void move() {
        // Add implementation for unit movement
    }
    public abstract boolean canHandle(IncidentType type);
    public abstract int getTicksToResolve(int severity);
}