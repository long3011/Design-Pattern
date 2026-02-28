package state;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public final class Game {

    private final Scanner in;
    private final PrintStream out;

    public Game(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void run() {
        out.println("=== State Pattern: Character Progression ===");
        out.print("Enter character name: ");
        String name = readNonBlankLine("Hero");
        GameCharacter character = new GameCharacter(name);

        out.println();
        out.println("Type 'help' to see commands.");

        while (true) {
            out.println();
            out.println(character.statusLine());
            out.println("Available actions: " + character.state().availableActions(character).stream().map(Action::command).toList());
            out.print("> ");

            String input = in.nextLine();
            Action action = Action.fromCommand(input);
            if (action == null) {
                out.println("Unknown command. Type 'help'.");
                continue;
            }

            switch (action) {
                case TRAIN -> out.println(character.train());
                case MEDITATE -> out.println(character.meditate());
                case FIGHT -> out.println(character.fight());
                case STATUS -> out.println(character.statusLine());
                case HELP -> printHelp();
                case EXIT -> {
                    out.println("Bye.");
                    return;
                }
            }

            if (character.rank() == Rank.MASTER) {
                out.println();
                out.println(character.statusLine());
                out.println("You reached MASTER. The game ends.");
                return;
            }
        }
    }

    private String readNonBlankLine(String fallback) {
        String line = in.nextLine();
        if (line == null) return fallback;
        String trimmed = line.trim();
        return trimmed.isEmpty() ? fallback : trimmed;
    }

    private void printHelp() {
        out.println("Commands:");
        out.println("  train     - gain experience (always available)");
        out.println("  meditate  - regain health (unlocks at INTERMEDIATE)");
        out.println("  fight     - lose health, gain lots of XP (unlocks at EXPERT)");
        out.println("  status    - show your current stats");
        out.println("  help      - show this help");
        out.println("  exit      - quit");
    }
}
