package cityrescue;
public class Station {
    static int totalID = 1;
    static Station[] stations = new Station[100];
    static int stationsCount = 0;
    private int stationID;
    private String name;
    public Station(String name) {
        this.name = name;
        stationID = totalID;
        totalID++;
    }
    public int getStationID() { return stationID; }
}