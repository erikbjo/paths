package no.ntnu.idatg2001.goals;

import no.ntnu.idatg2001.goals.Goal;
import no.ntnu.idatg2001.units.Player;

/**
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
public class ScoreGoal implements Goal
{
    private int minimumPoints;

    public ScoreGoal(int minimumPoints){
        this.minimumPoints = minimumPoints;
    }

    @Override
    public boolean isFulfilled(Player player)
    {
        minimumPoints = player.getScore();
        if (minimumPoints > 0){
            return true;
        }
        else
        {
            return false;
        }
    }
}
