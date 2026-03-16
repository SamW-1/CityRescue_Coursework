package cityrescue;

public class CityMap {
    private String[][] map;
    private int width;
    private int height;
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
}