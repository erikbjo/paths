package no.ntnu.idatg2001.model;

public enum Languages {
  ENGLISH("English", "en"),
  SPANISH("Español", "es"),
  FRENCH("Française", "fr"),
  GERMAN("Deutsch", "de"),
  NORWEGIAN("Norsk", "no");

  private final String name;
  private final String localName;

  Languages(String name, String localName) {
    this.name = name;
    this.localName = localName;
  }

  public String getName() {
    return name;
  }

  public String getLocalName() {
    return localName;
  }
}
