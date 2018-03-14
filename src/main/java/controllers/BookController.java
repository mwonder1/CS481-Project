package main.java.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import Classes.Book;
import Classes.WritetoXML;
import Classes.tableBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import main.java.MainApp;

public class BookController {

	@FXML
	private BorderPane BookView;
	@FXML
	private Button homeBtn, booksBtn, librariesBtn, dictBtn, newDictBtn, settingsBtn, importBtn, deleteBtn;
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
	@FXML
	private TableColumn<tableBook, String> ageCol;
	@FXML
	private TableColumn<tableBook, String> authorCol;
	@FXML
	private TableColumn<tableBook, String> isbnCol;

	public void changeAgeCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableBook bookSelected = tableView.getSelectionModel().getSelectedItem();
		bookSelected.setAge(edditedCell.getNewValue().toString());
		for (int i = 0; i < Book.booksList.size(); i++) {
			if (bookSelected.getTitle().equals(Book.booksList.get(i).getTitle())) {
				Book.booksList.get(i).setAge(edditedCell.getNewValue().toString());
			}
		}

	}

	public void changeAuthorCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableBook bookSelected = tableView.getSelectionModel().getSelectedItem();
		bookSelected.setAuthor(edditedCell.getNewValue().toString());
		for (int i = 0; i < Book.booksList.size(); i++) {
			if (bookSelected.getTitle().equals(Book.booksList.get(i).getTitle())) {
				Book.booksList.get(i).setAuthor(edditedCell.getNewValue().toString());
			}
		}

	}

	public void changeISBNCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableBook bookSelected = tableView.getSelectionModel().getSelectedItem();
		bookSelected.setISBN(edditedCell.getNewValue().toString());
		for (int i = 0; i < Book.booksList.size(); i++) {
			if (bookSelected.getTitle().equals(Book.booksList.get(i).getTitle())) {
				Book.booksList.get(i).setISBN(edditedCell.getNewValue().toString());
			}
		}

	}

	public void changeTitleCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableBook bookSelected = tableView.getSelectionModel().getSelectedItem();

		for (int i = 0; i < Book.booksList.size(); i++) {
			if (bookSelected.getTitle().equals(Book.booksList.get(i).getTitle())) {
				Book.booksList.get(i).setTitle(edditedCell.getNewValue().toString());
			}
		}
		bookSelected.setTitle(edditedCell.getNewValue().toString());
	}

	public void deleteBtn() {

		ObservableList<tableBook> selectedRows, allBooks;
		allBooks = tableView.getItems();
		selectedRows = tableView.getSelectionModel().getSelectedItems();

		for (tableBook book : selectedRows) {
			Book.deleteFromSystem(book);
			allBooks.remove(book);
		}

	}

	public void generateDictBtn() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(MainApp.primaryStage);

		WritetoXML.writeOutput(file);
	}

	private ObservableList<tableBook> getBooks() {

		ObservableList<tableBook> books = FXCollections.observableArrayList();

		for (int i = 0; i < Book.booksList.size(); i++) {

			String title = Book.booksList.get(i).getTitle();
			String uniqueWords = Integer.toString(Book.booksList.get(i).getUniqueWords().size());
			String totalWords = Integer.toString(Book.booksList.get(i).getTotalWords());
			String age = Book.booksList.get(i).getAge();
			String author = Book.booksList.get(i).getAuthor();
			String ISBN = Book.booksList.get(i).getISBN();

			books.add(new tableBook(title, uniqueWords, totalWords, age, author, ISBN));
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

	public void importBooksBtn() throws IOException {

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(0, new FileChooser.ExtensionFilter("txt", "*.txt"));
		List<File> list = fileChooser.showOpenMultipleDialog(MainApp.primaryStage);
		for (File file : list) {
			Book.addBook(file);
		}
		tableView.setItems(getBooks());
	}

	public void initialize() {

		// Set up table columns
		titleCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("title"));
		uniqueWordsCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("uniqueWords"));
		totalWordsCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("totalWords"));
		ageCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("age"));
		authorCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("author"));
		isbnCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("ISBN"));

		// Retrieve Books and allow the titleCol and ageCol to be editable
		tableView.setItems(getBooks());
		tableView.setEditable(true);
		titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
		ageCol.setCellFactory(TextFieldTableCell.forTableColumn());
		authorCol.setCellFactory(TextFieldTableCell.forTableColumn());
		isbnCol.setCellFactory(TextFieldTableCell.forTableColumn());

		// Allow multiple rows to be selected
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

}
