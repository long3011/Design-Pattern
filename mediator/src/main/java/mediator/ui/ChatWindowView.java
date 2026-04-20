package mediator.ui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ChatWindowView {
    private final TextArea conversationArea = new TextArea();
    private final ComboBox<String> recipientSelector = new ComboBox<>();
    private final TextField messageInput = new TextField();
    private final Button sendButton = new Button("Send");
    private final VBox root;

    public ChatWindowView() {
        conversationArea.setEditable(false);
        conversationArea.setWrapText(true);
        conversationArea.setPromptText("Messages appear here...");

        recipientSelector.setPromptText("Recipient");
        recipientSelector.setPrefWidth(120);

        messageInput.setPromptText("Type a message");
        HBox.setHgrow(messageInput, Priority.ALWAYS);

        HBox controls = new HBox(8, recipientSelector, messageInput, sendButton);
        root = new VBox(10, conversationArea, controls);
        root.setPadding(new Insets(12));
        VBox.setVgrow(conversationArea, Priority.ALWAYS);
    }

    public Parent rootNode() {
        return root;
    }

    public TextArea conversationArea() {
        return conversationArea;
    }

    public ComboBox<String> recipientSelector() {
        return recipientSelector;
    }

    public TextField messageInput() {
        return messageInput;
    }

    public Button sendButton() {
        return sendButton;
    }
}

