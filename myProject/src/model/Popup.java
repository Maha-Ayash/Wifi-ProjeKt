package model;

import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {
	

	public static void questionMessage() throws FileNotFoundException {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);

		popupwindow.setTitle("Achtung!");

		Label label = new Label(" Die Frage existiert schon! Sie können für die gleichen Frage nur Aktualisieren."+ "\n");
		label.setTextFill(Color.BLACK);
		label.setFont(new Font("System", 14));

		Button button = new Button("Ok");

		button.setOnAction(e -> popupwindow.close());

		VBox layout = new VBox(10);

		layout.getChildren().addAll(label, button);

		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout, 500, 100);

		popupwindow.setScene(scene);

		popupwindow.showAndWait();
	}

	public static void answerMessage() throws FileNotFoundException {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);

		popupwindow.setTitle("Achtung!");

		Label label = new Label("Sie haben ZWEI oder DREI  Antworten ,die gleiche sind, hinzugefügt."+ "\n");
		label.setTextFill(Color.BLACK);
		label.setFont(new Font("System", 14));

		Button button = new Button("Ok");

		button.setOnAction(e -> popupwindow.close());

		VBox layout = new VBox(10);

		layout.getChildren().addAll(label, button);

		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout, 450, 100);

		popupwindow.setScene(scene);

		popupwindow.showAndWait();
	}
	public static void checkBoxMessage() throws FileNotFoundException {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);

		popupwindow.setTitle("Achtung!");

		Label label = new Label("Sie müssen mindesten ein richtige Antwort auswählen." + "\n");
		label.setTextFill(Color.BLACK);
		label.setFont(new Font("System", 14));

		Button button = new Button("Ok");

		button.setOnAction(e -> popupwindow.close());

		VBox layout = new VBox(10);

		layout.getChildren().addAll(label, button);

		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout, 450, 100);

		popupwindow.setScene(scene);

		popupwindow.showAndWait();
	}


}
