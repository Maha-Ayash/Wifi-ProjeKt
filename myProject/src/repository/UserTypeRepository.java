package repository;

import java.util.List;
import java.util.Optional;

import model.UserType;

public interface UserTypeRepository {
	
	public void add(UserType userType);

	public UserType update(UserType userType);

	public Optional<UserType> read(long id);

	public List<UserType> readAll();

	public void delete(UserType userType);


}
