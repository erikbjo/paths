package no.ntnu.idatg2001.paths.model.utilities;

import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.handlers.VolumeHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsStoryFileWriter {
  private static final PathsStoryFileWriter instance = new PathsStoryFileWriter();

  private PathsStoryFileWriter() {}

  public static PathsStoryFileWriter getInstance() {
    return instance;
  }

  public static void writeStoryToFile(Story story) {
    String fileName = story.getTitle() + ".paths";
    if (!fileExists(fileName)) {
      createFile(fileName);
    }
    StringBuilder content =
        new StringBuilder(
            story.getTitle());
    content.append("\n");

    story.getPassages().forEach(passage -> {
      content.append("\n::")
          .append(passage.getTitle())
          .append("\n")
          .append(passage.getContent())
          .append("\n");
        passage.getLinks().forEach(link -> content.append("[")
            .append(link.getText())
            .append("]")
            .append("(")
            .append(link.getReference())
            .append(")")
                .append("\n"));
    });


    Path filePath = Paths.get(fileName);

    try {
      if (filePath.getParent() != null) {
        Files.createDirectories(filePath.getParent());
      }
      try (BufferedWriter fileWriter = Files.newBufferedWriter(filePath)) {
        fileWriter.write(content.toString());
        System.out.println("Successfully created and wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred while creating and writing to the file.");
        e.printStackTrace();
      }
    } catch (IOException e) {
      System.out.println("An error occurred while creating the directories.");
      e.printStackTrace();
    }
  }

  private static boolean fileExists(String fileName) {
    boolean fileExists = false;

    try {
      Path filePath = Paths.get(fileName);
      fileExists = Files.exists(filePath);
    } catch (Exception e) {
      System.out.println("An error occurred while checking if the file exists.");
      e.printStackTrace();
    }

    return fileExists;
  }

  private static void createFile(String fileName) {
    try {
      Path filePath = Paths.get(fileName);
      Files.createFile(filePath);
      System.out.println("File created.");
    } catch (IOException e) {
      System.out.println("An error occurred while creating the file.");
      e.printStackTrace();
    }
  }
}