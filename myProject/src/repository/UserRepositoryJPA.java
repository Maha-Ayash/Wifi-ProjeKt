package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.User;

public class UserRepositoryJPA implements UserRepository {

//	private static final String USER_DB = "jdbc:derby:projectJPA; create=true";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectJPA");
	private static final EntityManager em = emf.createEntityManager();// JPA Connection

	@Override
	public void add(User user) {

		EntityTransaction transaction = em.getTransaction();

		if (user == null) {
			System.out.println("You cannot add an empty user");
		}
		if (em.find(User.class, user.getId()) != null) {
			System.out.println("This user already exists!");
		}
		else {
			transaction.begin();
			em.persist(user);
			transaction.commit();
			System.out.println("User inserted");
		}
	}

	@Override
	public User update(User user) {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		User updateUser = em.merge(user);
		transaction.commit();

		System.out.println("User updated");
		return updateUser;
	}

	@Override
	public Optional<User> read(long id) {
		User user = null;
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		user = em.find(User.class, id);
		transaction.commit();

		System.out.println("User read");
		return Optional.ofNullable(user);
	}

	@Override
	public List<User> readAll() {

		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		List<User> users = new ArrayList<>();
		TypedQuery<User> query = em.createNamedQuery("getAllUsers", User.class);
		users = query.getResultList();
		transaction.commit();
		System.err.println("all users: " + users);
		return users;
	}

	@Override
	public void delete(User user) {

		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		em.remove(user);
		transaction.commit();

	}

	@Override
	public Optional<User> read(String userName, String password) {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		TypedQuery<User> query = em.createNamedQuery("findUser", User.class);
		query.setParameter(1, userName);
		query.setParameter(2, password);
//		query.setParameter(3, type);

		List<User> users = query.getResultList();
		transaction.commit();

		System.out.println("User read");
		return Optional.ofNullable(users.size() > 0 ? users.get(0) : null);

	}

}
