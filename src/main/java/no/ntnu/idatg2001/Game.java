package no.ntnu.idatg2001;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.goals.Goal;
import no.ntnu.idatg2001.units.Player;

public class Game {
    private Player player;
    private Story story;
    private List<Goal> goals;


    public Game(Player player, Story story, List<Goal> goals){
        this.player = player;
        this.story = story;
        this.goals = new ArrayList<>();
    }
}
