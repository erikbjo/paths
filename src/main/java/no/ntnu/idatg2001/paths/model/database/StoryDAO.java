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

public class StoryDAO implements DAO<Story> {
  private static final StoryDAO instance = new StoryDAO();
  private final EntityManagerFactory emf;
  private final EntityManager em;

  public StoryDAO() {
    this.emf = Persistence.createEntityManagerFactory("pathDB");
    this.em = this.emf.createEntityManager();
  }

  public static StoryDAO getInstance() {
    return instance;
  }

  @Override
  public void add(Story story) {
    if (getInstance().getAll().contains(story)) {
      throw new IllegalArgumentException("Instance of story already exists in the database.");
    } else {
      this.em.getTransaction().begin();
      this.em.persist(story);
      this.em.getTransaction().commit();
    }
  }

  @Override
  public void remove(Story story) {
    List<Game> gamesWithStory = GameDAO.getInstance().findGamesByStory(story);
    for (Game game : gamesWithStory) {
      game.setStory(null);
      GameDAO.getInstance().update(game);
    }

    em.getTransaction().begin();
    em.remove(em.contains(story) ? story : em.merge(story));
    em.getTransaction().commit();
  }

  @Override
  public void update(Story story) {
    em.getTransaction().begin();
    em.merge(story);
    em.flush();
    em.getTransaction().commit();
  }

  @Override
  public Iterator<Story> iterator() {
    TypedQuery<Story> query = this.em.createQuery("SELECT a FROM Story a", Story.class);
    return query.getResultList().iterator();
  }

  @Override
  public Optional<Story> find(Long id) {
    return Optional.ofNullable(em.find(Story.class, id));
  }

  /**
   * Retrieves all stories in the database.
   *
   * @return a list of all stories.
   */
  @Override
  public List<Story> getAll() {
    return em.createQuery("SELECT a FROM Story a", Story.class).getResultList();
  }

  public List<Long> getAllStoryIDs() {
    return em.createQuery("SELECT a.id FROM Story a", Long.class).getResultList();
  }

  /** Prints details for all stories in the database. */
  @Override
  public void printAllDetails() {
    List<Story> storyList = getAll();
    for (Story story : storyList) {
      System.out.println("Story Details" + " :: " + story.getId() + " :: " + story.getTitle());
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
