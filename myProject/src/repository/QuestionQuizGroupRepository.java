package repository;

import java.util.List;
import java.util.Optional;

import model.QuestionQuizGroup;

public interface QuestionQuizGroupRepository {
	
	void add(QuestionQuizGroup group);

	QuestionQuizGroup update(QuestionQuizGroup group);

	Optional<QuestionQuizGroup> read(long id);

	List<QuestionQuizGroup> readAll();

	void delete(QuestionQuizGroup group);

}
