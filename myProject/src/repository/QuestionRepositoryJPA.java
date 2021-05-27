package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Question;


public class QuestionRepositoryJPA implements QuestionRepository {

	// private static final String USER_DB = "jdbc:derby:projectJPA; create=true";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectJPA");
	private static final EntityManager em = emf.createEntityManager();// JPA Connection

	@Override
	public void add(Question question) {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(question);
		transaction.commit();
		System.out.println("Question inserted");

	}

	@Override
	public Question update(Question question) {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Question updateQuestion = em.merge(question);
		transaction.commit();

		System.out.println("Question updated");
		return updateQuestion;
	}

	@Override
	public Optional<Question> read(long id) {

		Question question = null;
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		question = em.find(Question.class, id);
		transaction.commit();

		System.out.println("Question read");
		return Optional.ofNullable(question);
	}

	@Override
	public List<Question> readAll() {

		List<Question> questions = new ArrayList<>();

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		TypedQuery<Question> query = em.createNamedQuery("readAllQuestions", Question.class);
		questions = query.getResultList();

		transaction.commit();
		System.err.println("all Questions: " + questions);
		return questions;
	}

	@Override
	public void delete(Question question) {

		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.remove(question);
		transaction.commit();

	}

	@Override
	public List<Question> readAccordingGroup() {

		List<Question> questions = new ArrayList<>();

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		TypedQuery<Question> query = em.createNamedQuery("getQuestionAccordingGroup", Question.class);
		questions = query.getResultList();

		transaction.commit();
		System.err.println("all Questions: " + questions);
		return questions;
	}

	@Override
	public Optional<Question> read(String question) {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		TypedQuery<Question> query = em.createNamedQuery("findQuestion",Question.class);
		query.setParameter(1, question);
		
		List<Question> questions = query.getResultList();
		transaction.commit();
		
		System.out.println("Question read");
		return Optional.ofNullable(questions.size() > 0 ? questions.get(0) : null);
		
	}

}
