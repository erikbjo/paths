package no.ntnu.idatg2001.paths.ui.storyvisualizers;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Arrow extends Polygon {
  private static final double[] ARROW_SHAPE = new double[] {0, 0, 10, 5, 0, 10, 1, 5};

  public Arrow(double x, double y, double angle) {
    super(ARROW_SHAPE);
    setTranslateX(x);
    setTranslateY(y);
    setRotate(Math.toDegrees(angle));
  }
}
