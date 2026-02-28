package state;

import java.util.List;


public interface RankState {

    Rank rank();

    List<Action> availableActions(GameCharacter character);

    String train(GameCharacter character);

    String meditate(GameCharacter character);

    String fight(GameCharacter character);
}
