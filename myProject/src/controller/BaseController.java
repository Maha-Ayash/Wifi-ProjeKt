package controller;

import java.io.IOException;

import application.Constants;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Answer;
import model.Question;
import model.QuestionQuizGroup;
import model.User;
import model.UserType;
import repository.AnswerRepository;
import repository.AnswerRepositoryJPA;
import repository.QuestionQuizGroupRepository;
import repository.QuestionQuizGroupRepositoryJPA;
import repository.QuestionRepository;
import repository.QuestionRepositoryJPA;
import repository.UserRepository;
import repository.UserRepositoryJPA;
import repository.UserTypeRepository;
import repository.UserTypeRepositoryJPA;

public class BaseController extends Constants {

	public static UserType typeLoginUser;;
	public static String loginUsername;
	public static String quizTypeText;
	public static QuestionQuizGroup questionQuizGroupFromComboBox;

	static UserRepository userRepository = new UserRepositoryJPA();
	static ObservableList<User> users = FXCollections.observableArrayList(userRepository.readAll());
	static {
		users.addListener(new ListChangeListener<User>() {

			@Override
			public void onChanged(Change<? extends User> c) {
				while (c.next()) {
					if (c.wasReplaced()) {
						System.err.println("User updated: " + c.wasUpdated());
						for (User user : c.getAddedSubList()) {
							userRepository.update(user);
						}

					} else if (c.wasAdded()) {
						System.err.println("User was added to list");
						for (User user : c.getAddedSubList()) {
							userRepository.add(user);
						}
					} else if (c.wasRemoved()) {
						System.err.println("User was removed from list");
						for (User user : c.getRemoved()) {
							userRepository.delete(user);
						}
					}
				}
			}

		});
	}

	static UserTypeRepository userTypeRepository = new UserTypeRepositoryJPA();
	static ObservableList<UserType> types = FXCollections.observableArrayList(userTypeRepository.readAll());

	static QuestionRepository questionRepository = new QuestionRepositoryJPA();
	static ObservableList<Question> questions = FXCollections.observableArrayList(questionRepository.readAll());

	static QuestionQuizGroupRepository groupRepository = new QuestionQuizGroupRepositoryJPA();
	static ObservableList<QuestionQuizGroup> groups = FXCollections.observableArrayList(groupRepository.readAll());

	static AnswerRepository answerRepository = new AnswerRepositoryJPA();
	static ObservableList<Answer> answers = FXCollections.observableArrayList(answerRepository.readAll());
	static {
		questions.addListener(new ListChangeListener<Question>() {

			@Override
			public void onChanged(Change<? extends Question> c) {
				while (c.next()) {
					if (c.wasReplaced()) {
						System.err.println("Question updated: " + c.wasUpdated());
						for (Question question : c.getAddedSubList()) {
							questionRepository.update(question);
						}

					} else if (c.wasAdded()) {
						System.err.println("Question was added to list");
						for (Question question : c.getAddedSubList()) {
							questionRepository.add(question);
						}
					} else if (c.wasRemoved()) {
						System.err.println("Question was removed from list");
						for (Question question : c.getRemoved()) {
							questionRepository.delete(question);
						}
					}
				}
			}

		});
	}
	static {
		answers.addListener(new ListChangeListener<Answer>() {

			@Override
			public void onChanged(Change<? extends Answer> c) {
				while (c.next()) {
					if (c.wasReplaced()) {
						System.err.println("Question updated: " + c.wasUpdated());
						for (Answer answer : c.getAddedSubList()) {
							answerRepository.update(answer);
						}

					} else if (c.wasAdded()) {
						System.err.println("Question was added to list");
						for (Answer answer : c.getAddedSubList()) {
							answerRepository.add(answer);
						}
					} else if (c.wasRemoved()) {
						System.err.println("Question was removed from list");
						for (Answer answer : c.getRemoved()) {
							answerRepository.delete(answer);
						}
					}
				}
			}

		});
	}

	static {
		groups.addListener(new ListChangeListener<QuestionQuizGroup>() {

			@Override
			public void onChanged(Change<? extends QuestionQuizGroup> c) {
				while (c.next()) {
					if (c.wasReplaced()) {
						System.err.println("Group updated: " + c.wasUpdated());
						for (QuestionQuizGroup group : c.getAddedSubList()) {
							groupRepository.update(group);
						}

					} else if (c.wasAdded()) {
						System.err.println("Group was added to list");
						for (QuestionQuizGroup group : c.getAddedSubList()) {
							groupRepository.add(group);
						}
					} else if (c.wasRemoved()) {
						System.err.println("Group was removed from list");
						for (QuestionQuizGroup group : c.getRemoved()) {
							groupRepository.delete(group);
						}
					}
				}
			}

		});
	}
	static ObjectProperty<Question> selectedQuestionProperty = new SimpleObjectProperty<Question>();

	public void openStageFromFXML(String pathToFXML) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource(pathToFXML));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
