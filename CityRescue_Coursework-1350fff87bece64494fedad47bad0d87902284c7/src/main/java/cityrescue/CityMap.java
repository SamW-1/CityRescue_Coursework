package cityrescue;

public class CityMap {
    private String[][] map;
    private int width, height;
    public CityMap(int width, int height) {
        map = new String[height][width];
        this.width = width;
        this.height = height;
    }
    public int getWidth() { return width; }
    public int getHeight() { return width; }
    public void addObstacle(int x, int y) {
        map[y][x] = "obstacle";
    }
    public void removeObstacle(int x, int y) {
        map[y][x] = null;
    }
    public void addStation(Station station, int x, int y) {
        map[y][x] = Integer.toString(station.getStationID());
    }
    public boolean locationBlocked(int x, int y) {
        if (map[y][x] == null) { return false; }
        return true;
    }
    public void removeStation(int stationID) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j].equals(Integer.toString(stationID))) { map[i][j] = null; }
            }
        }
    }
}