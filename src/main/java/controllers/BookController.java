package main.java.controllers;

import java.io.IOException;

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
import main.java.tableBook;

public class BookController {

	@FXML
	private BorderPane BookView;
	@FXML
	private Button homeBtn, booksBtn, librariesBtn, dictBtn, newDictBtn, settingsBtn, importBtn, searchBtn;
	@FXML
	private MenuButton actionBtn;
	@FXML
	private TableView<tableBook> tableView;
	@FXML
	private TableColumn<tableBook, String> titleCol;
	@FXML
	private TableColumn<tableBook, String> uniqueWordsCol;
	@FXML
	private TableColumn<tableBook, String> totalWordsCol;

	private ObservableList<tableBook> getBooks() {

		ObservableList<tableBook> books = FXCollections.observableArrayList();

		for (int i = 0; i < Book.booksList.size(); i++) {

			String title = Book.booksList.get(i).getTitle();
			String uniqueWords = Integer.toString(Book.booksList.get(i).getUniqueWords().size());
			String totalWords = Integer.toString(Book.booksList.get(i).getTotalWords());

			books.add(new tableBook(title, uniqueWords, totalWords));
		}
		return books;
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

	public void initialize() {

		titleCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("title"));
		uniqueWordsCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("uniqueWords"));
		totalWordsCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("totalWords"));

		tableView.setItems(getBooks());
	}

}
