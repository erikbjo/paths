package no.ntnu.idatg2001.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.model.Database;

public class StoryView extends Application {
  private ResourceBundle resources;
  public static void mainApp(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    // Observes when the language in Database is changed, then calls updateLanguage()
    Database.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
            ResourceBundle.getBundle(
                    "story",
                    Locale.forLanguageTag(Database.getCurrentLanguage().getLocalName()));
    
    AnchorPane bottumAnchorPane = new AnchorPane();

    SplitPane rootHorizontalSplitPane = new SplitPane();
    AnchorPane leftAnchorPane = new AnchorPane();
    AnchorPane rightAnchorPane = new AnchorPane();

    rootHorizontalSplitPane.getItems().add(leftAnchorPane);
    rootHorizontalSplitPane.getItems().add(rightAnchorPane);

    rootHorizontalSplitPane.setOrientation(Orientation.HORIZONTAL);
    rootHorizontalSplitPane.setDividerPosition(0,0.5);
    
    bottumAnchorPane.getChildren().add(rootHorizontalSplitPane);

    SplitPane verticalSplitPane = new SplitPane();
    AnchorPane topRightAnchorPane = new AnchorPane();
    AnchorPane bottumRightAnchorPane = new AnchorPane();
    verticalSplitPane.getItems().add(topRightAnchorPane);
    verticalSplitPane.getItems().add(bottumRightAnchorPane);
    verticalSplitPane.setOrientation(Orientation.VERTICAL);

    rightAnchorPane.getChildren().add(verticalSplitPane);

    VBox leftVBox = new VBox();
    leftVBox.setAlignment(Pos.CENTER);
    leftVBox.setPrefWidth(200);
    Text storyHeadlineText = new Text("storyHeadlineText");
    TextArea storyTextArea = new TextArea("storyTextArea");
    storyTextArea.setEditable(false);

    leftVBox.getChildren().add(storyHeadlineText);
    leftVBox.getChildren().add(storyTextArea);
    leftAnchorPane.getChildren().add(leftVBox);

    TextArea pathOneTextArea = new TextArea("path1");
    TextArea pathTwoTextArea = new TextArea("path2");

    pathOneTextArea.setEditable(false);
    pathTwoTextArea.setEditable(false);

    pathOneTextArea.setMaxWidth(200);
    pathTwoTextArea.setMaxWidth(200);

    topRightAnchorPane.getChildren().add(pathOneTextArea);
    bottumRightAnchorPane.getChildren().add(pathTwoTextArea);

    
    

    Scene scene = new Scene(bottumAnchorPane, 600, 600);
    scene.getStylesheets().add("cssfiles/storyStyle.css");
    stage.setScene(scene);
    stage.show();
  }

  public void updateLanguage() {
    // update resources
    resources =
        ResourceBundle.getBundle(
            "story",
            Locale.forLanguageTag(Database.getCurrentLanguage().getLocalName()));
    }
}
