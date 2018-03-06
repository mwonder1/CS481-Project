package main.java.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class LibraryController {

	@FXML
	private BorderPane LibraryView;

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
