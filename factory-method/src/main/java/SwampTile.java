public class SwampTile implements Tile{
    @Override
    public char getCharacter() {
        return 'S';
    }

    @Override
    public String getType() {
        return "Swamp";
    }

    @Override
    public String action() {
        return "You are in the swamp!";
    }
}
