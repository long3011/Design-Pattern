package template;

import java.io.InputStream;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * A console Mastermind-style game.
 *
 * <p>Each player has a limited number of guesses to break a secret numeric code.
 * A guess yields a hint (green = correct digit+position, yellow = correct digit wrong position).
 *
 * <p>Winning: first player to guess wins immediately.
 * Losing: if everyone runs out of turns, the player with the best (green, then yellow) wins.
 */
public final class CodeBreakerGame extends Game {

    public static final int DEFAULT_CODE_LENGTH = 4;
    public static final int DEFAULT_MAX_TURNS = 8;

    private final Scanner scanner;
    private final PrintStream out;
    private final Random random;
    
    private int codeLength;

    private final List<PlayerState> players = new ArrayList<>();
    private String secret;

    private boolean finished;
    private int winnerIndex = -1;

    public CodeBreakerGame(InputStream in, PrintStream out) {
        this(in, out, new SecureRandom());
    }

    public CodeBreakerGame(InputStream in, PrintStream out, Random random) {
        this.scanner = new Scanner(Objects.requireNonNull(in, "in"));
        this.out = Objects.requireNonNull(out, "out");
        this.random = Objects.requireNonNull(random, "random");
    }

    @Override
    public void initializeGame(int numberOfPlayers) {
        this.codeLength = DEFAULT_CODE_LENGTH;
        int maxTurns = DEFAULT_MAX_TURNS;

        out.println("=== Code Breaker (Template Method Demo) ===");
        out.println("Rules: guess the " + codeLength + "-digit secret code.");
        out.println("Hint format: Green = right digit in right place; Yellow = right digit wrong place.");
        out.println("Turns per player: " + maxTurns);
        out.println();

        players.clear();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new PlayerState("Player " + (i + 1), maxTurns));
        }

        secret = randomDigits(codeLength);
        finished = false;
        winnerIndex = -1;
    }

    @Override
    public boolean endOfGame() {
        if (finished) return true;

        // end if all players have exhausted turns
        for (PlayerState p : players) {
            if (p.remainingTurns > 0) return false;
        }

        finished = true;
        return true;
    }

    @Override
    public void playSingleTurn(int player) {
        if (finished) return;

        PlayerState p = players.get(player);
        if (p.remainingTurns <= 0) {
            // skip players who are out of turns
            return;
        }

        out.println();
        out.println("--- " + p.name + " ---");
        out.println("Turns left: " + p.remainingTurns);

        String guess = readGuess(codeLength);
        p.remainingTurns--;

        Hint hint = score(secret, guess);
        p.bestHint = Hint.max(p.bestHint, hint);

        out.println("Guess: " + guess + " -> Green=" + hint.green + ", Yellow=" + hint.yellow);

        if (hint.green == codeLength) {
            finished = true;
            winnerIndex = player;
            out.println(p.name + " cracked the code!");
            return;
        }

        if (endOfGame()) {
            // if that was the last meaningful turn, announce later in displayWinner()
            return;
        }

        out.println("Next player's turn...");
    }

    @Override
    public void displayWinner() {
        out.println();
        out.println("=== Game Over ===");
        if (winnerIndex >= 0) {
            out.println("Winner: " + players.get(winnerIndex).name);
            out.println("Secret was: " + secret);
            return;
        }

        // No one guessed; pick best by (green, yellow), tie -> earliest player.
        int best = 0;
        for (int i = 1; i < players.size(); i++) {
            if (players.get(i).bestHint.compareTo(players.get(best).bestHint) > 0) {
                best = i;
            }
        }

        out.println("No one cracked the code.");
        out.println("Best performance: " + players.get(best).name + " (best green="
                + players.get(best).bestHint.green + ", yellow=" + players.get(best).bestHint.yellow + ")");
        out.println("Secret was: " + secret);
    }

    private String readGuess(int length) {
        while (true) {
            out.print("Enter your guess (" + length + " digits), or 'quit': ");
            if (!scanner.hasNextLine()) {
                // Treat EOF as quitting immediately.
                finished = true;
                return "0".repeat(length);
            }
            String raw = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if (raw.equals("quit") || raw.equals("q")) {
                finished = true;
                return "0".repeat(length);
            }
            if (raw.length() != length) {
                out.println("Invalid length. Expected exactly " + length + " digits.");
                continue;
            }
            boolean allDigits = true;
            for (int i = 0; i < raw.length(); i++) {
                if (!Character.isDigit(raw.charAt(i))) {
                    allDigits = false;
                    break;
                }
            }
            if (!allDigits) {
                out.println("Invalid input. Use only digits 0-9.");
                continue;
            }
            return raw;
        }
    }

    private String randomDigits(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    static Hint score(String secret, String guess) {
        if (secret.length() != guess.length()) {
            throw new IllegalArgumentException("secret and guess must have same length");
        }

        int green = 0;
        int[] secretCount = new int[10];
        int[] guessCount = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                green++;
            } else {
                secretCount[s - '0']++;
                guessCount[g - '0']++;
            }
        }

        int yellow = 0;
        for (int d = 0; d < 10; d++) {
            yellow += Math.min(secretCount[d], guessCount[d]);
        }

        return new Hint(green, yellow);
    }

    private static final class PlayerState {
        final String name;
        int remainingTurns;
        Hint bestHint = new Hint(0, 0);

        PlayerState(String name, int remainingTurns) {
            this.name = name;
            this.remainingTurns = remainingTurns;
        }
    }

    static final class Hint implements Comparable<Hint> {
        final int green;
        final int yellow;

        Hint(int green, int yellow) {
            this.green = green;
            this.yellow = yellow;
        }

        @Override
        public int compareTo(Hint other) {
            int c = Integer.compare(this.green, other.green);
            if (c != 0) return c;
            return Integer.compare(this.yellow, other.yellow);
        }

        static Hint max(Hint a, Hint b) {
            return a.compareTo(b) >= 0 ? a : b;
        }
    }
}

