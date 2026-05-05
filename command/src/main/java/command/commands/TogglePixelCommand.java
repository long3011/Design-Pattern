package command.commands;

import command.model.PixelArtModel;

public final class TogglePixelCommand implements EditorCommand {
    private final PixelArtModel model;

    public TogglePixelCommand(PixelArtModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.toggleCurrentPixel();
    }
}

