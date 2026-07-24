package cityrescue;

import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;


abstract class Unit {
    public static int totalID = 1;
    private static int unitCount = 0;
    public static final int MAX_UNITS = 50;
    private static Unit[] unitList = new Unit[MAX_UNITS];
    private int[] location;
    private final int unitID;
    public UnitStatus status;
    protected final int ticksAtScene;
    public void move() {
        // Add implementation for unit movement
    }
    public Unit(int ticksAtScene, int[] location) {
        unitID = totalID;
        totalID++;
        unitCount++;
        status = UnitStatus.IDLE;
        this.ticksAtScene = ticksAtScene;
        this.location = location;
    }
    public abstract boolean canHandle(IncidentType type);
    public abstract int getTicksToResolve(int severity);
    public abstract UnitType getUnitType();
    public int getID() { return unitID; }
    public int[] getLocation() { return location; }
    public static int[] getUnitIDs() {
        int[] IDs = new int[unitCount];
        int count = 0;
        for (int i = 0; i < totalID; i++) {
            if (unitList[i].status != UnitStatus.OUT_OF_SERVICE) { 
                IDs[count] = unitList[i].getID(); 
                count++;
            }
        }
        return IDs;
    }
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
    public static void removeUnit() {
        unitCount -= 1;
    }
}