package cityrescue;

import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitStatus;


abstract class Unit {
    private static int totalIDs = 1;
    private final int unitID;
    public UnitStatus status;
    protected final int ticksAtScene;
    public void move() {
        // Add implementation for unit movement
    }
    public Unit(int ticksAtScene) {
        unitID = totalIDs;
        totalIDs++;
        status = UnitStatus.IDLE;
        this.ticksAtScene = ticksAtScene;
    }
    public abstract boolean canHandle(IncidentType type);
    public abstract int getTicksToResolve(int severity);
    public int getID() { return unitID; }
}