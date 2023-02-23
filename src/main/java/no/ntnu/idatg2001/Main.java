package no.ntnu.idatg2001;

import java.nio.file.Path;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) {
        ArrayList<Story> stories = StoryFileHandler.readStoryFile(Path.of("Stories.paths"));
    }
}