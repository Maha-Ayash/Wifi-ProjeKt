package repository;

import java.util.List;
import java.util.Optional;

import model.User;



public interface UserRepository {

	// CRUD Methods
	public void add(User user) ;

	public User update(User user);

	public Optional<User> read(long id);
	
	public Optional<User> read(String userName , String password);

	public List<User> readAll();

	public void delete(User user);

}
