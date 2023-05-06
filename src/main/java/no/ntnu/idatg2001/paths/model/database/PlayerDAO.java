package no.ntnu.idatg2001.paths.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.units.Player;

public class PlayerDAO implements DAO<Player> {
  private static final PlayerDAO instance = new PlayerDAO();
  private final EntityManagerFactory emf;
  private final EntityManager em;

  public PlayerDAO() {
    this.emf = Persistence.createEntityManagerFactory("pathDB");
    this.em = this.emf.createEntityManager();
  }

  public static PlayerDAO getInstance() {
    return instance;
  }

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

  @Override
  public void remove(Player player) {
//    Player foundPlayer = em.find(Player.class, player.getId());
//    em.getTransaction().begin();
//    em.remove(foundPlayer);
//    em.getTransaction().commit();

    List<Game> gamesWithPlayer = GameDAO.getInstance().findGamesByPlayer(player);
    for (Game game : gamesWithPlayer) {
      game.setPlayer(null);
      GameDAO.getInstance().update(game);
    }

    em.getTransaction().begin();
    em.remove(em.contains(player) ? player : em.merge(player));
    em.getTransaction().commit();
  }

  @Override
  public void update(Player player) {
    em.getTransaction().begin();
    em.merge(player);
    em.flush();
    em.getTransaction().commit();
  }

  @Override
  public Iterator<Player> iterator() {
    TypedQuery<Player> query = this.em.createQuery("SELECT a FROM Player a", Player.class);
    return query.getResultList().iterator();
  }

  @Override
  public Optional<Player> find(Long id) {
    return Optional.ofNullable(em.find(Player.class, id));
  }

  /**
   * Retrieves all players in the database.
   *
   * @return a list of all players.
   */
  @Override
  public List<Player> getAll() {
    return em.createQuery("SELECT a FROM Player a", Player.class).getResultList();
  }

  public List<Long> getAllPlayerIDs() {
    return em.createQuery("SELECT a.id FROM Player a", Long.class).getResultList();
  }

  /** Prints details for all players in the database. */
  @Override
  public void printAllDetails() {
    List<Player> playerList = getAll();
    for (Player player : playerList) {
      System.out.println("Player Details" + " :: " + player.getId() + " :: " + player.getName());
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
}
