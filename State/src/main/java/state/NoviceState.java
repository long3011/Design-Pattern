package state;

import java.util.List;

public final class NoviceState extends BaseRankState {

    @Override
    public Rank rank() {
        return Rank.NOVICE;
    }

    @Override
    public List<Action> availableActions(GameCharacter character) {
        return List.of(Action.TRAIN, Action.STATUS, Action.HELP, Action.EXIT);
    }

    @Override
    public String train(GameCharacter character) {
        int before = character.experiencePoints();
        character.addXp(ProgressionRules.TRAIN_XP_NOVICE);
        int gained = character.experiencePoints() - before;
        return "You train hard. XP +" + gained + ".";
    }
}
