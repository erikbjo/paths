package no.ntnu.idatg2001.paths.model;

/**
 * Enum for the supported languages.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public enum Languages {
  ENGLISH("English", "en"),
  SPANISH("Español", "es"),
  FRENCH("Française", "fr"),
  GERMAN("Deutsch", "de"),
  NORWEGIAN("Norsk", "no");

  private final String name;
  private final String localName;

  /**
   * Constructor for the Languages enum.
   *
   * @param name the name of the language, in the language itself
   * @param localName the local name of the language
   */
  Languages(String name, String localName) {
    this.name = name;
    this.localName = localName;
  }

  /**
   * This function returns the name of the language.
   *
   * @return the name of the language
   */
  public String getName() {
    return name;
  }

  /**
   * This function returns the local name of the language.
   *
   * @return the local name of the language
   */
  public String getLocalName() {
    return localName;
  }
}
