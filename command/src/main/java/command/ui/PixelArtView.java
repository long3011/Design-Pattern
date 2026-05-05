package command.ui;

import command.model.PixelArtModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public final class PixelArtView extends GridPane {
    private static final double CELL_SIZE = 38.0;
    private static final Color PIXEL_ON_COLOR = Color.web("#2ecc71");
    private static final Color PIXEL_OFF_COLOR = Color.web("#f5f5f5");
    private static final Color GRID_LINE_COLOR = Color.web("#9e9e9e");
    private static final Color CURSOR_COLOR = Color.web("#f39c12");

    private final Rectangle[][] cells = new Rectangle[PixelArtModel.GRID_SIZE][PixelArtModel.GRID_SIZE];

    public PixelArtView(PixelArtModel model) {
        setAlignment(Pos.CENTER);
        setHgap(2);
        setVgap(2);
        setPadding(new Insets(12));

        for (int row = 0; row < PixelArtModel.GRID_SIZE; row++) {
            for (int column = 0; column < PixelArtModel.GRID_SIZE; column++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                cell.setArcWidth(6);
                cell.setArcHeight(6);
                cell.setStrokeWidth(1.5);
                cells[row][column] = cell;
                add(cell, column, row);
            }
        }

        refresh(model);
    }

    public void refresh(PixelArtModel model) {
        for (int row = 0; row < PixelArtModel.GRID_SIZE; row++) {
            for (int column = 0; column < PixelArtModel.GRID_SIZE; column++) {
                Rectangle cell = cells[row][column];
                boolean on = model.isPixelOn(row, column);
                boolean cursor = row == model.getCursorRow() && column == model.getCursorColumn();
                cell.setFill(on ? PIXEL_ON_COLOR : PIXEL_OFF_COLOR);
                cell.setStroke(cursor ? CURSOR_COLOR : GRID_LINE_COLOR);
                cell.setStrokeWidth(cursor ? 3.0 : 1.5);
            }
        }
    }
}

