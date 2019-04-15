package pa1.technologies;

import pa1.Cost;

public class ManufacturingTech extends Technology {

    /**
     * @return production bonus equal to 1 + current level * 0.5
     */
    @Override
    public double getProductionBonus() {
        // TODO
        return (1 + getLevel() * 0.5);
    }

    /**
     * Upgrade costs:
     * gold = production = (current level + 1) * 1000;
     * science = 0
     *
     * @return upgrade costs
     */
    @Override
    public Cost getUpgradeCost() {
        // TODO
        int gold = (getLevel() + 1) * 1000;
        int production = (getLevel() + 1) * 1000;
        return new Cost(gold, production, 0);
    }

    /**
     * Example string representation:
     * "ManufacturingTech | level: 1 | production bonus: 1.50"
     *
     * @return String representing this object
     */
    @Override
    public String toString() {
        // TODO
        return String.format("ManufacturingTech | level: %d | production bonus: %.2f",
                getLevel(), getProductionBonus());
    }
}