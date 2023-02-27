package no.ntnu.idatg2001.goals;

import no.ntnu.idatg2001.units.Player;

/**
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
public class HealthGoal implements Goal
{
    private int minimumHealth;

    public HealthGoal(int minimumHealth){
        this.minimumHealth = minimumHealth;
    }

    @Override
    public boolean isFulfilled(Player player){
        minimumHealth = player.getHealth();
        if (minimumHealth > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
