package template;

/**
 * Template Method framework for turn-based games.
 *
 * <p>The algorithm is fixed in {@link #play(int)}. Subclasses implement the steps.
 */
public abstract class Game {

    public final void play(int numberOfPlayers) {
        initializeGame(numberOfPlayers);
        int playerInTurn = 0;
        while (!endOfGame()) {
            playSingleTurn(playerInTurn);
            playerInTurn = ++playerInTurn % numberOfPlayers;
        }
        displayWinner();
    }

    public abstract void initializeGame(int numberOfPlayers);

    public abstract boolean endOfGame();

    public abstract void playSingleTurn(int player);

    public abstract void displayWinner();
}

