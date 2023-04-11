package no.ntnu.idatg2001.model.units;

public class Enemy extends Unit {

  private Enemy(EnemyBuilder builder) {
    super(builder);
  }

  @Override
  public void dialog() {}

  public static class EnemyBuilder extends UnitBuilder<EnemyBuilder> {
    @Override
    public Enemy build() {
      return new Enemy(this);
    }
  }
}
