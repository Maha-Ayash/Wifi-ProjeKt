package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import model.Answer;
import model.Popup;
import model.Question;
import model.QuestionQuizGroup;

public class QuestionFormController extends BaseController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane questionAnchorPane;

	@FXML
	private ComboBox<QuestionQuizGroup> questionGroupComboBox;

	@FXML
	private TextArea newQuestionTextArea;

	@FXML
	private CheckBox firstAnswerCheckBox;

	@FXML
	private TextArea firstAnswerTextArea;

	@FXML
	private CheckBox secondAnswerCheckBox;

	@FXML
	private TextArea secondAnswerTextArea;

	@FXML
	private CheckBox thirdAnswerCheckBox;

	@FXML
	private TextArea thirdAnswerTextArea;

	@FXML
	private Button addQuestionButton;

	@FXML
	private Button updateQuestionButton;

	@FXML
	private Button deleteQuestionButton;

	@FXML
	private Button exitQeustionButton;

	@FXML
	void handleAddQuestionButtonPressed(ActionEvent event) throws FileNotFoundException {

		Question question = new Question();

		String questionText = newQuestionTextArea.getText();

		Answer firstAnswer = new Answer();
		firstAnswer.setAnswer(firstAnswerTextArea.getText().toString());

		Answer secondAnswer = new Answer();
		secondAnswer.setAnswer(secondAnswerTextArea.getText().toString());

		Answer thirdAnswer = new Answer();
		thirdAnswer.setAnswer(thirdAnswerTextArea.getText().toString());

		QuestionQuizGroup group = questionGroupComboBox.getSelectionModel().getSelectedItem();
		System.out.println("The QUIZ GROUP:" + group.toString());
		if (firstAnswerCheckBox.isSelected()) {
			firstAnswer.setCorrect(true);
		} else {
			firstAnswer.setCorrect(false);
		}
		if (secondAnswerCheckBox.isSelected()) {
			secondAnswer.setCorrect(true);
		} else {
			secondAnswer.setCorrect(false);
		}
		if (thirdAnswerCheckBox.isSelected()) {
			thirdAnswer.setCorrect(true);
		} else {
			thirdAnswer.setCorrect(false);
		}

		List<Answer> answers = new ArrayList<Answer>();
		answers.add(firstAnswer);
		answers.add(secondAnswer);
		answers.add(thirdAnswer);

		if (!questionText.isEmpty() && !answers.isEmpty() && !group.getQuestionQuizGroup().isEmpty()
				&& !checkAddQuestion(questionText) && !checkAddAnswer(answers) && checkAtlistOneCheckBoxSelected()) {

			question.setQuestion(questionText);
			question.setAnswers(answers);
			question.setQuestionQuizGroup(group);
			questions.add(question);
			clearQuestionForm();
		}
		else {
			if (checkAddQuestion(questionText)) {
				Popup.questionMessage();
			} else if (checkAddAnswer(answers)) {
				Popup.answerMessage();
			} else if (!checkAtlistOneCheckBoxSelected()) {
				Popup.checkBoxMessage();
			}
		}
		System.out.println(question);
	}

	@FXML
	void handleDeletQuestionButtonPressed(ActionEvent event) {

		clearQuestionForm();

	}

	@FXML
	void handleUpdateQuestionButtonPressed(ActionEvent event) throws FileNotFoundException {

//		System.out.println(selectedQuestionProperty);
		Question question = selectedQuestionProperty.get();

		int index = questions.indexOf(question);

		question.setQuestion(newQuestionTextArea.getText());
		question.getAnswers().get(0).setAnswer(firstAnswerTextArea.getText());
		question.getAnswers().get(0).setCorrect(firstAnswerCheckBox.isSelected());
		question.getAnswers().get(1).setAnswer(secondAnswerTextArea.getText());
		question.getAnswers().get(1).setCorrect(secondAnswerCheckBox.isSelected());
		question.getAnswers().get(2).setAnswer(thirdAnswerTextArea.getText());
		question.getAnswers().get(2).setCorrect(thirdAnswerCheckBox.isSelected());
		question.setQuestionQuizGroup(questionGroupComboBox.getSelectionModel().getSelectedItem());

		if (!checkAddAnswer(question.getAnswers()) && checkAtlistOneCheckBoxSelected()) {
			questions.set(index, question);
			clearQuestionForm();

		} else {
			if (checkAddAnswer(question.getAnswers())) {
				Popup.answerMessage();
			} else if (!checkAtlistOneCheckBoxSelected()) {
				Popup.checkBoxMessage();
			}
		}
	}

	@FXML
	public void handleExitAdminAreaButtonPressed(ActionEvent event) throws IOException {

		exitQeustionButton.getScene().getWindow().hide();
		openStageFromFXML(PATHTOFXML_START_FORM);

	}

	@FXML
	void initialize() {
		assert questionAnchorPane != null
				: "fx:id=\"questionAnchorPane\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert questionGroupComboBox != null
				: "fx:id=\"questionGroupComboBox\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert newQuestionTextArea != null
				: "fx:id=\"newQuestionTextArea\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert firstAnswerCheckBox != null
				: "fx:id=\"firstAnswerCheckBox\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert firstAnswerTextArea != null
				: "fx:id=\"firstAnswerTextArea\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert secondAnswerCheckBox != null
				: "fx:id=\"secondAnswerCheckBox\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert secondAnswerTextArea != null
				: "fx:id=\"secondAnswerTextArea\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert thirdAnswerCheckBox != null
				: "fx:id=\"thirdAnswerCheckBox\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert thirdAnswerTextArea != null
				: "fx:id=\"thirdAnswerTextArea\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert addQuestionButton != null
				: "fx:id=\"addQuestionButton\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert updateQuestionButton != null
				: "fx:id=\"updateQuestionButton\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert deleteQuestionButton != null
				: "fx:id=\"deleteQuestionButton\" was not injected: check your FXML file 'QuestionForm.fxml'.";
		assert exitQeustionButton != null
				: "fx:id=\"exitQeustionButton\" was not injected: check your FXML file 'QuestionForm.fxml'.";

		addQuestionButton.disableProperty().bind(newQuestionTextArea.textProperty().isEmpty().or(firstAnswerTextArea
				.textProperty().isEmpty()
				.or(secondAnswerTextArea.textProperty().isEmpty().or(thirdAnswerTextArea.textProperty().isEmpty()).or(selectedQuestionProperty.isNotNull()))));

		updateQuestionButton.setDisable(true);
//		updateQuestionButton.disableProperty().bind(selectedQuestionProperty.isNotNull());
		selectedQuestionProperty.addListener(new ChangeListener<Question>() {

			@Override
			public void changed(ObservableValue<? extends Question> observable, Question oldValue, Question newValue) {
				if (newValue != null) {
					updateQuestionInForm(newValue);
					updateQuestionButton.setDisable(false);

				} else {
				
					clearQuestionForm();
				
				}
			}
		});

		questionGroupComboBox.setItems(groups);
	}

	// Empty all text fields
	private void clearQuestionForm() {
		newQuestionTextArea.clear();
		firstAnswerTextArea.clear();
		secondAnswerTextArea.clear();
		thirdAnswerTextArea.clear();
		questionGroupComboBox.getSelectionModel().clearSelection();
		firstAnswerCheckBox.setSelected(false);
		secondAnswerCheckBox.setSelected(false);
		thirdAnswerCheckBox.setSelected(false);
		updateQuestionButton.setDisable(true);
		addQuestionButton.disableProperty().bind(newQuestionTextArea.textProperty().isEmpty().or(firstAnswerTextArea
				.textProperty().isEmpty()
				.or(secondAnswerTextArea.textProperty().isEmpty().or(thirdAnswerTextArea.textProperty().isEmpty()).or(selectedQuestionProperty.isNull()))));
		
	
	}

	private void updateQuestionInForm(Question question) {

		newQuestionTextArea.setText(question.getQuestion());
		firstAnswerTextArea.setText(question.getAnswers().get(0).getAnswer());
		secondAnswerTextArea.setText(question.getAnswers().get(1).getAnswer());
		thirdAnswerTextArea.setText(question.getAnswers().get(2).getAnswer());
		questionGroupComboBox.getSelectionModel().select(question.getQuestionQuizGroup());
		if (question.getAnswers().get(0).isCorrect()) {
			firstAnswerCheckBox.setSelected(true);
		} else {
			firstAnswerCheckBox.setSelected(false);
		}
		if (question.getAnswers().get(1).isCorrect()) {
			secondAnswerCheckBox.setSelected(true);
		} else {
			secondAnswerCheckBox.setSelected(false);
		}
		if (question.getAnswers().get(2).isCorrect()) {
			thirdAnswerCheckBox.setSelected(true);
		} else {
			thirdAnswerCheckBox.setSelected(false);
		}
		
	}

	private boolean checkAddQuestion(String newQuestion) {

		newQuestion = newQuestionTextArea.getText().toString();
		if (questionRepository.read(newQuestion).isPresent() && (questionRepository.read(newQuestion).get()
				.getQuestion().toString().equalsIgnoreCase(newQuestion))) {
			return true;
		}
		return false;
	}

	private boolean checkAddAnswer(List<Answer> addAnswer) {

		boolean check = false;
		for (int i = 0; i < addAnswer.size() - 1; i++) {
			for (int j = i + 1; j < addAnswer.size(); j++) {
				if (addAnswer.get(i).getAnswer().toString().equalsIgnoreCase(addAnswer.get(j).getAnswer().toString())) {

					check = true;
				}
			}
		}
		return check;
	}

	private boolean checkAtlistOneCheckBoxSelected() {

		if (firstAnswerCheckBox.isSelected() || secondAnswerCheckBox.isSelected() || thirdAnswerCheckBox.isSelected()) {
			return true;
		} else {
			return false;
		}
	}
}
