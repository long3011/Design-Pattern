package mediator.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import mediator.domain.ChatClient;
import mediator.domain.ChatMediator;
import mediator.domain.ChatMessage;

import java.util.List;
import java.util.Objects;

public class ChatWindowController implements ChatClient {
    private final String username;
    private final ChatMediator mediator;
    private final ChatWindowView view;

    public ChatWindowController(String username, ChatMediator mediator) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }

        this.username = username.trim();
        this.mediator = Objects.requireNonNull(mediator, "mediator must not be null");
        this.view = new ChatWindowView();

        this.view.sendButton().setOnAction(event -> sendMessage());
        this.view.messageInput().setOnAction(event -> sendMessage());
    }

    public void show(Stage stage, List<String> allUsernames) {
        Objects.requireNonNull(stage, "stage must not be null");
        setupRecipients(allUsernames);

        stage.setTitle("Chat - " + username);
        stage.setScene(new Scene(view.rootNode(), 520, 360));
        stage.show();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void receive(ChatMessage message) {
        view.conversationArea().appendText(message.toDisplayLine() + System.lineSeparator());
    }

    private void setupRecipients(List<String> usernames) {
        view.recipientSelector().getItems().setAll(usernames.stream()
                .filter(name -> !username.equals(name))
                .toList());

        if (!view.recipientSelector().getItems().isEmpty()) {
            view.recipientSelector().getSelectionModel().selectFirst();
        }
    }

    private void sendMessage() {
        String recipient = view.recipientSelector().getValue();
        String content = view.messageInput().getText();

        if (recipient == null || recipient.isBlank()) {
            return;
        }

        if (content == null || content.isBlank()) {
            return;
        }

        mediator.sendPrivateMessage(username, recipient, content);
        view.messageInput().clear();
    }
}

