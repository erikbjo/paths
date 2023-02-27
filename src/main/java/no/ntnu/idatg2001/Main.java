package no.ntnu.idatg2001;

import java.nio.file.Path;
import java.util.List;

public class Main
{
    public static void main(String[] args) {
        List<Story> stories = StoryFileHandler.readStoryFile(Path.of("Stories.paths"));
        StoryFileHandler.writePathsFile(stories, Path.of("Stories.paths"));
    }
}