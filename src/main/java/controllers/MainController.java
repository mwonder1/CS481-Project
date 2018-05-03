package main.java.controllers;

import java.io.File;
import java.io.IOException;

import Classes.javaPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import main.java.MainApp;
import javafx.scene.text.Text;

public class MainController {

	@FXML
	private Button homeBtn, librariesBtn, dictBtn, booksBtn, settingBtn, savePathBtn;
	@FXML
	private BorderPane MainView;
	@FXML
	private Pane settingsPane;
	@FXML
	private TextArea text;
	@FXML
	private Text txt;

	public void goBooks() throws IOException {
		ViewControllers.showBooks();
	}

	public void goDictionary() throws IOException {
		ViewControllers.showDictionary();
	}

	public void goHome() throws IOException {
		ViewControllers.showHome();
		settingsPane.setVisible(false);
		
	}

	public void goLibrary() throws IOException {
		ViewControllers.showLibrary();
	}
	
	public void settingsBtn() {
		
		settingsPane.setVisible(true);
		text.setVisible(false);
		txt.setVisible(false);
		
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
