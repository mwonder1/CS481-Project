package main.java.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;

public class DictionaryController {

	@FXML
	private BorderPane dictView;
	@FXML
	private Button homeBtn, booksBtn, librariesBtn, dictBtn, generateDict, settingsBtn;
	@FXML
	private MenuButton actionBtn;

	public void goBooks() throws IOException {
		ViewControllers.showBooks();
	}

	public void goDictionary() throws IOException {
		ViewControllers.showDictionary();
	}

	public void goLibrary() throws IOException {
		ViewControllers.showLibrary();
	}

}
