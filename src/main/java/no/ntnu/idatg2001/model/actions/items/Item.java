package no.ntnu.idatg2001.model.actions.items;

public abstract class Item {
  // Information
  protected String name;
  protected int itemScore;
  protected int goldValue;

  protected Item(String name, int itemScore, int goldValue) {
    this.name = name;
    this.itemScore = itemScore;
    this.goldValue = goldValue;
  }
}
