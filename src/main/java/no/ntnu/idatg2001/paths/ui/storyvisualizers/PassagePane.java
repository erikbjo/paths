package no.ntnu.idatg2001.paths.ui.storyvisualizers;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import no.ntnu.idatg2001.paths.model.Passage;

public class PassagePane extends Region {

  private final Text title;
  private final Text content;
  private Passage passage;

  public PassagePane(Passage passage) {
    this.passage = passage;

    Rectangle rectangle = new Rectangle(150, 100, Color.LIGHTGRAY);
    rectangle.setStroke(Color.BLACK);

    title = new Text(passage.getTitle());
    title.setWrappingWidth(140);
    title.setLayoutX(10);
    title.setLayoutY(20);

    content = new Text(passage.getContent());
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
  }

  public Text getTitle() {
    return title;
  }

  public Text getContent() {
    return content;
  }

  public Passage getPassage() {
    return passage;
  }

  public void setPassage(Passage passage) {
    this.passage = passage;
  }
}
