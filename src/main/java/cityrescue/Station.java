package cityrescue;
public class Station {
    static int totalID = 1;
    public static Station[] stations = new Station[100];
    public static int stationsCount = 0;
    private int stationID;
    private String name;
    private Unit[] units = new Unit[100];
    private int unitsCount = 0;
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
}