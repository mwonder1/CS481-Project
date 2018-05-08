package main.java.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AboutController {

	@FXML
	private Button homeBtn, librariesBtn, dictBtn, booksBtn, savePathBtn;
	@FXML
	private BorderPane MainView;
	@FXML
	private Pane settingsPane;
	@FXML
	private TextArea text;
	@FXML
	private Text txt;
	@FXML
	private MenuItem saveBtn, importBtn, genBtn, aboutBtn, closeBtn, settingsBtn;

	public void aboutBtn() {

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

	// public void savePathBtn() {
	// Alert alert = new Alert(AlertType.INFORMATION);
	// alert.setTitle("Defaul Save Location");
	// alert.setHeaderText(null);
	// alert.setContentText("Please select a default location and name for your save
	// files.");
	// alert.showAndWait();
	//
	// FileChooser fileChooser = new FileChooser();
	// FileChooser.ExtensionFilter extFilter = new
	// FileChooser.ExtensionFilter("SER", "*.ser");
	// fileChooser.getExtensionFilters().add(extFilter);
	// File file = fileChooser.showSaveDialog(MainApp.primaryStage);
	// javaPreferences.destination = file.getAbsolutePath();
	//
	// javaPreferences.setDestination(javaPreferences.destination);
	// }

	public void settingsBtn() throws IOException {
		ViewControllers.showSettings();

	}

}
