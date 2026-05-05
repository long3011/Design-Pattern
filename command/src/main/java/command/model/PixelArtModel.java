package command.model;

public final class PixelArtModel {
    public static final int GRID_SIZE = 8;

    private final boolean[][] pixels = new boolean[GRID_SIZE][GRID_SIZE];
    private int cursorRow;
    private int cursorColumn;

    public int getCursorRow() {
        return cursorRow;
    }

    public int getCursorColumn() {
        return cursorColumn;
    }

    public boolean isPixelOn(int row, int column) {
        validateCoordinates(row, column);
        return pixels[row][column];
    }

    public void setPixel(int row, int column, boolean on) {
        validateCoordinates(row, column);
        pixels[row][column] = on;
    }

    public void moveCursorUp() {
        if (cursorRow > 0) {
            cursorRow--;
        }
    }

    public void moveCursorDown() {
        if (cursorRow < GRID_SIZE - 1) {
            cursorRow++;
        }
    }

    public void moveCursorLeft() {
        if (cursorColumn > 0) {
            cursorColumn--;
        }
    }

    public void moveCursorRight() {
        if (cursorColumn < GRID_SIZE - 1) {
            cursorColumn++;
        }
    }

    public void toggleCurrentPixel() {
        pixels[cursorRow][cursorColumn] = !pixels[cursorRow][cursorColumn];
    }

    public void clear() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                pixels[row][column] = false;
            }
        }
        cursorRow = 0;
        cursorColumn = 0;
    }

    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("int[][] pixelArt = {\n");

        for (int row = 0; row < GRID_SIZE; row++) {
            builder.append("    {");
            for (int column = 0; column < GRID_SIZE; column++) {
                builder.append(pixels[row][column] ? 1 : 0);
                if (column < GRID_SIZE - 1) {
                    builder.append(", ");
                }
            }
            builder.append("}");
            if (row < GRID_SIZE - 1) {
                builder.append(',');
            }
            builder.append('\n');
        }

        builder.append("};");
        return builder.toString();
    }

    private static void validateCoordinates(int row, int column) {
        if (row < 0 || row >= GRID_SIZE || column < 0 || column >= GRID_SIZE) {
            throw new IndexOutOfBoundsException(
                    "Coordinates must be within the 8x8 grid: row=" + row + ", column=" + column
            );
        }
    }
}

