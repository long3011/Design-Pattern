# Template Method Pattern — Console Game (Code Breaker)

This module demonstrates the **Template Method** design pattern using a simple console game.

## Pattern mapping

- `Game` is the **framework**.
  - `Game#play(int)` is the **template method** (fixed algorithm).
  - `initializeGame`, `playSingleTurn`, `endOfGame`, `displayWinner` are the **hooks/steps**.
- `CodeBreakerGame` is a **concrete game** that implements the steps.

## Game rules

- Each player tries to guess a secret **4-digit** code.
- Each guess returns a hint:
  - **bulls**: correct digit in correct position
  - **cows**: correct digit but in wrong position
- Each player gets **8 turns**.
- If a player guesses the code exactly, they win immediately.
- If nobody guesses, the player who achieved the best hint (more bulls, then more cows) wins.

## How to run

From the repository root:

```powershell
mvn -pl template -am test
mvn -pl template -am exec:java -Dexec.mainClass=template.Main
```

If `exec:java` isn’t configured in your Maven setup, you can run via your IDE by running `template.Main`.

## Controls

- Enter a 4-digit number (e.g., `0123`) when prompted.
- Type `quit` to end the game early.

