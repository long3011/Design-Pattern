package command;

import command.commands.EditorCommand;
import command.commands.GenerateCodeCommand;
import command.commands.MoveCursorDownCommand;
import command.commands.MoveCursorLeftCommand;
import command.commands.MoveCursorRightCommand;
import command.commands.MoveCursorUpCommand;
import command.commands.TogglePixelCommand;
import command.model.PixelArtModel;
import command.ui.PixelArtView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class PixelArtApplication extends Application {
    private final PixelArtModel model = new PixelArtModel();
    private final PixelArtView view = new PixelArtView(model);
    private final Label statusLabel = new Label();
    private final TextArea codeOutputArea = new TextArea();

    @Override
    public void start(Stage stage) {
        Label instructions = new Label("Use the arrow keys to move the cursor, press Space to toggle a pixel, and click Create Code to print Java code for the current 8x8 grid.");
        instructions.setWrapText(true);

        Button createCodeButton = new Button("Create Code");
        createCodeButton.setOnAction(event -> {
            GenerateCodeCommand command = new GenerateCodeCommand(model);
            command.execute();
            codeOutputArea.setText(command.getGeneratedCode());
        });

        codeOutputArea.setEditable(false);
        codeOutputArea.setWrapText(false);
        codeOutputArea.setPrefRowCount(10);
        codeOutputArea.setPromptText("Generated Java code will appear here after you click Create Code.");
        codeOutputArea.setStyle("-fx-font-family: 'Consolas';");

        VBox controls = new VBox(10, instructions, statusLabel, createCodeButton, codeOutputArea);
        controls.setAlignment(Pos.CENTER_LEFT);
        controls.setPadding(new Insets(12));

        BorderPane root = new BorderPane();
        root.setCenter(view);
        root.setBottom(controls);
        root.setPadding(new Insets(12));
        root.setFocusTraversable(true);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            EditorCommand command = mapKeyToCommand(event.getCode());
            if (command != null) {
                command.execute();
                refreshUi();
            }
        });
        scene.setOnMouseClicked(event -> root.requestFocus());

        stage.setTitle("Command Pattern Pixel Art Editor");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        refreshUi();
        Platform.runLater(root::requestFocus);
    }

    private EditorCommand mapKeyToCommand(KeyCode keyCode) {
        return switch (keyCode) {
            case UP -> new MoveCursorUpCommand(model);
            case DOWN -> new MoveCursorDownCommand(model);
            case LEFT -> new MoveCursorLeftCommand(model);
            case RIGHT -> new MoveCursorRightCommand(model);
            case SPACE -> new TogglePixelCommand(model);
            default -> null;
        };
    }

    private void refreshUi() {
        view.refresh(model);
        statusLabel.setText(String.format(
                "Cursor: row %d, column %d  |  Pixel is %s",
                model.getCursorRow() + 1,
                model.getCursorColumn() + 1,
                model.isPixelOn(model.getCursorRow(), model.getCursorColumn()) ? "on" : "off"
        ));
    }
}

