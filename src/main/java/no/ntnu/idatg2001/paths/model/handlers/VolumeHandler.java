package no.ntnu.idatg2001.paths.model.handlers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import no.ntnu.idatg2001.paths.model.Database;

public class VolumeHandler {
    private static int currentVolume = 50;

    public static int getCurrentVolume() {
        return currentVolume;
    }

    public static void setCurrentVolume(int currentVolume) {
        VolumeHandler.currentVolume = currentVolume;
    }
}
