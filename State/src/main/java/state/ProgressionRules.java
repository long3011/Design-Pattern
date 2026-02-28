package state;

/**
 * Central place to tune the game's numbers.
 */
public final class ProgressionRules {

    private ProgressionRules() {
    }

    // XP thresholds (inclusive) at which the character is promoted.
    public static final int XP_FOR_INTERMEDIATE = 100;
    public static final int XP_FOR_EXPERT = 250;
    public static final int XP_FOR_MASTER = 500;

    // Training rewards
    public static final int TRAIN_XP_NOVICE = 25;
    public static final int TRAIN_XP_INTERMEDIATE = 20;
    public static final int TRAIN_XP_EXPERT = 15;

    // Meditate rewards
    public static final int MEDITATE_HP_INTERMEDIATE = 15;
    public static final int MEDITATE_HP_EXPERT = 10;

    // Fight costs/rewards
    public static final int FIGHT_HP_COST_EXPERT = 20;
    public static final int FIGHT_XP_GAIN_EXPERT = 50;

    public static final int MAX_HP = 100;
}
