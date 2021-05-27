package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class QuizGroupFormController extends BaseController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane quizTypeAnchorPane;

	@FXML
	private Button quizSWButton;

	@FXML
	private Button quizOOPButton;

	@FXML
	private Button quizJavaButton;

	@FXML
	private Button quizMixButton;

	@FXML 
	private Button backbutton;

	@FXML
	void initialize() {
		assert quizTypeAnchorPane != null
				: "fx:id=\"quizTypeAnchorPane\" was not injected: check your FXML file 'QuizTypeForm.fxml'.";
		assert quizSWButton != null
				: "fx:id=\"quizSWButton\" was not injected: check your FXML file 'QuizTypeForm.fxml'.";
		assert quizOOPButton != null
				: "fx:id=\"quizOOPButton\" was not injected: check your FXML file 'QuizTypeForm.fxml'.";
		assert quizJavaButton != null
				: "fx:id=\"quizJavaButton\" was not injected: check your FXML file 'QuizTypeForm.fxml'.";
		assert quizMixButton != null
				: "fx:id=\"quizMixQuizButton\" was not injected: check your FXML file 'QuizTypeForm.fxml'.";

		quizJavaButton.setOnAction(e -> {
			quizJavaButton.getScene().getWindow().hide();
			quizTypeText = "JAVA";
			try {
				openStageFromFXML(PATHTOFXML_START_QUIZ_FORM);
				Stage currentStage = (Stage) quizJavaButton.getScene().getWindow();
				currentStage.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		quizOOPButton.setOnAction(e -> {
			quizOOPButton.getScene().getWindow().hide();
			quizTypeText = "OOP";
			try {
				openStageFromFXML(PATHTOFXML_START_QUIZ_FORM);
				Stage currentStage = (Stage) quizJavaButton.getScene().getWindow();
				currentStage.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		quizSWButton.setOnAction(e -> {
			quizSWButton.getScene().getWindow().hide();
			quizTypeText = "SWE";
			try {
				openStageFromFXML(PATHTOFXML_START_QUIZ_FORM);
				Stage currentStage = (Stage) quizJavaButton.getScene().getWindow();
				currentStage.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		quizMixButton.setOnAction(e -> {
			quizMixButton.getScene().getWindow().hide();
			quizTypeText = "Mix Quiz";
			try {
				openStageFromFXML(PATHTOFXML_START_QUIZ_FORM);
				Stage currentStage = (Stage) quizJavaButton.getScene().getWindow();
				currentStage.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}

	@FXML public void handleBackButtonPressed(ActionEvent event) throws IOException {
		backbutton.getScene().getWindow().hide();
		openStageFromFXML(PATHTOFXML_START_FORM);
	}
}