package pa1.ministers;


import pa1.City;
import pa1.Cost;
import pa1.Player;
import pa1.exceptions.TooPoorException;
import pa1.technologies.Technology;

import java.util.List;

public class WarGeneral extends Minister {

    /**
     * Calls the superclass' constructor
     *
     * @param intelligence
     * @param experience
     * @param leadership
     */
    public WarGeneral(int intelligence, int experience, int leadership) {
        // TODO
    }

    /**
     * @param city to collect production points from
     * @return 1.5 times the amount collected by other types of minister
     */
    @Override
    public int collectProductionPoints(City city) {
        // TODO
    }

    /**
     * Recruits more troops than superclass implementation,
     * troops added to city = 50 + round(leadership * 0.2).
     * <p>
     * Recruitment still costs 500 golds. Throw an exception
     * when player does not have enough gold.
     *
     * @param player
     * @param city
     * @throws TooPoorException
     */
    @Override
    public void recruitTroops(Player player, City city) throws TooPoorException {
        // TODO
    }


    /**
     * WarGeneral gets attack bonus when attacking a city.
     * bonus_multiplier = 1 + (intelligence + experience + leadership) / 1500
     * <p>
     * Attacking city loses troops equal to min(# of troops attacking, # of troops in the defending city)
     * Defending city loses round(bonus_multiplier * # of attacking troops * product of tech attack bonuses)
     * <p>
     * "[attacker city name] loses [number of troops lost by attacker] troops while attacking"
     * "[defender city name] loses [number of troops lost by defender] troops while defending"
     *
     * @param attacker       attacking city
     * @param defender       defending city
     * @param troops         number of troops deployed for the attack
     * @param technologyList
     */
    @Override
    public void attackCity(City attacker, City defender, int troops, List<Technology> technologyList) {
        // TODO
    }

    /**
     * Example string representation:
     * "WarGeneral | intelligence: 100 | experience: 100 | leadership: 100 | READY" - when isReady() == true
     * "WarGeneral | intelligence: 100 | experience: 100 | leadership: 100 | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
    }
}
