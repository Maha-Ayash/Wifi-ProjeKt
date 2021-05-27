package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import model.Question;
import javafx.scene.layout.AnchorPane;

public class QuestionTablesFormController extends BaseController {

	@FXML
	private AnchorPane tabAnchorPane;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TabPane questionTableTabPane;

	@FXML
	private Tab softwareEngineeringTab;

	@FXML
	private TableView<Question> softwareEngineeringTable;

	@FXML
	private TableColumn<Question, String> softwareColumn;

	@FXML
	private TableColumn<Question, String> deleteColumnSWE;

	@FXML
	private Tab objectOrientedProgrammingTab;

	@FXML
	private TableView<Question> ObjectOrientedProgrammingTable;

	@FXML
	private TableColumn<Question, String> objectOrientedProgrammingColumn;

	@FXML
	private TableColumn<Question, String> deleteColumnOOP;

	@FXML
	private Tab javaTab;

	@FXML
	private TableView<Question> javaTable;

	@FXML
	private TableColumn<Question, String> javaColumn;

	@FXML
	private TableColumn<Question, String> deleteColumnJAVA;
	
	

	@FXML
	void javaTabSelection(Event event) throws IOException {

		if (javaTab.isSelected()) {
			questionTableTabPane.getChildrenUnmodifiable();

		}
	}

	@FXML
	void objectOrientedProgrammingTabSelection(Event event) throws IOException {
		if (objectOrientedProgrammingTab.isSelected()) {
			questionTableTabPane.getChildrenUnmodifiable();

		}
	}

	@FXML
	void softwareEngineeringTabSelection(Event event) {

		if (softwareEngineeringTab.isSelected()) {
			questionTableTabPane.getChildrenUnmodifiable();
		}

	}

	@FXML
	void initialize() {
		assert questionTableTabPane != null
				: "fx:id=\"questionTableTabPane\" was not injected: check your FXML file 'QuestionTablesForm.fxml'.";
		assert softwareEngineeringTab != null
				: "fx:id=\"softwareEngineeringTab\" was not injected: check your FXML file 'QuestionTablesForm.fxml'.";
		assert softwareEngineeringTable != null
				: "fx:id=\"softwareEngineeringTable\" was not injected: check your FXML file 'QuestionTablesForm.fxml'.";
		assert softwareColumn != null
				: "fx:id=\"softwareColumn\" was not injected: check your FXML file 'QuestionTablesForm.fxml'.";
		assert objectOrientedProgrammingTab != null
				: "fx:id=\"objectOrientedProgrammingTab\" was not injected: check your FXML file 'QuestionTablesForm.fxml'.";
		assert ObjectOrientedProgrammingTable != null
				: "fx:id=\"ObjectOrientedProgrammingTable\" was not injected: check your FXML file 'QuestionTablesForm.fxml'.";
		assert objectOrientedProgrammingColumn != null
				: "fx:id=\"objectOrientedProgrammongColumn\" was not injected: check your FXML file 'QuestionTablesForm.fxml'.";
		assert javaTab != null : "fx:id=\"javaTab\" was not injected: check your FXML file 'QuestionTablesForm.fxml'.";
		assert javaTable != null
				: "fx:id=\"javaTable\" was not injected: check your FXML file 'QuestionTablesForm.fxml'.";
		assert javaColumn != null
				: "fx:id=\"javaColumn\" was not injected: check your FXML file 'QuestionTablesForm.fxml'.";

		long javaGroupId = groups.stream().filter(type -> type.getQuestionQuizGroup().equals("JAVA")).findFirst().get()
				.getId();
		System.out.println(javaGroupId);
		long sweGroupId = groups.stream().filter(type -> type.getQuestionQuizGroup().equals("SWE")).findFirst().get()
				.getId();
		System.out.println(sweGroupId);
		long oopGroupId = groups.stream().filter(type -> type.getQuestionQuizGroup().equals("OOP")).findFirst().get()
				.getId();
		System.out.println(oopGroupId);

		softwareEngineeringTable
				.setItems(questions.sorted().filtered(g -> g.getQuestionQuizGroup().getId() == sweGroupId));
		softwareColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getQuestion()));

		javaTable.setItems(questions.sorted().filtered(g -> g.getQuestionQuizGroup().getId() == javaGroupId));
		javaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getQuestion()));

		ObjectOrientedProgrammingTable
				.setItems(questions.sorted().filtered(g -> g.getQuestionQuizGroup().getId() == oopGroupId));
		objectOrientedProgrammingColumn
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getQuestion()));

		softwareEngineeringTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Question>() {

			@Override
			public void changed(ObservableValue<? extends Question> observable, Question oldValue, Question newValue) {

				System.out.println("Table Selection Changed called: " + newValue);
				selectedQuestionProperty.set(newValue);
			}
		});

		javaTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Question>() {

			@Override
			public void changed(ObservableValue<? extends Question> observable, Question oldValue, Question newValue) {

				System.out.println("Table Selection Changed called: " + newValue);
				selectedQuestionProperty.set(newValue);
			}
		});

		ObjectOrientedProgrammingTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Question>() {

					@Override
					public void changed(ObservableValue<? extends Question> observable, Question oldValue,
							Question newValue) {

						System.out.println("Table Selection Changed called: " + newValue);
						selectedQuestionProperty.set(newValue);
				
					}
				});

		// Delete-Button every Cell
		var deleteCallBack = new Callback<TableColumn<Question, String>, TableCell<Question, String>>() {

			// always call it when it adds a new Cell in the table
			@Override
			public TableCell<Question, String> call(TableColumn<Question, String> param) {

				TableCell<Question, String> cell = new TableCell<Question, String>() {

					Button deleteButton = new Button("Löschen");

					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);

						} else {
							// call when Button pressed
							deleteButton.setOnAction(e -> {
								Question question = getTableView().getItems().get(getIndex());
								// remove from ChangeListener
								questions.remove(question);

							});
							setGraphic(deleteButton);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		deleteColumnOOP.setCellFactory(deleteCallBack);
		deleteColumnJAVA.setCellFactory(deleteCallBack);
		deleteColumnSWE.setCellFactory(deleteCallBack);

	}

}
