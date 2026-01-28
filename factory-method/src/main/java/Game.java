public class Game {
    public Map map;
    public String type = "City"; // Change to "Wilderness" to create a wilderness map

    public void createMap() {
        switch(type) {
            case "City":
                map = new CityMap();
                break;
            case "Wilderness":
                map = new WildernessMap();
                break;
            default:
                throw new IllegalArgumentException("Unknown map type: " + type);
        }
        map.tiles = new Tile[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map.createTile(i, j);
            }
        }
    }

    public static   void main(String[] args) {
        Game game = new Game();
        game.createMap();
        game.map.display();
    }
}
