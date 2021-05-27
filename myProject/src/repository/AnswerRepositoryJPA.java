package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Answer;

public class AnswerRepositoryJPA implements AnswerRepository {
	
	//private static final String USER_DB = "jdbc:derby:projectJPA; create=true";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectJPA");
	private static final EntityManager em = emf.createEntityManager();// JPA Connection

	@Override
	public void add(Answer answer) {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(answer);
		transaction.commit();
		System.out.println("Answer inserted");
	}

	@Override
	public Answer update(Answer answer) {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Answer updateAnswer = em.merge(answer);
		transaction.commit();

		System.out.println("Answer updated");

		return updateAnswer;
	}

	@Override
	public Optional<Answer> read(long id) {
		
		Answer answer = null;
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		answer = em.find(Answer.class, id);
		transaction.commit();

		System.out.println("Answer read");
		return Optional.ofNullable(answer);
	}

	@Override
	public List<Answer> readAll() {
		
		List<Answer> answers = new ArrayList<>();

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		TypedQuery<Answer> query = em.createNamedQuery("readAllAnswers", Answer.class);
		answers = query.getResultList();

		transaction.commit();
		System.err.println("all Answers: " + answers);
		return answers;
	}

	@Override
	public void delete(Answer answer) {
		
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.remove(answer);
		transaction.commit();
		
	}

}
