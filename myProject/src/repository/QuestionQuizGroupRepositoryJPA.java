package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.QuestionQuizGroup;

public class QuestionQuizGroupRepositoryJPA implements QuestionQuizGroupRepository{
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectJPA");
	private static final EntityManager em = emf.createEntityManager();// JPA Connection

	@Override
	public void add(QuestionQuizGroup group) {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(group);
		transaction.commit();
		System.out.println("Group inserted");
		
	}

	@Override
	public QuestionQuizGroup update(QuestionQuizGroup group) {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		QuestionQuizGroup updateGroup = em.merge(group);
		transaction.commit();

		System.out.println("Group updated");

		return updateGroup;
	}

	@Override
	public Optional<QuestionQuizGroup> read(long id) {
		
		QuestionQuizGroup group = null;
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		group = em.find(QuestionQuizGroup.class, id);
		transaction.commit();

		System.out.println("Group read");
		return Optional.ofNullable(group);
	}

	@Override
	public List<QuestionQuizGroup> readAll() {
		
		List<QuestionQuizGroup> groups = new ArrayList<>();

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		TypedQuery<QuestionQuizGroup> query = em.createNamedQuery("readAllGroups", QuestionQuizGroup.class);
		groups = query.getResultList();

		transaction.commit();
		System.err.println("all Answers: " + groups);
		return groups;
	}

	@Override
	public void delete(QuestionQuizGroup group) {
		
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.remove(group);
		transaction.commit();
		
	}

}
