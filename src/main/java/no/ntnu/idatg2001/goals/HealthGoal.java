package no.ntnu.idatg2001.goals;

import no.ntnu.idatg2001.Player;
import no.ntnu.idatg2001.goals.Goal;

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
        if (player.getHealth() > 0){
            this.minimumHealth = minimumHealth;
            return true;
        }
        else {
            return false;
        }
    }
}
