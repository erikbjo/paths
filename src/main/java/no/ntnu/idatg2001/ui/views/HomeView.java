package no.ntnu.idatg2001.ui.views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeView extends Application {

    public static void mainApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        VBox storiesVBox = new VBox();
        VBox deadLinksVBox = new VBox();
        HBox middleHBox = new HBox();

        storiesVBox.setSpacing(10);
        deadLinksVBox.setSpacing(10);
        middleHBox.setSpacing(10);

        Text pathsGameText = new Text("Paths Game");
        Text storiesText = new Text("Stories");
        Text deadLinksText = new Text("Dead links");

    pathsGameText.setFont(Font.font("Comic sans", 50));

        TextArea storiesTextArea = new TextArea("storiesTextArea");
        TextArea deadLinksTextArea = new TextArea("deadLinksTextArea");

        storiesTextArea.setEditable(false);
        deadLinksTextArea.setEditable(false);

        storiesVBox.getChildren().add(storiesText);
        storiesVBox.getChildren().add(storiesTextArea);

        deadLinksVBox.getChildren().add(deadLinksText);
        deadLinksVBox.getChildren().add(deadLinksTextArea);

        middleHBox.getChildren().add(storiesVBox);
        middleHBox.getChildren().add(deadLinksVBox);

        Region fillerRegion = new Region();

        Button startNewGameButton = new Button("Start new game");
        startNewGameButton.setOnAction(event -> {
            // Launch the Player Information View in a new window
            PlayerInformationView playerInfoView = new PlayerInformationView();
            playerInfoView.start(primaryStage);
        });

        gridPane.add(pathsGameText, 0,0);
        gridPane.add(middleHBox,0,1);
        gridPane.add(startNewGameButton,0,2);

    gridPane.setAlignment(Pos.CENTER);

        anchorPane.getChildren().add(gridPane);
        AnchorPane.setTopAnchor(gridPane, 10.0);
        AnchorPane.setLeftAnchor(gridPane, 10.0);
        AnchorPane.setRightAnchor(gridPane, 10.0);
        AnchorPane.setBottomAnchor(gridPane, 10.0);

        Scene scene = new Scene(anchorPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

