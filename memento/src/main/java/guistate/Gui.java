package guistate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.util.List;

public class Gui extends Application {

    // create a GUI with three adjacent ColorBoxes and one CheckBox below them
    private Controller controller;
    private ColorBox colorBox1;
    private ColorBox colorBox2;
    private ColorBox colorBox3;
    private CheckBox checkBox;

    // History window
    private ListView<IMemento> historyListView;
    private boolean suppressHistorySelectionEvent = false;

    public void start(Stage stage) {

        controller = new Controller(this);

        // Insets for margin and padding
        Insets insets = new Insets(10, 10, 10, 10);

        // Create three ColorBoxes
        colorBox1 = new ColorBox(1, controller);
        colorBox2 = new ColorBox(2, controller);
        colorBox3 = new ColorBox(3, controller);

        // Create a CheckBox
        checkBox = new CheckBox("Click me!");
        checkBox.setPadding(insets);

        // Add the ColorBoxes and CheckBox to a HBox
        HBox hBox = new HBox(colorBox1.getRectangle(), colorBox2.getRectangle(), colorBox3.getRectangle());
        hBox.setSpacing(10);

        hBox.setMargin(colorBox1.getRectangle(), insets);
        hBox.setMargin(colorBox2.getRectangle(), insets);
        hBox.setMargin(colorBox3.getRectangle(), insets);


        Label label = new Label("Press Ctrl-Z to undo the last change.");
        label.setPadding(insets);

        // create a VBox that contains the HBox and the CheckBox
        VBox vBox = new VBox(hBox, checkBox, label);
        // call controller when the CheckBox is clicked
        checkBox.setOnAction(event -> {
            controller.setIsSelected(checkBox.isSelected());
        });

        // Set the HBox to be the root of the Scene
        Scene scene = new Scene(vBox);
        scene.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.Z) {
                controller.undo();
            } else if (event.isControlDown() && event.getCode() == KeyCode.Y) {
                controller.redo();
            }
        });


        stage.setScene(scene);
        stage.setTitle("Memento Pattern Example");
        stage.show();

        createHistoryWindow();
        updateHistoryView(controller.getTimeline());
    }

    public void updateGui() {
        // called after restoring state from a Memento
        colorBox1.setColor(controller.getOption(1));
        colorBox2.setColor(controller.getOption(2));
        colorBox3.setColor(controller.getOption(3));
        checkBox.setSelected(controller.getIsSelected());
    }

    public void updateHistoryView(List<IMemento> timeline) {
        if (historyListView == null) {
            return;
        }

        suppressHistorySelectionEvent = true;
        historyListView.setItems(FXCollections.observableArrayList(timeline));
        if (!timeline.isEmpty()) {
            historyListView.getSelectionModel().select(timeline.size() - 1);
            historyListView.scrollTo(timeline.size() - 1);
        }
        suppressHistorySelectionEvent = false;
    }

    private void createHistoryWindow() {
        Stage historyStage = new Stage();
        historyStage.setTitle("State History");

        historyListView = new ListView<>();
        historyListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            if (suppressHistorySelectionEvent) {
                return;
            }
            int index = newIndex == null ? -1 : newIndex.intValue();
            if (index >= 0) {
                controller.restoreFromHistoryIndex(index);
            }
        });

        VBox root = new VBox(new Label("Click a state to restore it:"), historyListView);
        root.setSpacing(8);
        root.setPadding(new Insets(10));

        historyStage.setScene(new Scene(root, 500, 320));
        historyStage.show();
    }
}
