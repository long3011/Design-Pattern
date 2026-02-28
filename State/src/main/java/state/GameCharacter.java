package state;

import java.util.Objects;


public final class GameCharacter {

    private final String name;
    private int level; // A simple numeric representation that follows the rank.
    private int experiencePoints;
    private int healthPoints;

    private RankState state;

    public GameCharacter(String name) {
        this(name, 1, 0, ProgressionRules.MAX_HP);
    }

    public GameCharacter(String name, int level, int experiencePoints, int healthPoints) {
        this.name = name;
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        this.level = Math.max(1, level);
        this.experiencePoints = Math.max(0, experiencePoints);
        this.healthPoints = clamp(healthPoints, 0, ProgressionRules.MAX_HP);
        this.state = new NoviceState();
        promoteIfNeeded();
    }

    public String name() {
        return name;
    }

    public int level() {
        return level;
    }

    public int experiencePoints() {
        return experiencePoints;
    }

    public int healthPoints() {
        return healthPoints;
    }

    public Rank rank() {
        return state.rank();
    }

    public RankState state() {
        return state;
    }

    void setState(RankState state) {
        this.state = Objects.requireNonNull(state, "state");
    }

    public String train() {
        return state.train(this);
    }

    public String meditate() {
        return state.meditate(this);
    }

    public String fight() {
        return state.fight(this);
    }

    public void addXp(int amount) {
        if (amount <= 0) return;
        experiencePoints += amount;
        promoteIfNeeded();
    }

    public void heal(int amount) {
        if (amount <= 0) return;
        healthPoints = clamp(healthPoints + amount, 0, ProgressionRules.MAX_HP);
    }

    public void takeDamage(int amount) {
        if (amount <= 0) return;
        healthPoints = clamp(healthPoints - amount, 0, ProgressionRules.MAX_HP);
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public void promoteIfNeeded() {
        if (state.rank() == Rank.MASTER) {
            return;
        }

        Rank nextRank = computeRankFromXp(experiencePoints);
        if (nextRank == state.rank()) {
            return;
        }

        switch (nextRank) {
            case NOVICE -> setState(new NoviceState());
            case INTERMEDIATE -> setState(new IntermediateState());
            case EXPERT -> setState(new ExpertState());
            case MASTER -> setState(new MasterState());
        }
        this.level = switch (nextRank) {
            case NOVICE -> 1;
            case INTERMEDIATE -> 2;
            case EXPERT -> 3;
            case MASTER -> 4;
        };
    }

    private static Rank computeRankFromXp(int xp) {
        if (xp >= ProgressionRules.XP_FOR_MASTER) return Rank.MASTER;
        if (xp >= ProgressionRules.XP_FOR_EXPERT) return Rank.EXPERT;
        if (xp >= ProgressionRules.XP_FOR_INTERMEDIATE) return Rank.INTERMEDIATE;
        return Rank.NOVICE;
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public String statusLine() {
        return String.format("Name: %s | Rank: %s | Level: %d | XP: %d | HP: %d/%d",
                name,
                rank(),
                level,
                experiencePoints,
                healthPoints,
                ProgressionRules.MAX_HP
        );
    }
}
