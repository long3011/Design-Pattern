package state;

import java.util.List;

public final class IntermediateState extends BaseRankState {

    @Override
    public Rank rank() {
        return Rank.INTERMEDIATE;
    }

    @Override
    public List<Action> availableActions(GameCharacter character) {
        return List.of(Action.TRAIN, Action.MEDITATE, Action.STATUS, Action.HELP, Action.EXIT);
    }

    @Override
    public String train(GameCharacter character) {
        int before = character.experiencePoints();
        character.addXp(ProgressionRules.TRAIN_XP_INTERMEDIATE);
        int gained = character.experiencePoints() - before;
        return "You practice fundamentals. XP +" + gained + ".";
    }

    @Override
    public String meditate(GameCharacter character) {
        int before = character.healthPoints();
        character.heal(ProgressionRules.MEDITATE_HP_INTERMEDIATE);
        int gained = character.healthPoints() - before;
        return "You meditate and recover. HP +" + gained + ".";
    }
}
