package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Answer;
import model.Question;

public class QuizFormController extends BaseController {

	@FXML
	private AnchorPane quizAnchorPane;

	@FXML
	private Label quizTypeLabel;

	@FXML
	private Label questionTextLabel;

	@FXML
	private CheckBox firstAnswerCheckBox;

	@FXML
	private CheckBox secondAnswerCheckBox;

	@FXML
	private CheckBox thirdAnswerCheckBox;

	@FXML
	private Button saveAndContinueButton;

	@FXML
	private Button playAgainButton;

	@FXML
	private Button exitQuizButton;

	@FXML
	private Label firstAnswerLabel;

	@FXML
	private Label secondAnswerLabel;

	@FXML
	private Label thirdAnswerLabel;

	@FXML
	private Label counterLabel;

	static int index;
	static int score = 0;
	static Question question;
	static List<Question> questionByGroup;
	static List<Answer> answersCurrentQuestion;
	Alert a = new Alert(AlertType.NONE);
	static int counter = 1;
	static int answerCounter = 0;

	@FXML
	void handlePlayAgainButtonPreesed(ActionEvent event) throws IOException {
		score = 0;
	    answerCounter = 0;
	    firstAnswerCheckBox.setSelected(false);
		secondAnswerCheckBox.setSelected(false);
		thirdAnswerCheckBox.setSelected(false);
		startQuiz();
		playAgainButton.setDisable(true);

	}

	@FXML
	void handleSaveAndContinuePressed(ActionEvent event) {
		checkAnswers();
		anotherQuestion();
		System.out.println("the end score :" + score);

	}

	@FXML
	void handleExitQuizButtonPressed(ActionEvent event) throws IOException {
		exitQuizButton.getScene().getWindow().hide();

		openStageFromFXML(PATHTOFXML_START_FORM);
	}

	@FXML
	void initialize() {

		assert quizAnchorPane != null
				: "fx:id=\"quizAnchorPane\" was not injected: check your FXML file 'QuizForm.fxml'.";
		assert quizTypeLabel != null
				: "fx:id=\"quizTypeLabel\" was not injected: check your FXML file 'QuizForm.fxml'.";
		assert questionTextLabel != null
				: "fx:id=\"questionTextField\" was not injected: check your FXML file 'QuizForm.fxml'.";
		assert firstAnswerCheckBox != null
				: "fx:id=\"firstAnswerCheckBox\" was not injected: check your FXML file 'QuizForm.fxml'.";
		assert firstAnswerLabel != null
				: "fx:id=\"firstAnswerLabel\" was not injected: check your FXML file 'QuizForm.fxml'.";
		assert secondAnswerCheckBox != null
				: "fx:id=\"secondAnswerCheckBox\" was not injected: check your FXML file 'QuizForm.fxml'.";
		assert secondAnswerLabel != null
				: "fx:id=\"secondAnswerLabel\" was not injected: check your FXML file 'QuizForm.fxml'.";
		assert thirdAnswerCheckBox != null
				: "fx:id=\"thirdAnswerCheckBox\" was not injected: check your FXML file 'QuizForm.fxml'.";
		assert thirdAnswerLabel != null
				: "fx:id=\"thirdAnswerLabel\" was not injected: check your FXML file 'QuizForm.fxml'.";
		assert saveAndContinueButton != null
				: "fx:id=\"saveAndContinueButton\" was not injected: check your FXML file 'QuizForm.fxml'.";
		assert playAgainButton != null
				: "fx:id=\"playAgainButton\" was not injected: check your FXML file 'QuizForm.fxml'.";
		assert exitQuizButton != null
				: "fx:id=\"exitQuizButton\" was not injected: check your FXML file 'QuizForm.fxml'.";

		if (quizTypeText != "Mix Quiz") {
			quizTypeLabel.setText(quizTypeText + " Quiz");
		} else {
			quizTypeLabel.setText(quizTypeText);
		}
		quizTypeLabel.setFont(new Font("System", 20));
		quizTypeLabel.setTextFill(Color.WHITE);
		saveAndContinueButton.setDisable(true);
		playAgainButton.setDisable(true);

	}

	public List<Question> collectQuestionGroup(String groupName) {

		List<Question> groupedQuestion = questions.stream()
				.filter(q -> q.getQuestionQuizGroup().toString().equals(groupName)).collect(Collectors.toList());
		Collections.shuffle(groupedQuestion);
		return groupedQuestion;
	}

	void checkAnswers() {
		System.out.println("Befor Check methode" + index);
		List<Boolean> correctAnswers = new ArrayList<Boolean>();
		Question currentQuestion = new Question();

		currentQuestion = questionByGroup.get(index); // the actual Question
		System.out.println("the current Question" + currentQuestion);

		correctAnswers.add(firstAnswerCheckBox.isSelected() == currentQuestion.getAnswers().get(0).isCorrect());
		correctAnswers.add(secondAnswerCheckBox.isSelected() == currentQuestion.getAnswers().get(1).isCorrect());
		correctAnswers.add(thirdAnswerCheckBox.isSelected() == currentQuestion.getAnswers().get(2).isCorrect());
		System.out.println(" The Correct Answer are" + correctAnswers);

		if (correctAnswers.stream().allMatch(a -> a.equals(true))) {
			score += 10;
			answerCounter++;
			System.out.println("The recht answers" + answerCounter);
		}
		index++;
		System.out.println("After Checked :" + score);
		System.out.println("After Check methode" + index);
	}

	public Question anotherQuestion() {

		System.out.println("antherQuestion methode" + index);
		if (index < 3) {
			System.out.println(questionByGroup.get(index).toString());
			counter++;
			firstAnswerCheckBox.setSelected(false);
			secondAnswerCheckBox.setSelected(false);
			thirdAnswerCheckBox.setSelected(false);

			answersCurrentQuestion = questionByGroup.get(index).getAnswers();
			System.out.println("Answer befor shuffel" + answersCurrentQuestion);
			Collections.shuffle(answersCurrentQuestion);
			System.out.println("Answer after shuffel" + answersCurrentQuestion);

			questionTextLabel.setText(questionByGroup.get(index).getQuestion());

			firstAnswerLabel.setText(answersCurrentQuestion.get(0).getAnswer());

			secondAnswerLabel.setText(answersCurrentQuestion.get(1).getAnswer());

			thirdAnswerLabel.setText(answersCurrentQuestion.get(2).getAnswer());
		} else {
			saveAndContinueButton.setDisable(true);
			playAgainButton.setDisable(false);
			System.out.println("The Quiz is done!");
			String confirmText = "Ok";
			String title = "Ende Quiz!";
			String prompt; 
			List<String> result = Arrays.asList("Super!", "Gute!", "Leider!");
			if(score == 30) {
				prompt = result.get(0) + "\n" + "Sie haben " + answerCounter + " von 3 richtig beantwortet"+ "\n" 
			              + "Das Quiz Ergebnis: " + score ;
			}else if(score == 20 || score ==10){
				prompt = result.get(1) + "\n" + "Sie haben " + answerCounter + " von 3 richtig beantwortet"+ "\n" 
			              + "Das Quiz Ergebnis: " + score ;
			}
			else {
				prompt = result.get(2) + "\n" + "Sie haben " + answerCounter + " von 3 richtig beantwortet"+ "\n" 
			              + "Das Quiz Ergebnis: " + score ;
			}
					
			ButtonType ok = new ButtonType(confirmText, ButtonData.OK_DONE);

			Alert alert = new Alert(AlertType.NONE, "", ok);
			alert.getDialogPane().getStyleClass();
			alert.getDialogPane().getStyleClass().add("dialog-pane");

			alert.setTitle(title);
			alert.setContentText(prompt);

			alert.showAndWait();

		}
		counterLabel.setText("3/" + counter);
		return question;
	}

	public void startQuiz() {
		System.out.println("Start methode" + index);
		index = 0;
		score = 0;
		counter = 1;
		System.out.println(quizTypeText);

		if (quizTypeText != "Mix Quiz") {
			questionByGroup = collectQuestionGroup(quizTypeText);
			System.out.println("Group Question : " + questionByGroup);

		} else {
			questionByGroup = questions;
			System.out.println("Mix Question : " + questionByGroup);
		}
		System.out.println("Start" + questionByGroup);
		System.out.println(" ONE" + questionByGroup.get(index).toString());

		questionTextLabel.setText(questionByGroup.get(index).getQuestion());
		answersCurrentQuestion = questionByGroup.get(index).getAnswers();
		System.out.println("Answer befor shuffel" + answersCurrentQuestion);
		Collections.shuffle(answersCurrentQuestion);
		System.out.println("Answer after shuffel" + answersCurrentQuestion);

		firstAnswerLabel.setText(answersCurrentQuestion.get(0).getAnswer());

		secondAnswerLabel.setText(answersCurrentQuestion.get(1).getAnswer());

		thirdAnswerLabel.setText(answersCurrentQuestion.get(2).getAnswer());
		counterLabel.setText("3/" + counter);
		saveAndContinueButton.setDisable(false);
		System.out.println("After Start methode" + index);
	}

}
