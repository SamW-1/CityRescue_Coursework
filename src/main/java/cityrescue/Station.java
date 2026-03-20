package cityrescue;
public class Station {
    static int totalID = 1;
    public static Station[] stations = new Station[100];
    public static int stationsCount = 0;
    private int stationID;
    private String name;
    private Unit[] units;
    public int unitsCount = 0;
    public Station(String name) {
        this.name = name;
        stationID = totalID;
        totalID++;
    }
    public int getStationID() { return stationID; }
    public static boolean isStation(int id) { 
        for (Station st : stations) {
            if (st.getStationID() == id) { return true; }
        }    
        return false;
    }
    public boolean hasUnits() { 
        if (unitsCount == 0) { return false; }
        return true;
    }
    public static Station getStation(int id) { 
        for (Station st : stations) {
            if (st.getStationID() == id) { return st; }
        }
        return stations[99];
    }
    public static int[] getStationIDs() { 
        int[] IDs = new int[stationsCount];
        for (int i = 0; i < stationsCount; i++) {
            IDs[i] = stations[i].getStationID();
        }
        return IDs;
    }
    public void setStationCapacity(int cap) {
        // Assume station capacity is only set once at the start since units will be set to empty Arr
        units = new Unit[cap];
    }
    public boolean hasCapacity() {
        if (unitsCount < units.length-1) { return true; }
        return false;
    }
    public void addUnit(Unit unit) {
        units[unitsCount] = unit;
        unitsCount++;
    }
}