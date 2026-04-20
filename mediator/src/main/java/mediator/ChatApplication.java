package mediator;

import javafx.application.Application;
import javafx.stage.Stage;
import mediator.domain.ChatMediator;
import mediator.domain.ChatRoomMediator;
import mediator.ui.ChatWindowController;

import java.util.ArrayList;
import java.util.List;

public class ChatApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        ChatMediator mediator = new ChatRoomMediator();

        List<ChatWindowController> controllers = new ArrayList<>();
        controllers.add(new ChatWindowController("Alice", mediator));
        controllers.add(new ChatWindowController("Bob", mediator));
        controllers.add(new ChatWindowController("Carol", mediator));

        for (ChatWindowController controller : controllers) {
            mediator.registerClient(controller);
        }

        List<String> usernames = mediator.getRegisteredUsernames();

        controllers.get(0).show(primaryStage, usernames);
        for (int i = 1; i < controllers.size(); i++) {
            controllers.get(i).show(new Stage(), usernames);
        }
    }
}

