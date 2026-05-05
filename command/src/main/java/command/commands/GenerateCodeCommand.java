package command.commands;

import command.model.PixelArtModel;

public final class GenerateCodeCommand implements EditorCommand {
    private final PixelArtModel model;
    private String generatedCode = "";

    public GenerateCodeCommand(PixelArtModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        generatedCode = model.generateJavaCode();
        System.out.println(generatedCode);
    }

    public String getGeneratedCode() {
        return generatedCode;
    }
}

