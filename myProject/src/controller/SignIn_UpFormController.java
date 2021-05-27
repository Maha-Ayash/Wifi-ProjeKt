package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Type;
import model.User;
import model.UserType;


public class SignIn_UpFormController extends BaseController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane quizProgramAnchorPane;

	@FXML
	private Button signInUpAnchorPane;

	@FXML
	private AnchorPane loginAnchorPane;

	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Button loginButton;

	@FXML
	private AnchorPane signUpAnchorPane;

	@FXML
	private TextField registerUsernameTextField;

	@FXML
	private TextField registerPasswordTextFiel;

	@FXML
	private TextField confirmPasswordTextField;

	@FXML
	private Button signupButton;

	private Alert a = new Alert(AlertType.NONE);

	static boolean confirmUser;

	@FXML
	void loginpaneShow(ActionEvent event) {
		loginAnchorPane.setVisible(true);
		signUpAnchorPane.setVisible(false);

	}

	@FXML
	void signuppaneShow(ActionEvent event) {
		loginAnchorPane.setVisible(false);
		signUpAnchorPane.setVisible(true);
	}

	@FXML
	void handleLoginButtonPressed(ActionEvent event) throws IOException {


		System.out.println(confirmUser = check());

		if (check()) {
			a.setAlertType(AlertType.CONFIRMATION);
			a.setContentText("Username Und Password sind Korrekt!");
			a.showAndWait();
			openStageFromFXML(PATHTOFXML_START_FORM);
			System.out.println(users);
			System.out.println("Username And Password is Correct!");

		} else {
			a.setAlertType(AlertType.CONFIRMATION);
			a.setContentText("Username Oder Password ist Falsch!");
			a.showAndWait();
			openStageFromFXML(PATHTOFXML_SIGNIN_UP_FORM);
			System.out.println("Username Or Password is Wrong!");
		}
        //Stage = Child class from window and getWindow() actually brings Stage (Polymorphism) 
		Stage currentStage = (Stage) loginButton.getScene().getWindow();
		currentStage.close();
	}

	@FXML
	void handleSignupButtonPressed(ActionEvent event) throws IOException {

		String username = registerUsernameTextField.getText();
		String password = registerPasswordTextFiel.getText();
		String rPassword = confirmPasswordTextField.getText();

		
		

		// use Stream and LAMBDA
		UserType userType = types.stream().filter(type -> Type.USER.equals(type.getType())).findFirst().get();
		System.out.println(userType);
		User newUser = new User(username,password,userType);
		System.out.println();

		if (username != null && password != null && rPassword.equals(password)) {
			users.add(newUser);
			System.out.println("New User registered");
			signupButton.getScene().getWindow().hide();
			openStageFromFXML(PATHTOFXML_SIGNIN_UP_FORM);
		} else {
			signUpAnchorPane.setVisible(true);
			System.out.println("Error in confirm Password");
		}

	}

	@FXML
	void initialize() {
		assert quizProgramAnchorPane != null
				: "fx:id=\"quizProgramAnchorPane\" was not injected: check your FXML file 'SignIn_UpForm.fxml'.";
		assert signInUpAnchorPane != null
				: "fx:id=\"signInUpAnchorPane\" was not injected: check your FXML file 'SignIn_UpForm.fxml'.";
		assert loginAnchorPane != null
				: "fx:id=\"loginAnchorPane\" was not injected: check your FXML file 'SignIn_UpForm.fxml'.";
		assert usernameTextField != null
				: "fx:id=\"usernameTextField\" was not injected: check your FXML file 'SignIn_UpForm.fxml'.";
		assert passwordTextField != null
				: "fx:id=\"passwordTextField\" was not injected: check your FXML file 'SignIn_UpForm.fxml'.";
		assert loginButton != null
				: "fx:id=\"loginButton\" was not injected: check your FXML file 'SignIn_UpForm.fxml'.";
		assert signUpAnchorPane != null
				: "fx:id=\"signUpAnchorPane\" was not injected: check your FXML file 'SignIn_UpForm.fxml'.";
		assert registerUsernameTextField != null
				: "fx:id=\"registerUsernameTextField\" was not injected: check your FXML file 'SignIn_UpForm.fxml'.";
		assert registerPasswordTextFiel != null
				: "fx:id=\"registerPasswordTextFiel\" was not injected: check your FXML file 'SignIn_UpForm.fxml'.";
		assert confirmPasswordTextField != null
				: "fx:id=\"confirmPasswordTextField\" was not injected: check your FXML file 'SignIn_UpForm.fxml'.";
		assert signupButton != null
				: "fx:id=\"signupButton\" was not injected: check your FXML file 'SignIn_UpForm.fxml'.";

	}
	
	public boolean check() {
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		if (userRepository.read(username, password).isPresent()) {
			loginUsername = (String) (userRepository.read(username, password)).get().getUserName();
			typeLoginUser = (UserType)(userRepository.read(username, password)).get().getType();
			
			return true;
		}
		return false;
	}
}
