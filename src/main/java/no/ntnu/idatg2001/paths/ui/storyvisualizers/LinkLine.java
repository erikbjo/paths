package no.ntnu.idatg2001.paths.ui.storyvisualizers;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import no.ntnu.idatg2001.paths.model.Link;

public class LinkLine extends Line {

  private final Text linkText; // might be useless, might be useful for hover text
  private final Group arrows;
  private Link link;
  private PassagePane startPane;
    private PassagePane endPane;

  public LinkLine(Link link, PassagePane startPane, PassagePane endPane) {
    this.link = link;
    this.arrows = new Group();

    startXProperty()
        .bind(startPane.layoutXProperty().add(startPane.widthProperty().divide(2)).add(20));
    startYProperty()
        .bind(startPane.layoutYProperty().add(startPane.heightProperty().divide(2)).subtract(20));

    endXProperty()
        .bind(endPane.layoutXProperty().add(endPane.widthProperty().divide(2)).subtract(20));
    endYProperty().bind(endPane.layoutYProperty().add(endPane.heightProperty().divide(2)).add(20));

    this.linkText = new Text(link.getText());
    setStrokeWidth(3);

    // ADD ARROWS ALONG THE LINE
    double numberOfArrows = 5;
    for (int i = 0; i < numberOfArrows; i++) {
      double t = i / numberOfArrows;
      Arrow arrow = new Arrow(0, 0, 0);
      this.arrows.getChildren().add(arrow);

      // Update the arrow's position and angle when the line's properties change
      startXProperty().addListener((observable, oldValue, newValue) -> updateArrow(arrow, t));
      startYProperty().addListener((observable, oldValue, newValue) -> updateArrow(arrow, t));
      endXProperty().addListener((observable, oldValue, newValue) -> updateArrow(arrow, t));
      endYProperty().addListener((observable, oldValue, newValue) -> updateArrow(arrow, t));
    }


    this.setOnMouseEntered(
        mouseEvent -> {
          setStrokeWidth(10);
          setStyle("-fx-stroke: blue");
        });

    this.setOnMouseExited(
        mouseEvent -> {
          setStrokeWidth(3);
          setStyle("-fx-stroke: black");
        });
  }

  public PassagePane getStartPane() {
    return startPane;
  }

  public PassagePane getEndPane() {
    return endPane;
  }

  private void updateArrow(Arrow arrow, double t) {
    double x = startXProperty().get() * (1 - t) + endXProperty().get() * t;
    double y = startYProperty().get() * (1 - t) + endYProperty().get() * t;
    double angle =
        Math.atan2(
            endYProperty().get() - startYProperty().get(),
            endXProperty().get() - startXProperty().get());
    arrow.setTranslateX(x - 5); // -5 makes it be in the center of the line
    arrow.setTranslateY(y - 5);
    arrow.setRotate(Math.toDegrees(angle));
  }

  public Group getArrows() {
    return this.arrows;
  }

  public Link getLink() {
    return link;
  }

  public void setLink(Link link) {
    this.link = link;
    this.linkText.setText(link.getText());
  }

  public Text getLinkText() {
    return linkText;
  }
}
