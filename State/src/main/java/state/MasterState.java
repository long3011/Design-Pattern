package state;

import java.util.List;

public final class MasterState extends BaseRankState {

    @Override
    public Rank rank() {
        return Rank.MASTER;
    }

    @Override
    public List<Action> availableActions(GameCharacter character) {
        return List.of(Action.STATUS, Action.EXIT);
    }

    @Override
    public String train(GameCharacter character) {
        return "You have mastered the path. There is nothing left to train.";
    }

    @Override
    public String meditate(GameCharacter character) {
        return "Your mind is perfectly calm already.";
    }

    @Override
    public String fight(GameCharacter character) {
        return "No opponent remains worthy.";
    }
}
