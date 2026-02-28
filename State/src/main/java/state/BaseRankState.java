package state;

import java.util.List;

abstract class BaseRankState implements RankState {

    @Override
    public String meditate(GameCharacter character) {
        return "You can't meditate at the " + rank() + " rank.";
    }

    @Override
    public String fight(GameCharacter character) {
        return "You can't fight at the " + rank() + " rank.";
    }

    protected static String actionsLine(List<Action> actions) {
        return actions.stream().map(Action::command).reduce((a, b) -> a + ", " + b).orElse("");
    }
}
