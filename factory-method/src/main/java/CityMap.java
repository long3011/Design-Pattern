public class CityMap extends Map {
    @Override
    public void createTile(int x, int y) {
        int type = (int)(Math.random() * 3);
        switch (type) {
            case 0:
                tiles[x][y] = new RoadTile();
                break;
            case 1:
                tiles[x][y] = new BuildingTile();
                break;
            case 2:
                tiles[x][y] = new ForestTile();
                break;
            default:
                throw new IllegalArgumentException("Unknown tile type: " + type);
        }
    };
}
