package no.ntnu.idatg2001.paths.model.items.equipables.armor;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.items.equipables.Equipable;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The Armor class represents an armor in the game. It extends the Item class and implements the
 * equipable interface.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Armor extends Equipable {

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "armor_attributes_id")
  private Attributes attributes;

  /**
   * Constructor for the Armor class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   * @param attributes the attributes of the item
   */
  protected Armor(String name, int itemScore, int goldValue, Attributes attributes) {
    super(name, itemScore, goldValue);
    this.attributes = attributes;
  }

  protected Armor() {}

  /**
   * Gets the attributes of the armor.
   *
   * @return the attributes of the armor
   */
  protected Attributes getAttributes() {
    return attributes;
  }

  /**
   * Sets the attributes of the armor.
   *
   * @param attributes the attributes of the armor
   */
  protected void setAttributes(Attributes attributes) {
    this.attributes = attributes;
  }

  /**
   * Adds attributes to the armor.
   *
   * @param attributes the attributes to add
   */
  protected void addAttributes(Attributes attributes) {
    this.attributes.addAttributes(attributes);
  }

  @Override
  public void equip(Player player) {
    player.getInventory().remove(this);
    player.getEquippedItems().add(this);
    player.getAttributes().addAttributes(this.attributes);
  }

  /**
   * Uneqiups the item from the player.
   *
   * @param player the player who is unequipping the item
   */
  @Override
  public void unEquip(Player player) {
    player.getEquippedItems().remove(this);
    player.getInventory().add(this);
    player.getAttributes().subtractAttributes(this.attributes);
  }
}
