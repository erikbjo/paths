package no.ntnu.idatg2001;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.model.Story;
import no.ntnu.idatg2001.model.StoryFileHandler;
import no.ntnu.idatg2001.ui.views.HomeView;

public class Main {
    public static void main(String[] args) {

        List<Story> stories = new ArrayList<>();
        StoryFileHandler.writePathsFile(stories, "Story.paths");
        // StoryFileHandler.readStoryFile("Story.paths");

        HomeView.mainApp(args);
    }
}
