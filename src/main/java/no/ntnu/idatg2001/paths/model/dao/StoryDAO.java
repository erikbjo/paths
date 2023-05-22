package no.ntnu.idatg2001.paths.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;

/**
 * {@inheritDoc} The StoryDAO class is a Data Access Object for the Story class.
 *
 * <p>It is used to retrieve and store Story objects to and from the database.
 *
 * <p>It implements the DAO interface.
 *
 * <p>It is a singleton class.
 *
 * @see DAO
 * @see Story
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class StoryDAO implements DAO<Story> {
  private static final StoryDAO instance = new StoryDAO();
  private final EntityManagerFactory emf;
  private final EntityManager em;

  /**
   * Constructor for the StoryDAO class.
   *
   * <p>Initializes the EntityManagerFactory and EntityManager.
   */
  public StoryDAO() {
    this.emf = Persistence.createEntityManagerFactory("pathDB");
    this.em = this.emf.createEntityManager();
  }

  /**
   * Returns the instance of the StoryDAO class.
   *
   * @return the instance of the StoryDAO class
   */
  public static StoryDAO getInstance() {
    return instance;
  }

  /** {@inheritDoc} */
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

  /**
   * {@inheritDoc} <br>
   * <br>
   * Remove all links with the story to be removed. This is done to avoid a constraint violation in
   * the database. Sets all games with the story to be removed to have no story.
   */
  @Override
  public void remove(Story story) {
    List<Game> gamesWithStory = GameDAO.getInstance().findGamesByStory(story);
    for (Game game : gamesWithStory) {
      game.setStory(null);
      GameDAO.getInstance().update(game);
    }

    List<Link> linksWithStory = StoryDAO.getInstance().findLinksByStory(story);
    for (Link link : linksWithStory) {
      link.setStory(null);
      em.getTransaction().begin();
      em.merge(link);
      em.flush();
      em.getTransaction().commit();
    }

    List<Passage> passagesWithStory = StoryDAO.getInstance().findPassagesByStory(story);
    for (Passage passage : passagesWithStory) {
      passage.setStory(null);
      em.getTransaction().begin();
      em.merge(passage);
      em.flush();
      em.getTransaction().commit();
    }

    em.getTransaction().begin();
    em.remove(em.contains(story) ? story : em.merge(story));
    em.getTransaction().commit();
  }

  /** {@inheritDoc} */
  @Override
  public void update(Story story) {
    em.getTransaction().begin();
    em.merge(story);
    em.flush();
    em.getTransaction().commit();
  }

  /** {@inheritDoc} */
  @Override
  public Iterator<Story> iterator() {
    TypedQuery<Story> query = this.em.createQuery("SELECT a FROM Story a", Story.class);
    return query.getResultList().iterator();
  }

  /** {@inheritDoc} */
  @Override
  public Optional<Story> find(Long id) {
    return Optional.ofNullable(em.find(Story.class, id));
  }

  /** {@inheritDoc} */
  @Override
  public List<Story> getAll() {
    return em.createQuery("SELECT a FROM Story a", Story.class).getResultList();
  }

  /** {@inheritDoc} */
  @Override
  public void printAllDetails() {
    List<Story> storyList = getAll();
    for (Story story : storyList) {
      System.out.println("Story Details" + " :: " + story.getId() + " :: " + story.getTitle());
      System.out.println("Links: " + findLinksByStory(story));
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
   * Finds all links with the given story.
   *
   * @param story the story to find links for
   * @return a list of all links with the given story
   */
  public List<Link> findLinksByStory(Story story) {
    TypedQuery<Link> query =
        this.em.createQuery("SELECT a FROM Link a WHERE a.story = :story", Link.class);
    query.setParameter("story", story);
    return query.getResultList();
  }

  /**
   * Finds all passages with the given story.
   *
   * @param story the story to find passages for
   * @return a list of all passages with the given story
   */
  public List<Passage> findPassagesByStory(Story story) {
    TypedQuery<Passage> query =
        this.em.createQuery("SELECT a FROM Passage a WHERE a.story = :story", Passage.class);
    query.setParameter("story", story);
    return query.getResultList();
  }
}
