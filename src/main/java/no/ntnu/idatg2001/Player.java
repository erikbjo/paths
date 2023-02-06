package no.ntnu.idatg2001;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.03
 */
public class Player
{
    private String name;
    private int health;
    private int score;
    private int gold;
    private List<String> inventory;

    /**
     *
     * @param name
     * @param health
     * @param score
     * @param gold
     */
    public Player(String name, int health, int score, int gold){
        this.name = name;
        this.health = health;
        this.score = score;
        this.gold = gold;
        this.inventory = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @param health
     */
    public void addHealth(int health){
        if (health > 0){
            this.health = health;
        }

    }

    /**
     *
     * @return
     */
    public int getHealth()
    {
        return health;
    }

    /**
     *
     * @param points
     */
    public void addScore(int points){

    }

    /**
     *
     * @return
     */
    public int getScore()
    {
        return score;
    }

    /**
     *
     * @param gold
     */
    public boolean addGold(int gold){
        if(gold >= 0){
            this.gold = gold;
            return true;
        }
        else {
            return false;
        }

    }

    /**
     *
     * @return
     */
    public int getGold()
    {
        return gold;
    }

    /**
     *
     * @param item
     */
    public void addToInventory(String item){
        inventory.add(item);
    }

    /**
     *
     * @return
     */
   /** public List<String> getInventory(){
        inventory.get();
    }*/
}
