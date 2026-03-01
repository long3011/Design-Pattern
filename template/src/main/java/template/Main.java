package template;

import java.util.Scanner;

public final class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many players? (1-4)");
        int n = readInt(scanner, 1, 4);

        Game game = new CodeBreakerGame(System.in, System.out);
        game.play(n);
    }

    private static int readInt(Scanner scanner, int min, int max) {
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                return min;
            }
            String line = scanner.nextLine().trim();
            try {
                int n = Integer.parseInt(line);
                if (n < min || n > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                    continue;
                }
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number.");
            }
        }
    }
}

