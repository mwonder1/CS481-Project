package main.java.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MainController {

	@FXML
	private Button homeBtn, booksBtn, librariesBtn, dictBtn;
	@FXML
	private BorderPane MainView;

	public void goBooks() throws IOException {
		ViewControllers.showBooks();
	}

	public void goDictionary() throws IOException {
		ViewControllers.showDictionary();
	}

	public void goHome() throws IOException {
		ViewControllers.showHome();
	}

	public void goLibrary() throws IOException {
		ViewControllers.showLibrary();
	}

}
