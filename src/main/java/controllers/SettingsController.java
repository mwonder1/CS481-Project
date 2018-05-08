package main.java.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import Classes.javaPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import main.java.MainApp;

public class SettingsController {

	@FXML
	private BorderPane MainView;

	@FXML
	private Button dictBtn;

	@FXML
	private Button librariesBtn;

	@FXML
	private Button booksBtn;

	@FXML
	private Button homeBtn;

	@FXML
	private Pane settingsPane;

	@FXML
	private Button savePathBtn;

	@FXML
	private MenuItem saveBtn;

	@FXML
	private MenuItem closeBtn;

	@FXML
	private MenuItem importBtn;

	@FXML
	private MenuItem genBtn;

	@FXML
	private MenuItem aboutBtn;

	@FXML
	private MenuItem settingsBtn;

	public void aboutBtn() throws IOException {
		ViewControllers.showAbout();
	}

	public void closeBtn() throws FileNotFoundException {
		MenuController.closeBtn();
	}

	public void genBtn() {
		MenuController.genBtn();
	}

	public void goBooks() throws IOException {
		ViewControllers.showBooks();
	}

	public void goDictionary() throws IOException {
		ViewControllers.showDictionary();
	}

	public void goLibrary() throws IOException {
		ViewControllers.showLibrary();
	}

	public void importBtn() throws IOException {
		MenuController.importBtn();
	}

	public void saveBtn() {
		MenuController.saveBtn();
	}

	public void savePathBtn() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Defaul Save Location");
		alert.setHeaderText(null);
		alert.setContentText("Please select a default location and name for your save files.");
		alert.showAndWait();

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER", "*.ser");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(MainApp.primaryStage);
		javaPreferences.destination = file.getAbsolutePath();

		javaPreferences.setDestination(javaPreferences.destination);
	}

}
