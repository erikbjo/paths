package no.ntnu.idatg2001.model.actions.items.consumables;

import no.ntnu.idatg2001.model.units.Attributes;
import no.ntnu.idatg2001.model.units.Player;

public class AttributePotion extends Potion implements Consumable {
  private Attributes attributes;

  public AttributePotion(String name, int itemScore, int goldValue, Attributes attributes) {
    super(name, itemScore, goldValue);
    this.attributes = attributes;
  }

  @Override
  public void use(Player player) {
    player.getAttributes().addAttributes(attributes);
  }
}
