package repository;

import java.util.List;
import java.util.Optional;

import model.Answer;

public interface AnswerRepository {

	// CRUD Method
	// create
	void add(Answer answer);

	// update
	Answer update(Answer answer);

	// read
	Optional<Answer> read(long id);

	// read all
	List<Answer> readAll();

	// delete
	void delete(Answer answer);
}
