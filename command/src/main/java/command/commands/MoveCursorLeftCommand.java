package command.commands;

import command.model.PixelArtModel;

public final class MoveCursorLeftCommand implements EditorCommand {
    private final PixelArtModel model;

    public MoveCursorLeftCommand(PixelArtModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.moveCursorLeft();
    }
}

