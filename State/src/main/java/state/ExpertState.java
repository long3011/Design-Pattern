package state;

import java.util.List;

public final class ExpertState extends BaseRankState {

    @Override
    public Rank rank() {
        return Rank.EXPERT;
    }

    @Override
    public List<Action> availableActions(GameCharacter character) {
        return List.of(Action.TRAIN, Action.MEDITATE, Action.FIGHT, Action.STATUS, Action.HELP, Action.EXIT);
    }

    @Override
    public String train(GameCharacter character) {
        int before = character.experiencePoints();
        character.addXp(ProgressionRules.TRAIN_XP_EXPERT);
        int gained = character.experiencePoints() - before;
        return "You refine advanced techniques. XP +" + gained + ".";
    }

    @Override
    public String meditate(GameCharacter character) {
        int before = character.healthPoints();
        character.heal(ProgressionRules.MEDITATE_HP_EXPERT);
        int gained = character.healthPoints() - before;
        return "You center yourself. HP +" + gained + ".";
    }

    @Override
    public String fight(GameCharacter character) {
        if (!character.isAlive()) {
            return "You are too weak to fight. Meditate first.";
        }

        int hpBefore = character.healthPoints();
        character.takeDamage(ProgressionRules.FIGHT_HP_COST_EXPERT);
        int hpLost = hpBefore - character.healthPoints();

        int xpBefore = character.experiencePoints();
        character.addXp(ProgressionRules.FIGHT_XP_GAIN_EXPERT);
        int xpGained = character.experiencePoints() - xpBefore;

        if (!character.isAlive()) {
            return "You win, but collapse from exhaustion. HP -" + hpLost + ", XP +" + xpGained + ".";
        }
        return "You fight a tough enemy. HP -" + hpLost + ", XP +" + xpGained + ".";
    }
}
