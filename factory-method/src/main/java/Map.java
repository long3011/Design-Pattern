public abstract class Map {
    public Tile[][] tiles;

    public abstract void createTile(int x, int y);

    public void display() {
        for (Tile[] tile : tiles) {
            for (Tile value : tile) {
                System.out.print(value.getCharacter() + " ");
            }
            System.out.println();
        }
    }
}
