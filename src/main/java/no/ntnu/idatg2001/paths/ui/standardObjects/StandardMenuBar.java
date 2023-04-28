package no.ntnu.idatg2001.paths.ui.standardObjects;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import no.ntnu.idatg2001.paths.ui.controllers.SettingsController;

public class StandardMenuBar extends MenuBar {

    private SettingsController settingsController;

    public StandardMenuBar() {
        // Create menu items
        MenuItem helpItem = new MenuItem("Help");
        helpItem.setOnAction(actionEvent -> onHelp());
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(actionEvent -> onAbout());
        MenuItem settingsItem = new MenuItem("Settings");
        settingsItem.setOnAction(
        event -> {
        settingsController = new SettingsController(event);
        });
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(actionEvent -> System.exit(0));

        // Create menus and add items
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(settingsItem, quitItem);

        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().addAll(helpItem, aboutItem);

        // add menus
        this.getMenus().addAll(fileMenu, helpMenu);
        }

private void onAbout() {
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