
package main.java.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Classes.Book;
import Classes.Library;
import Classes.WritetoXML;
import Classes.tableBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import main.java.MainApp;

public class BookController {

	@FXML
	TableView<tableBook> tableView;
	@FXML
	private BorderPane BookView;
	@FXML
	private Button homeBtn, booksBtn, librariesBtn, dictBtn, newDictBtn, addBook, importBtn1, deleteBtn, searchBtn;
	@FXML
	private MenuItem importBtn;
	@FXML
	private TableColumn<tableBook, String> titleCol, uniqueWordsCol, totalWordsCol, ageCol, authorCol, isbnCol,
			genreCol, completeCol;

	public void aboutBtn() throws IOException {
		ViewControllers.showAbout();
	}

	public void addBooksBtn() throws IOException {

		if (Library.systemLibrary.size() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No books found.");
			alert.setContentText("Please upload some books first.");

			alert.showAndWait();
			return;
		}

		List<String> success = new ArrayList<>();
		List<String> fail = new ArrayList<>();

		List<String> choices = new ArrayList<>();
		for (int i = 0; i < Library.libraries.size(); i++) {
			choices.add(Library.libraries.get(i).getTitle());
		}

		ChoiceDialog<String> dialog = new ChoiceDialog<>("Libraries...", choices);
		dialog.setTitle("Which library do you want to add to?");
		dialog.setHeaderText("Please choose which library you would like to add the selected books too.");
		dialog.setContentText("Select a library:");

		Optional<String> result = dialog.showAndWait();

		String title = result.get();

		Library library = null;

		for (int i = 0; i < Library.libraries.size(); i++) {
			if (Library.libraries.get(i).getTitle().equals(title)) {
				library = Library.libraries.get(i);
			}
		}

		ObservableList<tableBook> selectedRows;
		selectedRows = tableView.getSelectionModel().getSelectedItems();

		for (tableBook book : selectedRows) {
			for (int i = 0; i < Library.systemLibrary.size(); i++) {
				if (book.getTitle().equals(Library.systemLibrary.get(i).getTitle())) {
					if (library.getBooksList().contains(Library.systemLibrary.get(i))) {
						fail.add(Library.systemLibrary.get(i).getTitle());
					} else {
						Library.addBooktoLibrary(library, Library.systemLibrary.get(i));
						success.add(Library.systemLibrary.get(i).getTitle());
					}
				}
			}
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Successfully added Books");
		alert.setHeaderText(null);
		alert.setContentText("Books successfully added: " + success + "\n\nBooks not added: " + fail);
		alert.showAndWait();
	}

	public void changeAgeCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableBook bookSelected = tableView.getSelectionModel().getSelectedItem();

		for (int i = 0; i < Library.systemLibrary.size(); i++) {
			if (bookSelected.getTitle().equals(Library.systemLibrary.get(i).getTitle())) {
				Library.systemLibrary.get(i).setAge(edditedCell.getNewValue().toString());
			}
		}
		bookSelected.setAge(edditedCell.getNewValue().toString());
	}

	public void changeAuthorCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableBook bookSelected = tableView.getSelectionModel().getSelectedItem();
		for (int i = 0; i < Library.systemLibrary.size(); i++) {
			if (bookSelected.getTitle().equals(Library.systemLibrary.get(i).getTitle())) {
				Library.systemLibrary.get(i).setAuthor(edditedCell.getNewValue().toString());
			}
		}
		bookSelected.setAuthor(edditedCell.getNewValue().toString());
	}

	public void changeCompleteCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableBook bookSelected = tableView.getSelectionModel().getSelectedItem();

		for (int i = 0; i < Library.systemLibrary.size(); i++) {
			if (bookSelected.getTitle().equals(Library.systemLibrary.get(i).getTitle())) {
				Library.systemLibrary.get(i).setComplete(edditedCell.getNewValue().toString());
			}
		}
		bookSelected.setComplete(edditedCell.getNewValue().toString());
	}

	public void changeGenreCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableBook bookSelected = tableView.getSelectionModel().getSelectedItem();

		for (int i = 0; i < Library.systemLibrary.size(); i++) {
			if (bookSelected.getTitle().equals(Library.systemLibrary.get(i).getTitle())) {
				Library.systemLibrary.get(i).setGenre(edditedCell.getNewValue().toString());
			}
		}
		bookSelected.setGenre(edditedCell.getNewValue().toString());
	}

	public void changeISBNCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableBook bookSelected = tableView.getSelectionModel().getSelectedItem();

		for (int i = 0; i < Library.systemLibrary.size(); i++) {
			if (bookSelected.getTitle().equals(Library.systemLibrary.get(i).getTitle())) {
				Library.systemLibrary.get(i).setISBN(edditedCell.getNewValue().toString());
			}
		}
		bookSelected.setISBN(edditedCell.getNewValue().toString());
	}

	public void changeTitleCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableBook bookSelected = tableView.getSelectionModel().getSelectedItem();

		for (int i = 0; i < Library.systemLibrary.size(); i++) {
			if (bookSelected.getTitle().equals(Library.systemLibrary.get(i).getTitle())) {
				Library.systemLibrary.get(i).setTitle(edditedCell.getNewValue().toString());
			}
		}
		bookSelected.setTitle(edditedCell.getNewValue().toString());
	}

	public void closeBtn() throws FileNotFoundException {
		MenuController.closeBtn();
	}

	public void deleteBtn() {

		ObservableList<tableBook> selectedRows, allBooks;
		allBooks = tableView.getItems();
		selectedRows = tableView.getSelectionModel().getSelectedItems();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Removal");
		alert.setHeaderText("Removing book: " + tableView.getSelectionModel().getSelectedItem().getTitle());
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// ... user chose OK
			for (tableBook book : selectedRows) {
				Library.deleteFromSystem(book);
				allBooks.remove(book);
			}
		} else {
			// ... user chose CANCEL or closed the dialog
		}

	}

	public void genBtn() {
		MenuController.genBtn();
	}

	public void generateDictBtn() {

		if (Library.systemLibrary.size() == 0) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Unable to Generate");
			alert.setHeaderText(null);
			alert.setContentText("You have no books uploaded to do this.");
			alert.showAndWait();
			return;
		}

		else {

			List<String> choices = new ArrayList<>();

			for (int i = 0; i < Library.libraries.size(); i++) {
				choices.add(Library.libraries.get(i).getTitle());
			}
			choices.add("System Library");

			ChoiceDialog<String> dialog = new ChoiceDialog<>("Libraries...", choices);
			dialog.setTitle("Which library do you want to view?");
			dialog.setHeaderText("Please choose which library you would like view the contents of.");
			dialog.setContentText("Select a library:");

			Optional<String> result = dialog.showAndWait();
			String title = result.get();
			ArrayList<Book> lib = null;

			if (title.equals("System Library")) {
				lib = Library.systemLibrary;
			}

			for (int i = 0; i < Library.libraries.size(); i++) {
				if (Library.libraries.get(i).getTitle().equals(title)) {
					lib = Library.libraries.get(i).getBooksList();
				}
			}

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML", "*.xml");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showSaveDialog(MainApp.primaryStage);

			WritetoXML.writeOutput(lib, file);
		}
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

	public void importBooksBtn() throws IOException {

		Book.success.clear();
		Book.fail.clear();

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(0, new FileChooser.ExtensionFilter("txt", "*.txt"));
		List<File> list = fileChooser.showOpenMultipleDialog(MainApp.primaryStage);
		for (File file : list) {
			Book.addBook(file);
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Books Added to System Library");
		alert.setHeaderText(null);
		alert.setContentText(
				"Books successfully added: " + Book.success + "\n\nDuplicate books not added: " + Book.fail);

		alert.showAndWait();
		tableView.setItems(getBooks());
	}

	public void importBtn() throws IOException {
		MenuController.importBtn();
	}

	public void initialize() {

		// Set up table columns
		titleCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("title"));
		uniqueWordsCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("uniqueWords"));
		totalWordsCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("totalWords"));
		ageCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("age"));
		genreCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("genre"));
		authorCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("author"));
		isbnCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("ISBN"));
		completeCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("complete"));

		// Retrieve Books and allow the titleCol and ageCol to be editable
		tableView.setItems(getBooks());
		tableView.setEditable(true);
		titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
		ageCol.setCellFactory(TextFieldTableCell.forTableColumn());
		authorCol.setCellFactory(TextFieldTableCell.forTableColumn());
		isbnCol.setCellFactory(TextFieldTableCell.forTableColumn());
		genreCol.setCellFactory(TextFieldTableCell.forTableColumn());
		completeCol.setCellFactory(TextFieldTableCell.forTableColumn());

		// Allow multiple rows to be selected
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public void saveBtn() {
		MenuController.saveBtn();
	}

	public void searchBtn() {

		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Search");
		dialog.setHeaderText("Enter any filters:");

		ButtonType searchBtn = new ButtonType("Search", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(searchBtn, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField author = new TextField();
		author.setPromptText("Title");

		grid.add(new Label("Title:"), 0, 0);
		grid.add(author, 1, 0);

		dialog.getDialogPane().setContent(grid);

		Optional<String> result = dialog.showAndWait();
		String author1 = result.get();
		// searchBooks(author1);
		tableView.setItems(searchBooks(author1));

	}

	public void settingsBtn() throws IOException {
		ViewControllers.showSettings();
	}

	private ObservableList<tableBook> searchBooks(String name) {

		ObservableList<tableBook> books = FXCollections.observableArrayList();

		for (int i = 0; i < Library.systemLibrary.size(); i++) {
			if (name.equals(Library.systemLibrary.get(i).getTitle())) {

				String title = Library.systemLibrary.get(i).getTitle();
				String uniqueWords = Integer.toString(Library.systemLibrary.get(i).getUniqueWords().size());
				String totalWords = Integer.toString(Library.systemLibrary.get(i).getTotalWords());
				String age = Library.systemLibrary.get(i).getAge();
				String author = Library.systemLibrary.get(i).getAuthor();
				String ISBN = Library.systemLibrary.get(i).getISBN();
				String genre = Library.systemLibrary.get(i).getGenre();
				String complete = Library.systemLibrary.get(i).getComplete();

				books.add(new tableBook(title, uniqueWords, totalWords, age, author, ISBN, genre, complete));
			} else {
				break;
			}
		}
		return books;
	}

	ObservableList<tableBook> getBooks() {

		ObservableList<tableBook> books = FXCollections.observableArrayList();

		for (int i = 0; i < Library.systemLibrary.size(); i++) {

			String title = Library.systemLibrary.get(i).getTitle();
			String uniqueWords = Integer.toString(Library.systemLibrary.get(i).getUniqueWords().size());
			String totalWords = Integer.toString(Library.systemLibrary.get(i).getTotalWords());
			String age = Library.systemLibrary.get(i).getAge();
			String author = Library.systemLibrary.get(i).getAuthor();
			String ISBN = Library.systemLibrary.get(i).getISBN();
			String genre = Library.systemLibrary.get(i).getGenre();
			String complete = Library.systemLibrary.get(i).getComplete();

			books.add(new tableBook(title, uniqueWords, totalWords, age, author, ISBN, genre, complete));
		}
		return books;
	}

}
