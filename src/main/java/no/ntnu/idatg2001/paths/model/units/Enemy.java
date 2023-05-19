package no.ntnu.idatg2001.paths.model.units;

/**
 * The Unit class represents an enemy in the game. It extends the Unit class.
 *
 * @see Unit
 * @see EnemyBuilder
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
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

  /** {@inheritDoc} */
  public static class EnemyBuilder extends UnitBuilder<EnemyBuilder> {
    @Override
    public Enemy build() {
      return new Enemy(this);
    }
  }
}
