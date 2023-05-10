package no.ntnu.idatg2001.paths.ui.storyvisualizers;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.ui.dialogs.EditLinkDialog;

public class LinkLine extends Line {

  private final Text linkText;
  private Link link;

  public LinkLine(Link link, PassagePane startPane, PassagePane endPane) {
    this.link = link;

    startXProperty().bind(startPane.layoutXProperty().add(startPane.widthProperty().divide(2)));
    startYProperty().bind(startPane.layoutYProperty().add(startPane.heightProperty().divide(2)));

    endXProperty().bind(endPane.layoutXProperty().add(endPane.widthProperty().divide(2)));
    endYProperty().bind(endPane.layoutYProperty().add(endPane.heightProperty().divide(2)));

    this.linkText = new Text(link.getText());

    setStrokeWidth(3);

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

  public Link getLink() {
    return link;
  }

  public Text getLinkText() {
    return linkText;
  }
}
