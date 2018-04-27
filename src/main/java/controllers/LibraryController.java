package main.java.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Classes.Book;
import Classes.Library;
import Classes.tableBook;
import Classes.tableLibrary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

public class LibraryController {
	public Library currentLib;
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
	@FXML
	private TableColumn<tableBook, String> genreCol;
	@FXML
	private BorderPane LibraryView;
	@FXML
	private TextField newLibTitle;
	@FXML
	private Button createNewLib, dictBtn, booksBtn, homeBtn, librariesBtn, LibDeleteBtn, bookDeleteBtn, libDeleteBtn;
	@FXML
	private Button addBooks, openLib;
	@FXML
	private TableView<tableBook> libView;
	@FXML
	private TableView<tableLibrary> tableView;
	@FXML
	private TableColumn<tableLibrary, String> libTitleCol;
	@FXML
	private TableColumn<tableLibrary, String> libUniqueWordsCol;
	@FXML
	private TableColumn<tableLibrary, String> libTotalWordsCol;
	@FXML
	private TableColumn<tableLibrary, String> numBooks;

	public void bookDeleteBtn() {

		ObservableList<tableBook> selectedRows, allBooks;
		allBooks = libView.getItems();
		selectedRows = libView.getSelectionModel().getSelectedItems();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Removal");
		alert.setHeaderText("Removing book: " + libView.getSelectionModel().getSelectedItem().getTitle());
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// ... user chose OK
			for (tableBook book : selectedRows) {
				for (int i = 0; i < currentLib.getBooksList().size(); i++) {
					if (currentLib.getBooksList().get(i).getTitle().equals(book.getTitle())) {
						currentLib.getBooksList().remove(i);
						allBooks.remove(book);
					}
				}
			}
		} else {
			// ... user chose CANCEL or closed the dialog
		}

	}

	public void changeTitleCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableLibrary librarySelected = tableView.getSelectionModel().getSelectedItem();
		librarySelected.setTitle(edditedCell.getNewValue().toString());
	}

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

	public void initialize() {

		// Set up book table columns
		titleCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("title"));
		uniqueWordsCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("uniqueWords"));
		totalWordsCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("totalWords"));
		ageCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("age"));
		genreCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("genre"));
		authorCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("author"));
		isbnCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("ISBN"));

		// Set up library table columns
		libTitleCol.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("title"));
		libUniqueWordsCol.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("uniqueWords"));
		libTotalWordsCol.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("totalWords"));
		numBooks.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("numBooks"));

		// Retrieve Books and allow the titleCol and ageCol to be editable

		tableView.setItems(getLibrary());
		tableView.setEditable(true);
		titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

		// Allow multiple rows to be selected
		// tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public void libDeleteBtn() {

		ObservableList<tableLibrary> selectedRows;
		selectedRows = tableView.getSelectionModel().getSelectedItems();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Removal");
		alert.setHeaderText("Removing Library: " + tableView.getSelectionModel().getSelectedItem().getTitle());
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// ... user chose OK
			for (tableLibrary lib : selectedRows) {
				Library.deleteLibrary(lib);
			}
		} else {
			// ... user chose CANCEL or closed the dialog
		}
		tableView.setItems(getLibrary());

	}

	public void newLibrary() throws FileNotFoundException {

		if (Library.libraries.size() == 0) {
			Library.createLibrary(newLibTitle.getText());
			tableView.setItems(getLibrary());
		}

		else {
			for (int i = 0; i < Library.libraries.size(); i++) {
				if (newLibTitle.getText().equals("")) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Please enter a name.");
					alert.showAndWait();
				}

				else if (Library.libraries.get(i).getTitle().equals(newLibTitle.getText())) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText(null);
					alert.setContentText("A Library with the same name already exists! Please try a different name.");
					alert.showAndWait();
				} else {
					Library.createLibrary(newLibTitle.getText());
				}
				tableView.setItems(getLibrary());
				System.out.println(newLibTitle.getText() + " library created.");
				System.out.println(Library.libraries.size());
			}
		}
	}

	public void openLibBtn() {

		if (Library.libraries.size() == 0) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(null);
			alert.setContentText("There are no libraries to open. Please create one before trying again.");
			alert.showAndWait();

		} else {
			List<String> choices = new ArrayList<>();
			for (int i = 0; i < Library.libraries.size(); i++) {
				choices.add(Library.libraries.get(i).getTitle());
			}

			ChoiceDialog<String> dialog = new ChoiceDialog<>("Libraries...", choices);
			dialog.setTitle("Which library do you want to view?");
			dialog.setHeaderText("Please choose which library you would like view the contents of.");
			dialog.setContentText("Select a library:");

			Optional<String> result = dialog.showAndWait();

			String title = result.get();

			for (int i = 0; i < Library.libraries.size(); i++) {
				if (Library.libraries.get(i).getTitle().equals(title)) {
					currentLib = Library.libraries.get(i);
				}
			}

			libDeleteBtn.setVisible(false);
			bookDeleteBtn.setVisible(true);
			libView.setItems(getBooks(currentLib));
			libView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			libView.setVisible(true);
			tableView.setVisible(false);
			tableView.setEditable(false);

		}
	}

	private ObservableList<tableBook> getBooks(Library lib) {

		ObservableList<tableBook> books = FXCollections.observableArrayList();

		for (int i = 0; i < lib.getBooksList().size(); i++) {

			String title = lib.getBooksList().get(i).getTitle();
			String uniqueWords = Integer.toString(lib.getBooksList().get(i).getUniqueWords().size());
			String totalWords = Integer.toString(lib.getBooksList().get(i).getTotalWords());
			String age = lib.getBooksList().get(i).getAge();
			String author = lib.getBooksList().get(i).getAuthor();
			String ISBN = lib.getBooksList().get(i).getISBN();
			String genre = lib.getBooksList().get(i).getGenre();

			books.add(new tableBook(title, uniqueWords, totalWords, age, author, ISBN, genre));
		}
		return books;
	}

	// Library library = new Library();

	private ObservableList<tableLibrary> getLibrary() {

		ObservableList<tableLibrary> libraries = FXCollections.observableArrayList();

		for (int i = 0; i < Library.libraries.size(); i++) {
			// Entry<String, ArrayList<Book>> entry =
			// Library.libraries.get(i).entrySet().iterator().next();
			// for (Map.Entry<String, ArrayList<Book>> entry :
			// Library.libraries.get(i).entrySet()) {
			// for (int i1 = 0; i1 < Library.libraries.get(i1).size(); i1++) {
			// String title = entry.getKey();
			String title = Library.libraries.get(i).getTitle();
			// String numBooks = Integer.toString(Library.libraries.get(i1).values().size();
			String numBooks = Integer.toString(Library.libraries.get(i).getBooksList().size());
			int uni = 0, tot = 0;

			for (int z = 0; z < Library.libraries.get(i).getBooksList().size(); z++) {
				Book book = Library.libraries.get(i).getBooksList().get(z);
				// ArrayList<Book> temp = new ArrayList<>();
				uni += book.getUniqueWords().size();
				tot += book.getTotalWords();
			}

			String uniqueWords = Integer.toString(uni);
			// Integer.toString(uni);
			String totalWords = Integer.toString(tot);
			// Integer.toString(tot);
			libraries.add(new tableLibrary(title, uniqueWords, totalWords, numBooks));
		}

		return libraries;
	}

	// tableView.setItems(getLibrary());
}
