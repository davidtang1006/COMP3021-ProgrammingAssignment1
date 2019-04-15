package pa1.technologies;


import pa1.Cost;

public class WarTech extends Technology {

    /**
     * @return attack bonus equal to 1 + level * 0.5
     */
    @Override
    public double getAttackBonus() {
        // TODO
        return (1 + getLevel() * 0.5);
    }

    /**
     * Upgrade costs:
     * gold = science = (current level + 1) * 1000;
     * production = 0
     *
     * @return upgrade costs
     */
    @Override
    public Cost getUpgradeCost() {
        // TODO
        int gold = (getLevel() + 1) * 1000;
        int science = (getLevel() + 1) * 1000;
        return new Cost(gold, 0, science);
    }

    /**
     * Example string representation:
     * "WarTech | level: 1 | attack bonus: 1.50"
     *
     * @return String representing this object
     */
    @Override
    public String toString() {
        // TODO
        return String.format("WarTech | level: %d | attack bonus: %.2f", getLevel(), getAttackBonus());
    }
}