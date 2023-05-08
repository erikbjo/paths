package no.ntnu.idatg2001.paths.ui.storyvisualizers;

import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import no.ntnu.idatg2001.paths.model.Link;

public class LinkLine extends Line {

  private final Link link;
  private final Text linkText;

  public LinkLine(Link link, PassagePane startPane, PassagePane endPane) {
    this.link = link;

    startXProperty().bind(startPane.layoutXProperty().add(startPane.widthProperty().divide(2)));
    startYProperty().bind(startPane.layoutYProperty().add(startPane.heightProperty().divide(2)));
    endXProperty().bind(endPane.layoutXProperty().add(endPane.widthProperty().divide(2)));
    endYProperty().bind(endPane.layoutYProperty().add(endPane.heightProperty().divide(2)));

    this.linkText = new Text(link.getText());

    setStrokeWidth(2);

    // TODO: ADD STYLE CHANGES FOR USABILITY, LIKE GLOW ON HOVER ETC

    this.setOnMouseEntered(
            mouseEvent -> {
              setStrokeWidth(10);
            }
    );

    this.setOnMouseExited(
            mouseEvent -> setStrokeWidth(2)
    );
  }

  public Link getLink() {
    return link;
  }

  public Text getLinkText() {
    return linkText;
  }
}
