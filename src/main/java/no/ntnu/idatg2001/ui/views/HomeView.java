package no.ntnu.idatg2001.ui.views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.ui.controllers.SettingsController;

public class HomeView extends Application {
  private SettingsController settingsController;

  public static void mainApp(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    BorderPane borderPane = new BorderPane();
    borderPane.setTop(createMenuBar());
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
    startNewGameButton.setOnAction(
        event -> {
          // Launch the Player Information View in a new window
          PlayerInformationView playerInfoView = new PlayerInformationView();
          playerInfoView.start(primaryStage);
        });

    Button settingsButton = new Button("Settings");
    settingsButton.setOnAction(
        event -> {
          settingsController = new SettingsController();
        });

    gridPane.add(pathsGameText, 0, 0);
    gridPane.add(middleHBox, 0, 1);
    gridPane.add(startNewGameButton, 0, 2);
    gridPane.add(settingsButton, 0, 3);

    gridPane.setAlignment(Pos.CENTER);

    anchorPane.getChildren().add(gridPane);
    anchorPane.getChildren().add(borderPane);

    AnchorPane.setTopAnchor(gridPane, 10.0);
    AnchorPane.setLeftAnchor(gridPane, 10.0);
    AnchorPane.setRightAnchor(gridPane, 10.0);
    AnchorPane.setBottomAnchor(gridPane, 10.0);

    Scene scene = new Scene(anchorPane, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private MenuBar createMenuBar() {
    // Create menu items
    MenuItem helpItem = new MenuItem("Help");
    helpItem.setOnAction(actionEvent -> onHelp());
    MenuItem aboutItem = new MenuItem("About");
    aboutItem.setOnAction(actionEvent -> onAbout());
    MenuItem settingsItem = new MenuItem("Settings");
    settingsItem.setOnAction(
        event -> {
          settingsController = new SettingsController();
        });
    MenuItem quitItem = new MenuItem("Quit");
    quitItem.setOnAction(actionEvent -> System.exit(0));

    // Create menus and add items
    Menu fileMenu = new Menu("File");
    fileMenu.getItems().addAll(settingsItem, quitItem);

    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(helpItem, aboutItem);

    // Create menu bar and add menus
    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().addAll(fileMenu, helpMenu);

    return menuBar;
  }

  public void onAbout() {
    Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
    aboutDialog.setTitle("About paths");
    aboutDialog.setHeaderText("Paths");
    aboutDialog.setContentText("Created by Erik Bjørnsen and Emil Klevgård-Slåttsveen");

    aboutDialog.showAndWait();
  }

  public void onHelp() {
    Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
    aboutDialog.setTitle("Help paths");
    aboutDialog.setHeaderText("Paths");
    aboutDialog.setContentText(
        "I live in the American Gardens Building on West 81st Street on the 11th floor. My name is Patrick Bateman. I’m 27 years old. I believe in taking care of myself, and a balanced diet and a rigorous exercise routine. In the morning, if my face is a little puffy, I’ll put on an ice pack while doing my stomach crunches. I can do a thousand now. After I remove the ice pack I use a deep pore cleanser lotion. In the shower I use a water activated gel cleanser, then a honey almond body scrub, and on the face an exfoliating gel scrub. Then I apply an herb-mint facial masque which I leave on for 10 minutes while I prepare the rest of my routine. I always use an after shave lotion with little or no alcohol, because alcohol dries your face out and makes you look older. Then moisturizer, then an anti-aging eye balm followed by a final moisturizing protective lotion. There is an idea of a Patrick Bateman. Some kind of abstraction. But there is no real me. Only an entity. Something illusory. And though I can hide my cold gaze, and you can shake my hand and feel flesh gripping yours, and maybe you can even sense our lifestyles are probably comparable, I simply am not there.");

    aboutDialog.showAndWait();
  }
}
