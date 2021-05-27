package repository;

import java.util.List;
import java.util.Optional;

import model.Question;


public interface QuestionRepository {

	// CRUD Methods
	public void add(Question question);

	public Question update(Question question);

	public Optional<Question> read(long id);
	
	public Optional<Question> read(String question);

	public List<Question> readAll();

	public void delete(Question question);
	
	public List<Question> readAccordingGroup();

}
