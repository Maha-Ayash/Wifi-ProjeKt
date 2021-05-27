package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Type;


public class StartFormController extends BaseController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane startAnchorPane;

	@FXML
	private Label signInUserNameTextField;

	@FXML
	private Button openQuizButton;

	@FXML
	private Button openQuestionButton;

	@FXML
	private Button exitButton;

	@FXML
	void handleExitButtonPressed(ActionEvent event) throws IOException {
		exitButton.getScene().getWindow().hide();
		openStageFromFXML(PATHTOFXML_SIGNIN_UP_FORM);
	}

	@FXML
	void handleOpenQuestionPressd(ActionEvent event) throws IOException {
		openQuestionButton.getScene().getWindow().hide();
		openStageFromFXML(PATHTOFXML_ADMIN_FORM);

	}

	@FXML
	void handleOpenQuizButtonPressed(ActionEvent event) throws IOException {
		openQuizButton.getScene().getWindow().hide();
		openStageFromFXML(PATHTOFXML_QUIZ_TYPE_FORM);
	}

	@FXML
	void initialize() {
		assert startAnchorPane != null : "fx:id=\"stratForm\" was not injected: check your FXML file 'StartForm.fxml'.";
		assert signInUserNameTextField != null
				: "fx:id=\"signInUserNameTextField\" was not injected: check your FXML file 'StartForm.fxml'.";
		assert openQuizButton != null
				: "fx:id=\"openQuizButton\" was not injected: check your FXML file 'StartForm.fxml'.";
		assert openQuestionButton != null
				: "fx:id=\"openQuestionButton\" was not injected: check your FXML file 'StartForm.fxml'.";
		assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'StartForm.fxml'.";

		signInUserNameTextField.setFont(new Font("System", 25));
		signInUserNameTextField.setTextFill(Color.WHITE);
		signInUserNameTextField.setText(loginUsername);

		if (typeLoginUser.getType().equals(Type.ADMIN)) {
			openQuestionButton.visibleProperty().set(true);
		}
		else {
			openQuestionButton.visibleProperty().set(false);
		}
		
	}

}
