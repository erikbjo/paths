package no.ntnu.idatg2001.items.consumables;

import java.lang.reflect.Field;
import no.ntnu.idatg2001.items.AttributeItem;
import no.ntnu.idatg2001.units.Attributes;
import no.ntnu.idatg2001.units.Player;

public class AttributePotion extends Potion implements AttributeItem {
  private int strength;
  private int perception;
  private int endurance;
  private int charisma;
  private int intelligence;
  private int agility;
  private int luck;

  public AttributePotion(int strength, int perception, int endurance,
                         int charisma, int intelligence, int agility, int luck) {
    this.strength = strength;
    this.perception = perception;
    this.endurance = endurance;
    this.charisma = charisma;
    this.intelligence = intelligence;
    this.agility = agility;
    this.luck = luck;
  }

  @Override
  public void use(Player player) {
    Attributes attributes = player.getAttributes();
    Field[] fields = this.getClass().getDeclaredFields();
    for (Field field : fields) {

    }
  }
}
