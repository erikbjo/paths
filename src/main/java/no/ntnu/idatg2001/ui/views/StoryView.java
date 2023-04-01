package no.ntnu.idatg2001.ui.views;

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

public class StoryView extends Application {
  public static void mainApp(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
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

    leftVBox.getChildren().add(storyHeadlineText);
    leftVBox.getChildren().add(storyTextArea);
    leftAnchorPane.getChildren().add(leftVBox);

    TextArea pathOneTextArea = new TextArea("path1");
    TextArea pathTwoTextArea = new TextArea("path2");

    pathOneTextArea.setMaxWidth(200);
    pathTwoTextArea.setMaxWidth(200);

    topRightAnchorPane.getChildren().add(pathOneTextArea);
    bottumRightAnchorPane.getChildren().add(pathTwoTextArea);

    
    

    Scene scene = new Scene(bottumAnchorPane, 600, 600);
    scene.getStylesheets().add("cssfiles/storyStyle.css");
    stage.setScene(scene);
    stage.show();
  }
}
