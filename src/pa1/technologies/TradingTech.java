package pa1.technologies;

import pa1.Cost;

public class TradingTech extends Technology {
    /**
     * @return gold bonus equal to 1 + (level * 0.5);
     */
    @Override
    public double getGoldBonus() {
        // TODO
        return (1 + getLevel() * 0.5);
    }

    /**
     * Upgrade costs:
     * gold = production = science = (current level + 1) * 1000;
     *
     * @return upgrade costs
     */
    @Override
    public Cost getUpgradeCost() {
        // TODO
        int gold = (getLevel() + 1) * 1000;
        int production = (getLevel() + 1) * 1000;
        int science = (getLevel() + 1) * 1000;
        return new Cost(gold, production, science);
    }

    /**
     * Example string representation:
     * "TradingTech | level: 1 | gold bonus: 1.50"
     *
     * @return String representing this object
     */
    @Override
    public String toString() {
        // TODO
        return String.format("TradingTech | level: %d | gold bonus: %.2f", getLevel(), getGoldBonus());
    }
}