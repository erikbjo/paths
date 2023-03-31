package no.ntnu.idatg2001.ui.views;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StoryView extends Application {
  public static void mainApp(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    AnchorPane bottumAnchorPane = new AnchorPane();

    SplitPane rootVerticalSplitPane = new SplitPane();
    AnchorPane leftAnchorPane = new AnchorPane();
    AnchorPane rightAnchorPane = new AnchorPane();
    rootVerticalSplitPane.getItems().add(leftAnchorPane);
    rootVerticalSplitPane.getItems().add(rightAnchorPane);
    rootVerticalSplitPane.setOrientation(Orientation.VERTICAL);
    
    bottumAnchorPane.getChildren().add(rootVerticalSplitPane);

    SplitPane horizontalSplitPane = new SplitPane();
    AnchorPane topRightAnchorPane = new AnchorPane();
    AnchorPane bottumRightAnchorPane = new AnchorPane();
    horizontalSplitPane.getItems().add(topRightAnchorPane);
    horizontalSplitPane.getItems().add(bottumRightAnchorPane);
    horizontalSplitPane.setOrientation(Orientation.HORIZONTAL);

    rightAnchorPane.getChildren().add(horizontalSplitPane);

    VBox leftHBox = new VBox();
    Text storyHeadlineText = new Text("storyHeadlineText");
    TextArea storyTextArea = new TextArea("storyTextArea");

    leftHBox.getChildren().add(storyHeadlineText);
    leftHBox.getChildren().add(storyTextArea);
    leftAnchorPane.getChildren().add(leftHBox);

    TextArea pathOneTextArea = new TextArea("path1");
    TextArea pathTwoTextArea = new TextArea("path2");

    topRightAnchorPane.getChildren().add(pathOneTextArea);
    bottumRightAnchorPane.getChildren().add(pathTwoTextArea);

    
    

    Scene scene = new Scene(bottumAnchorPane, 600, 600);
    scene.getStylesheets().add("cssfiles/test.css");
    stage.setScene(scene);
    stage.show();
  }
}
