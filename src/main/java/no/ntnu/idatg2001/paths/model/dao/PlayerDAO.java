package no.ntnu.idatg2001.paths.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * {@inheritDoc} The StoryDAO class is a Data Access Object for the Player class.
 *
 * <p>It is used to retrieve and store Player objects to and from the database.
 *
 * <p>It implements the DAO interface.
 *
 * <p>It is a singleton class.
 *
 * @see DAO
 * @see Player
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class PlayerDAO implements DAO<Player> {
  private static final PlayerDAO instance = new PlayerDAO();
  private final EntityManagerFactory emf;
  private final EntityManager em;

  /**
   * Constructor for the PlayerDAO class.
   *
   * <p>Initializes the EntityManagerFactory and EntityManager.
   */
  public PlayerDAO() {
    this.emf = Persistence.createEntityManagerFactory("pathDB");
    this.em = this.emf.createEntityManager();
  }

  /**
   * Returns the instance of the PlayerDAO class.
   *
   * @return the instance of the PlayerDAO class.
   */
  public static PlayerDAO getInstance() {
    return instance;
  }

  /** {@inheritDoc} */
  @Override
  public void add(Player player) {
    if (getInstance().getAll().contains(player)) {
      throw new IllegalArgumentException("Instance of player already exists in the database.");
    } else {
      this.em.getTransaction().begin();
      this.em.persist(player);
      this.em.getTransaction().commit();
    }
  }

  /** {@inheritDoc} */
  @Override
  public void remove(Player player) {
    List<Game> gamesWithPlayer = GameDAO.getInstance().findGamesByPlayer(player);
    for (Game game : gamesWithPlayer) {
      game.setPlayer(null);
      GameDAO.getInstance().update(game);
    }

    em.getTransaction().begin();
    em.remove(em.contains(player) ? player : em.merge(player));
    em.getTransaction().commit();
  }

  /** {@inheritDoc} */
  @Override
  public void update(Player player) {
    List<Game> gamesWithPlayer = GameDAO.getInstance().findGamesByPlayer(player);
    for (Game game : gamesWithPlayer) {
      game.setPlayer(null);
      GameDAO.getInstance().update(game);
    }

    em.getTransaction().begin();
    em.merge(player);
    em.getTransaction().commit();

    Player updatedPlayer = em.find(Player.class, player.getId());

    for (Game game : gamesWithPlayer) {
      game.setPlayer(updatedPlayer);
      GameDAO.getInstance().update(game);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Iterator<Player> iterator() {
    TypedQuery<Player> query = this.em.createQuery("SELECT a FROM Player a", Player.class);
    return query.getResultList().iterator();
  }

  /** {@inheritDoc} */
  @Override
  public Optional<Player> find(Long id) {
    return Optional.ofNullable(em.find(Player.class, id));
  }

  /** {@inheritDoc} */
  @Override
  public List<Player> getAll() {
    return em.createQuery("SELECT a FROM Player a", Player.class).getResultList();
  }

  /** {@inheritDoc} */
  @Override
  public void printAllDetails() {
    List<Player> playerList = getAll();
    for (Player player : playerList) {
      System.out.println("Player Details" + " :: " + player.getId() + " :: " + player.getName());
    }
  }

  /** {@inheritDoc} */
  @Override
  public void close() {
    if (em.isOpen()) {
      this.em.close();
    }
    if (emf.isOpen()) {
      this.emf.close();
    }
  }
}
