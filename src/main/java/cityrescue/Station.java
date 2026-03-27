package cityrescue;

public class Station {
    public static int totalID = 1;
    public static final int MAX_STATIONS = 20;
    private static Station[] stationList = new Station[MAX_STATIONS];
    private int stationID;
    private String name;
    private Unit[] units;
    private int[] location;
    public int unitsCount = 0;
    public Station(String name, int x, int y) {
        this.name = name;
        stationID = totalID;
        totalID++;
        location[0] = x;
        location[1] = y;
    }
    public int getStationID() { return stationID; }
    public static boolean isStation(int id) { 
        for (Station st : stationList) {
            if (st.getStationID() == id) { return true; }
        }    
        return false;
    }
    public static void addStation(Station station) { stationList[totalID-1] = station; }
    public boolean hasUnits() { 
        if (unitsCount == 0) { return false; }
        return true;
    }
    public static Station getStation(int id) { 
        for (Station st : stationList) {
            if (st.getStationID() == id) { return st; }
        }
        return null;
    }
    public static Station getStation(Unit unit) { 
        for (Station st : stationList) {
            if (st.hasUnit(unit)) { return st; }
        }
        return null;
    }
    public static int[] getStationIDs() { 
        int[] IDs = new int[totalID-1];
        for (int i = 0; i < totalID-1; i++) {
            IDs[i] = stationList[i].getStationID();
        }
        return IDs;
    }
    public int[] getLocation() { return location; }
    public void setStationCapacity(int cap) {
        // Assume station capacity is only set once at the start since units will be set to empty Arr
        units = new Unit[cap];
    }
    public boolean hasCapacity() {
        if (unitsCount < units.length-1) { return true; }
        return false;
    }
    public boolean hasUnit(Unit unit) {
        for (Unit elem : units) {
            if (elem == unit) { return true; }
        }
        return false;
    }
    public void addUnit(Unit unit) {
        units[unitsCount] = unit;
        unitsCount++;
    }
    public void removeUnit(Unit unit) {
        for (Unit i : units) {
            if (i == unit) { i = null; }
        }

        for (int i = 0; i < units.length-1; i++) {
            if (units[i] == null) {
                if (units[i+1] != null) {
                    units[i] = units[i+1];
                    units[i+1] = null;
                }
                else {
                    break;
                }
            }
        }
    }
}