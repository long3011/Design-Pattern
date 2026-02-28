package state;

/**
 * Player-facing actions.
 */
public enum Action {
    TRAIN("train"),
    MEDITATE("meditate"),
    FIGHT("fight"),
    STATUS("status"),
    HELP("help"),
    EXIT("exit");

    private final String command;

    Action(String command) {
        this.command = command;
    }

    public String command() {
        return command;
    }

    public static Action fromCommand(String input) {
        if (input == null) return null;
        String normalized = input.trim().toLowerCase();
        for (Action a : values()) {
            if (a.command.equals(normalized)) return a;
        }
        return null;
    }
}
