package no.ntnu.idatg2001.paths.model.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import no.ntnu.idatg2001.paths.model.Languages;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.handlers.VolumeHandler;

public class SettingsFileWriter {
  private static final String FILENAME = "paths.settings";

  public static void saveSettings() {
    if (fileExists()) {
      String content =
          "Settings\n"
              + "\nLanguage:"
              + LanguageHandler.getCurrentLanguage()
              + "\nVolume:"
              + VolumeHandler.getCurrentVolume();
      Path filePath = Paths.get(FILENAME);

      try {
        if (filePath.getParent() != null) {
          Files.createDirectories(filePath.getParent());
        }
        try (BufferedWriter fileWriter = Files.newBufferedWriter(filePath)) {
          fileWriter.write(content);
          System.out.println("Successfully created and wrote to the file.");
        } catch (IOException e) {
          System.out.println("An error occurred while creating and writing to the file.");
          e.printStackTrace();
        }
      } catch (IOException e) {
        System.out.println("An error occurred while creating the directories.");
        e.printStackTrace();
      }
    } else {
      createFile();
    }
  }

  public static void readSettings() {
    if (fileExists()) {
      Path filePath = Paths.get(FILENAME);
      try (BufferedReader fileReader = Files.newBufferedReader(filePath)) {
        String line;
        System.out.println("Reading file content:");
        while ((line = fileReader.readLine()) != null) {
          if (line.startsWith("Language:")) {
            String language = line.split(":")[1];
            LanguageHandler.setCurrentLanguage(Languages.valueOf(language));
            System.out.println("Language set to: " + language);
          } else if (line.startsWith("Volume:")) {
            int volume = Integer.parseInt(line.split(":")[1]);
            VolumeHandler.setCurrentVolume(volume);
            System.out.println("Volume set to: " + volume);
          }
        }
      } catch (IOException e) {
        System.out.println("An error occurred while reading the file.");
        e.printStackTrace();
      }
    } else {
      createFile();
    }
  }

  private static boolean fileExists() {
    boolean fileExists = false;

    try {
      Path filePath = Paths.get(FILENAME);
      fileExists = Files.exists(filePath);
    } catch (Exception e) {
      System.out.println("An error occurred while checking if the file exists.");
      e.printStackTrace();
    }

    return fileExists;
  }

  private static void createFile() {
    try {
      Path filePath = Paths.get(FILENAME);
      Files.createFile(filePath);
      System.out.println("File created.");
    } catch (IOException e) {
      System.out.println("An error occurred while creating the file.");
      e.printStackTrace();
    }
  }
}
