package no.ntnu.idatg2001.paths.ui.storyvisualizers;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import javafx.scene.Group;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.ui.dialogs.EditLinkDialog;

public class LinkLine extends Line {

  private static final String ARROW = "M 0 0 L 1 0.5 L 0 1 z";

  private final Text linkText;
  private final Group arrows;
  private Link link;

  public LinkLine(Link link, PassagePane startPane, PassagePane endPane) {
    this.link = link;
    this.arrows = new Group();

    startXProperty().bind(startPane.layoutXProperty().add(startPane.widthProperty().divide(2)));
    startYProperty().bind(startPane.layoutYProperty().add(startPane.heightProperty().divide(2)));

    endXProperty().bind(endPane.layoutXProperty().add(endPane.widthProperty().divide(2)));
    endYProperty().bind(endPane.layoutYProperty().add(endPane.heightProperty().divide(2)));

    this.linkText = new Text(link.getText());
    setStrokeWidth(3);

    // ADD ARROWS ALONG THE LINE
    double numberOfArrows = 5;
    for (int i = 0; i < numberOfArrows; i++) {
      double t = i / numberOfArrows;
      Arrow arrow =
          new Arrow(0, 0, 0);
      this.arrows.getChildren().add(arrow);

      // Update the arrow's position and angle when the line's properties change
      startXProperty().addListener((observable, oldValue, newValue) -> updateArrow(arrow, t));
      startYProperty().addListener((observable, oldValue, newValue) -> updateArrow(arrow, t));
      endXProperty().addListener((observable, oldValue, newValue) -> updateArrow(arrow, t));
      endYProperty().addListener((observable, oldValue, newValue) -> updateArrow(arrow, t));
    }

    // TODO: ADD STYLE CHANGES FOR USABILITY, LIKE GLOW ON HOVER ETC

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

    this.setOnMouseClicked(
        mouseEvent -> {
          // open edit link dialog
          EditLinkDialog editLinkDialog = new EditLinkDialog(link);

          Optional<Link> result = editLinkDialog.showAndWait();
          result.ifPresent(link1 -> this.link = link1);
        });
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

  public Text getLinkText() {
    return linkText;
  }
}
