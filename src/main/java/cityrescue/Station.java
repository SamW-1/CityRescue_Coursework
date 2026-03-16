package cityrescue;
public class Station {
    static int totalID = 1;
    private int stationID;
    private String name;
    public Station(String name) {
        this.name = name;
        stationID = totalID;
        totalID++;
    }
    public int getStationID() { return stationID; }
}