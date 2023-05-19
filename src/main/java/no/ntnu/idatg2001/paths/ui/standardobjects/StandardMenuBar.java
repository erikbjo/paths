package no.ntnu.idatg2001.paths.ui.standardobjects;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.dao.GameDAO;
import no.ntnu.idatg2001.paths.model.dao.PlayerDAO;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.ui.alerts.ExceptionAlert;
import no.ntnu.idatg2001.paths.ui.controllers.MainMenuController;
import no.ntnu.idatg2001.paths.ui.controllers.SettingsController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class StandardMenuBar extends MenuBar {

  private final MenuItem helpItem;
  private final MenuItem aboutItem;
  private final MenuItem settingsItem;
  private final MenuItem quitItem;
  private final Menu helpMenu;
  private final Menu fileMenu;
  private ResourceBundle resourceBundle =
      ResourceBundle.getBundle(
          "languages/menuBar",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private SettingsController settingsController;

  public StandardMenuBar(Stage stage) {
    // Create menu items
    helpItem = new MenuItem(resourceBundle.getString("helpItem"));
    helpItem.setOnAction(actionEvent -> onHelp());
    aboutItem = new MenuItem(resourceBundle.getString("aboutItem"));
    aboutItem.setOnAction(actionEvent -> onAbout());
    settingsItem = new MenuItem(resourceBundle.getString("settingsItem"));
    settingsItem.setOnAction(event -> settingsController = new SettingsController(event));
    quitItem = new MenuItem(resourceBundle.getString("quitItem"));
    quitItem.setOnAction(
        actionEvent -> {
          actionEvent.consume();
          GameDAO.getInstance().close();
          StoryDAO.getInstance().close();
          PlayerDAO.getInstance().close();
          Platform.exit();
        });

    // Create menus and add items
    fileMenu = new Menu(resourceBundle.getString("fileMenu"));
    fileMenu.getItems().addAll(settingsItem, quitItem);

    helpMenu = new Menu(resourceBundle.getString("helpMenu"));
    helpMenu.getItems().addAll(helpItem, aboutItem);

    // add menus
    this.getMenus().addAll(fileMenu, helpMenu);

    if (!stage.getTitle().equals("Home")) {
      MenuItem homeItem = new MenuItem("Home");
      homeItem.setOnAction(
          actionEvent -> {
            onHome(stage);
          });
      fileMenu.getItems().add(0, homeItem);
    }

    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> updateLanguage());
  }

  public StandardMenuBar(Stage stage, Story story) {
    // Create menu items
    helpItem = new MenuItem(resourceBundle.getString("helpItem"));
    helpItem.setOnAction(actionEvent -> onHelp());
    aboutItem = new MenuItem(resourceBundle.getString("aboutItem"));
    aboutItem.setOnAction(actionEvent -> onAbout());
    settingsItem = new MenuItem(resourceBundle.getString("settingsItem"));
    settingsItem.setOnAction(event -> settingsController = new SettingsController(event));
    quitItem = new MenuItem(resourceBundle.getString("quitItem"));
    quitItem.setOnAction(
        actionEvent -> {
          actionEvent.consume();
          GameDAO.getInstance().close();
          StoryDAO.getInstance().close();
          PlayerDAO.getInstance().close();
          Platform.exit();
        });

    // Create menus and add items
    fileMenu = new Menu(resourceBundle.getString("fileMenu"));
    fileMenu.getItems().addAll(settingsItem, quitItem);

    helpMenu = new Menu(resourceBundle.getString("helpMenu"));
    helpMenu.getItems().addAll(helpItem, aboutItem);

    // add menus
    this.getMenus().addAll(fileMenu, helpMenu);

    MenuItem homeItem = new MenuItem(resourceBundle.getString("homeItem"));
    homeItem.setOnAction(
        actionEvent -> {
          StoryDAO.getInstance().remove(story);
          onHome(stage);
        });
    fileMenu.getItems().add(0, homeItem);

    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> updateLanguage());
  }

  private void onAbout() {
    Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
    aboutDialog.setTitle(resourceBundle.getString("aboutDialogTitle"));
    aboutDialog.setHeaderText(resourceBundle.getString("aboutDialogHeaderText"));
    aboutDialog.setContentText(resourceBundle.getString("aboutDialogContentText"));

    aboutDialog.showAndWait();
  }

  public void onHelp() {
    Alert helpDialog = new Alert(Alert.AlertType.INFORMATION);
    helpDialog.setTitle(resourceBundle.getString("helpDialogTitle"));
    helpDialog.setHeaderText(resourceBundle.getString("helpDialogHeaderText"));
    helpDialog.setContentText(resourceBundle.getString("helpDialogContentText"));
    helpDialog.showAndWait();
  }

  public void onHome(Stage stage) {
    try {
      new MainMenuController(stage);
    } catch (IOException e) {
      new ExceptionAlert(e).showAndWait();
    }
  }

  public void updateLanguage() {
    resourceBundle =
        ResourceBundle.getBundle(
            "languages/menuBar",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    fileMenu.setText(resourceBundle.getString("fileMenu"));
    helpMenu.setText(resourceBundle.getString("helpMenu"));
    helpItem.setText(resourceBundle.getString("helpItem"));
    aboutItem.setText(resourceBundle.getString("aboutItem"));
    settingsItem.setText(resourceBundle.getString("settingsItem"));
    quitItem.setText(resourceBundle.getString("quitItem"));
  }
}
