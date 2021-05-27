package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.UserType;

public class UserTypeRepositoryJPA implements UserTypeRepository {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectJPA");
	private static final EntityManager em = emf.createEntityManager();// JPA Connection

	@Override
	public void add(UserType userType) {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(userType);
		transaction.commit();
		System.out.println("User Type inserted");

	}

	@Override
	public UserType update(UserType userType) {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		UserType updateUserType = em.merge(userType);
		transaction.commit();

		System.out.println("User Type updated");
		return updateUserType;
	}

	@Override
	public Optional<UserType> read(long id) {

		UserType userType = null;
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		userType = em.find(UserType.class, id);
		transaction.commit();

		System.out.println("User Type read");
		return Optional.ofNullable(userType);
	}

	@Override
	public List<UserType> readAll() {

		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		List<UserType> types = new ArrayList<>();
		TypedQuery<UserType> query = em.createNamedQuery("readAllUserType", UserType.class);
		types = query.getResultList();
		transaction.commit();
		System.err.println("all users: " + types);
		return types;
	}

	@Override
	public void delete(UserType userType) {

		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.remove(userType);
		transaction.commit();

	}

}
