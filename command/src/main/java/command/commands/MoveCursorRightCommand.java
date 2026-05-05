package command.commands;

import command.model.PixelArtModel;

public final class MoveCursorRightCommand implements EditorCommand {
    private final PixelArtModel model;

    public MoveCursorRightCommand(PixelArtModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.moveCursorRight();
    }
}

