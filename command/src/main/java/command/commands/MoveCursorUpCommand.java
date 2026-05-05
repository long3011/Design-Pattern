package command.commands;

import command.model.PixelArtModel;

public final class MoveCursorUpCommand implements EditorCommand {
    private final PixelArtModel model;

    public MoveCursorUpCommand(PixelArtModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.moveCursorUp();
    }
}

