package no.ntnu.idatg2001.model.units;

/** The Unit class represents an enemy in the game. */
public class Enemy extends Unit {

  /**
   * Instantiates a new Enemy.
   *
   * @param builder the builder
   */
  private Enemy(EnemyBuilder builder) {
    super(builder);
  }

  /** {@inheritDoc} */
  @Override
  public String dialog() {
    return "Hello, my name is " + super.getName();
  }

  public static class EnemyBuilder extends UnitBuilder<EnemyBuilder> {
    @Override
    public Enemy build() {
      return new Enemy(this);
    }
  }
}
