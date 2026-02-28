package state;

import java.util.List;

/**
 * State interface for the character's rank.
 *
 * Contract:
 * - Each action returns a human-readable message describing what happened.
 * - Implementations may mutate the {@link GameCharacter} (xp/hp) and trigger rank transitions.
 * - {@link #availableActions(GameCharacter)} must reflect what the player can do in the current state.
 */
public interface RankState {

    Rank rank();

    List<Action> availableActions(GameCharacter character);

    String train(GameCharacter character);

    String meditate(GameCharacter character);

    String fight(GameCharacter character);
}
