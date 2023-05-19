package no.ntnu.idatg2001.paths.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * {@inheritDoc}
 * The GameDAO class is a Data Access Object for the Game class.
 *
 * <p>It is used to retrieve and store Game objects to and from the database.
 *
 * <p>It implements the DAO interface.
 *
 * <p>It is a singleton class.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @see DAO
 * @see Game
 */
public class GameDAO implements DAO<Game> {
  private static final GameDAO instance = new GameDAO();
  private final EntityManagerFactory emf;
  private final EntityManager em;

  /**
   * Constructor for the GameDAO class.
   *
   * <p>Initializes the EntityManagerFactory and EntityManager.
   */
  public GameDAO() {
    this.emf = Persistence.createEntityManagerFactory("pathDB");
    this.em = this.emf.createEntityManager();
  }

  public static GameDAO getInstance() {
    return instance;
  }

  /** {@inheritDoc} */
  @Override
  public void add(Game game) {
    if (getInstance().getAll().contains(game)) {
      throw new IllegalArgumentException("Instance of game already exists in the database.");
    } else {
      this.em.getTransaction().begin();
      this.em.persist(game);
      this.em.getTransaction().commit();
    }
  }

  /** {@inheritDoc} */
  @Override
  public void remove(Game game) {
    Game foundGame = em.find(Game.class, game.getId());
    em.getTransaction().begin();
    em.remove(foundGame);
    em.getTransaction().commit();
  }

  /** {@inheritDoc} */
  @Override
  public void update(Game game) {
    em.getTransaction().begin();
    em.merge(game);
    em.flush();
    em.getTransaction().commit();
  }

  /** {@inheritDoc} */
  @Override
  public Iterator<Game> iterator() {
    TypedQuery<Game> query = this.em.createQuery("SELECT a FROM Game a", Game.class);
    return query.getResultList().iterator();
  }

  /** {@inheritDoc} */
  @Override
  public Optional<Game> find(Long id) {
    return Optional.ofNullable(em.find(Game.class, id));
  }

  /** {@inheritDoc} */
  @Override
  public List<Game> getAll() {
    return em.createQuery("SELECT a FROM Game a", Game.class).getResultList();
  }

  /** {@inheritDoc} */
  @Override
  public void printAllDetails() {
    List<Game> gameList = getAll();
    for (Game game : gameList) {
      System.out.println("Game Details" + " :: " + game.getId() + " :: " + game.getStory());
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

  /**
   * Finds all games in the database with the given player.
   *
   * @param player the player to find games for.
   * @return a list of all games with the given player.
   */
  public List<Game> findGamesByPlayer(Player player) {
    TypedQuery<Game> query =
        this.em.createQuery("SELECT a FROM Game a WHERE a.player = :player", Game.class);
    query.setParameter("player", player);
    return query.getResultList();
  }

  /**
   * Finds all games in the database with the given story.
   *
   * @param story the story to find games for.
   * @return a list of all games with the given story.
   */
  public List<Game> findGamesByStory(Story story) {
    TypedQuery<Game> query =
        this.em.createQuery("SELECT a FROM Game a WHERE a.story = :story", Game.class);
    query.setParameter("story", story);
    return query.getResultList();
  }
}
