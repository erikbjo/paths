package no.ntnu.idatg2001.paths.ui.storyvisualizers;

import java.util.Optional;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.ui.dialogs.EditPassageDialog;

public class PassagePane extends Region {

  private Passage passage;

  public PassagePane(Passage passage) {
    this.passage = passage;

    Rectangle rectangle = new Rectangle(150, 100, Color.LIGHTGRAY);
    rectangle.setStroke(Color.BLACK);

    Text title = new Text(passage.getTitle());
    title.setWrappingWidth(140);
    title.setLayoutX(10);
    title.setLayoutY(20);

    Text content = new Text(passage.getContent());
    content.setWrappingWidth(140);
    content.setLayoutX(10);
    content.setLayoutY(40);

    getChildren().addAll(rectangle, title);

    this.setOnMouseEntered(
        mouseEvent -> {
          rectangle.setStrokeWidth(3);
          rectangle.setStroke(Color.BLUE);
        });

    this.setOnMouseExited(
        mouseEvent -> {
          rectangle.setStrokeWidth(1);
          rectangle.setStroke(Color.BLACK);
        });

    this.setOnMouseClicked(
        mouseEvent -> {
          EditPassageDialog editPassageDialog = new EditPassageDialog(passage);

          Optional<Passage> result = editPassageDialog.showAndWait();
          result.ifPresent(
              passage1 -> {
                this.passage = passage1;
                title.setText(passage.getTitle());
                content.setText(passage.getContent());
              });
        });
  }

  public Passage getPassage() {
    return passage;
  }
}
