package command.commands;

import command.model.PixelArtModel;

public final class MoveCursorDownCommand implements EditorCommand {
    private final PixelArtModel model;

    public MoveCursorDownCommand(PixelArtModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.moveCursorDown();
    }
}

