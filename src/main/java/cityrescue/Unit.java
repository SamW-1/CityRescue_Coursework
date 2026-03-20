package cityrescue;

import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitStatus;


abstract class Unit {
    private static int totalID = 1;
    private static Unit[] unitList = new Unit[100];
    private final int unitID;
    public UnitStatus status;
    protected final int ticksAtScene;
    public void move() {
        // Add implementation for unit movement
    }
    public Unit(int ticksAtScene) {
        unitID = totalID;
        totalID++;
        status = UnitStatus.IDLE;
        this.ticksAtScene = ticksAtScene;
    }
    public abstract boolean canHandle(IncidentType type);
    public abstract int getTicksToResolve(int severity);
    public int getID() { return unitID; }
    public static void addUnit(Unit unit) { unitList[totalID-1] = unit; }
    public static boolean isUnit(int ID) {
        for (Unit unit : unitList) {
            if (unit.getID() == ID) { return true; }
        }
        return false;
    }
    public static Unit getUnit(int ID) {
        for (Unit unit : unitList) {
            if (unit.getID() == ID) { return unit; }
        }
        return null;
    }
}