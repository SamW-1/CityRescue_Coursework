package cityrescue;

import cityrescue.enums.IncidentType;

abstract class Unit {
    private static int totalIDs = 1;
    private final int unitID;
    public void move() {
        // Add implementation for unit movement
    }
    public Unit() {
        unitID = totalIDs;
        totalIDs++;
    }
    public abstract boolean canHandle(IncidentType type);
    public abstract int getTicksToResolve(int severity);
}