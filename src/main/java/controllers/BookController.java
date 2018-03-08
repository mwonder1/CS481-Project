package main.java.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import main.java.Book;

public class BookController {

	@FXML
	private BorderPane BookView;
	@FXML
	private Button homeBtn, booksBtn, librariesBtn, dictBtn, newDictBtn, settingsBtn, importBtn, searchBtn;
	@FXML
	private MenuButton actionBtn;
	@FXML
	private TableView<?> bookTable;
	@FXML
	private TableColumn<?, ?> titleCol;
	@FXML
	private TableColumn<?, ?> AuthorCol;
	@FXML
	private TableColumn<?, ?> ISBNCol;

	public void goDictionary() throws IOException {
		ViewControllers.showDictionary();
	}

	public void goHome() throws IOException {
		ViewControllers.showHome();
	}

	public void goLibrary() throws IOException {
		ViewControllers.showLibrary();
	}

	public void initialize() {
		titleCol.setCellValueFactory(new PropertyValueFactory("Title"));
		AuthorCol.setCellValueFactory(new PropertyValueFactory("Author"));
		ISBNCol.setCellValueFactory(new PropertyValueFactory("ISBN"));
	}

	public void setTableContent(ArrayList<Book> booksList) {
		ObservableList<Book> data = FXCollections.<Book>observableArrayList(booksList);

		data.addAll(booksList);

		// bookTable.setItem(data);
		// bookTable.getItems().setAll(booksList);

	}

}
