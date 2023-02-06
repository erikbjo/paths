package no.ntnu.idatg2001.goals;

import no.ntnu.idatg2001.Player;
import no.ntnu.idatg2001.goals.Goal;

public class GoldGoal implements Goal
{

    private int minimumGold;

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
        if (player.getGold() > 0)
        {
            return true;
        } else
        {
            return false;
        }
    }
}
