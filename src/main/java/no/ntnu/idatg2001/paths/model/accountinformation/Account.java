package no.ntnu.idatg2001.paths.model.accountinformation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;

/**
 * Represents an account. Each account holds some information about that account.
 *
 * @author Erik Bjørnsen & Emil Klevgård-Slåttsveen
 * @version 2.1
 */
@Entity(name = "Account")
@Table(name = "account")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;

  private String userName;
  private String pinCode;

  public Account() {}

  public Account(String username, String pinCode) {
    setUsername(username);
    setPinCode(pinCode);
  }

  private void setUsername(String username) {
    if (username.isBlank() || username.isEmpty()) {
      throw new IllegalArgumentException("Username must not be empty or blank.");
    } else {
      this.userName = username;
    }
  }

  /**
   * Returns the name of the account owner.
   *
   * @return the name of the account owner as a String.
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Returns the pin code chosen by the account owner.
   *
   * @return the 4-digit pin code chosen by the account owner as a String.
   */
  public String getPinCode() {
    return pinCode;
  }

  /**
   * Sets the pin code for the account.
   *
   * @param pinCode the pin code to be set.
   * @throws IllegalArgumentException if the pin code is not 4 digits or contains non-numeric
   *     characters.
   */
  public void setPinCode(String pinCode) throws IllegalArgumentException {
    if (!pinCode.matches("\\d+")) {
      throw new IllegalArgumentException("Pin code must only consist of numbers.");
    } else if (pinCode.length() != 4) {
      throw new IllegalArgumentException("Pin code must consist of 4 digits.");
    } else {
      this.pinCode = pinCode;
    }
  }

  /**
   * Returns the account id.
   *
   * @return the id as a String.
   */
  public String getId() {
    return id;
  }
}
