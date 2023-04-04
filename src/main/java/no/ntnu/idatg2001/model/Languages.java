package no.ntnu.idatg2001.model;

public enum Languages {
    ENGLISH("English"),
    SPANISH("Español"),
    FRENCH("Français"),
    GERMAN("Deutsch"),
    NORWEGIAN("Norsk");

    private final String name;

    Languages(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}