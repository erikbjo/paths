package no.ntnu.idatg2001.goals;

import no.ntnu.idatg2001.units.Player;

/**
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
public class GoldGoal implements Goal
{
    private int minimumGold;

    public GoldGoal(int minimumGold){
        this.minimumGold = minimumGold;
    }

    public boolean isFulfilled(Player player)
    {
        if (player. > 0)
        {
            return true;
        } else
        {
            return false;
        }
    }
}
