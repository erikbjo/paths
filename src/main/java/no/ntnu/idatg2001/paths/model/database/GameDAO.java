package no.ntnu.idatg2001.paths.model.database;

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

public class GameDAO implements DAO<Game> {
  private static final GameDAO instance = new GameDAO();
  private final EntityManagerFactory emf;
  private final EntityManager em;

  public GameDAO() {
    this.emf = Persistence.createEntityManagerFactory("pathDB");
    this.em = this.emf.createEntityManager();
  }

  public static GameDAO getInstance() {
    return instance;
  }

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

  @Override
  public void remove(Game game) {
    Game foundGame = em.find(Game.class, game.getId());
    em.getTransaction().begin();
    em.remove(foundGame);
    em.getTransaction().commit();
  }

  @Override
  public void update(Game game) {
    em.getTransaction().begin();
    em.merge(game);
    em.flush();
    em.getTransaction().commit();
  }

  @Override
  public Iterator<Game> iterator() {
    TypedQuery<Game> query = this.em.createQuery("SELECT a FROM Game a", Game.class);
    return query.getResultList().iterator();
  }

  @Override
  public Optional<Game> find(Long id) {
    return Optional.ofNullable(em.find(Game.class, id));
  }

  /**
   * Retrieves all accounts in the database.
   *
   * @return a list of all accounts.
   */
  @Override
  public List<Game> getAll() {
    return em.createQuery("SELECT a FROM Game a", Game.class).getResultList();
  }

  public List<Long> getAllGameIDs() {
    return em.createQuery("SELECT a.id FROM Game a", Long.class).getResultList();
  }

  /** Prints details for all accounts in the database. */
  @Override
  public void printAllDetails() {
    List<Game> gameList = getAll();
    for (Game game : gameList) {
      System.out.println("Game Details" + " :: " + game.getId() + " :: " + game.getStory());
    }
  }

  /** Closes the EntityManager and EntityManagerFactory. */
  @Override
  public void close() {
    if (em.isOpen()) {
      this.em.close();
    }
    if (emf.isOpen()) {
      this.emf.close();
    }
  }

  public List<Game> findGamesByPlayer(Player player) {
    TypedQuery<Game> query =
        this.em.createQuery("SELECT a FROM Game a WHERE a.player = :player", Game.class);
    query.setParameter("player", player);
    return query.getResultList();
  }

  public List<Game> findGamesByStory(Story story) {
    TypedQuery<Game> query =
            this.em.createQuery("SELECT a FROM Game a WHERE a.story = :story", Game.class);
    query.setParameter("story", story);
    return query.getResultList();
  }
}
