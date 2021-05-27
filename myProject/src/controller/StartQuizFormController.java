package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StartQuizFormController extends BaseController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane startAnchorPane;

	@FXML
	private Button startQuizButton;

	@FXML
	private Button backButton;

	@FXML
	void handleStartButtonPressed(ActionEvent event) {
		loadQuizFormSceneAndStartQuiz();
	}

	@FXML
	void handlebackButtonPressed(ActionEvent event) throws IOException {
		openStageFromFXML(PATHTOFXML_QUIZ_TYPE_FORM);
		Stage currentStage = (Stage) backButton.getScene().getWindow();

		currentStage.close();
	}

	@FXML
	void initialize() {
		assert startAnchorPane != null
				: "fx:id=\"startAnchorPane\" was not injected: check your FXML file 'StartQuizForm.fxml'.";
		assert startQuizButton != null
				: "fx:id=\"startQuizButton\" was not injected: check your FXML file 'StartQuizForm.fxml'.";
		assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'StartQuizForm.fxml'.";

	}

	private void loadQuizFormSceneAndStartQuiz() {
		try {
			Stage currentStage = (Stage) startQuizButton.getScene().getWindow();
			currentStage.close();

			// Load second scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource(PATHTOFXML_QUIZ_FORM));
			Parent root = loader.load();

			// Get controller of second scene
			QuizFormController quizFormController = loader.getController();

			// Pass whatever data you want. You can have multiple method calls here
			quizFormController.startQuiz();

			// Show second scene in new window
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Quiz");
			stage.show();
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
