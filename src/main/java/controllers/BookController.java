
package main.java.controllers;

import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
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
	private Button homeBtn, booksBtn, librariesBtn, dictBtn, newDictBtn, addBook, importBtn, deleteBtn;
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
	@FXML
	private TableColumn<tableBook, String> genreCol;

	public void addBooksBtn() throws IOException {

		List<String> success = new ArrayList<>();
		List<String> fail = new ArrayList<>();

		@SuppressWarnings("resource")
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
						Library.libraries.get(0).addBooktoLibrary(library, Library.systemLibrary.get(i));
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

	public void generateDictBtn() {
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

		Library lib = null;

		for (int i = 0; i < Library.libraries.size(); i++) {
			if (Library.libraries.get(i).getTitle().equals(title)) {
				lib = Library.libraries.get(i);
			}
		}

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(MainApp.primaryStage);

		WritetoXML.writeOutput(lib, file);
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
		genreCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("genre"));
		authorCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("author"));
		isbnCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("ISBN"));

		// Retrieve Books and allow the titleCol and ageCol to be editable
		tableView.setItems(getBooks());
		tableView.setEditable(true);
		titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
		ageCol.setCellFactory(TextFieldTableCell.forTableColumn());
		authorCol.setCellFactory(TextFieldTableCell.forTableColumn());
		isbnCol.setCellFactory(TextFieldTableCell.forTableColumn());
		genreCol.setCellFactory(TextFieldTableCell.forTableColumn());

		// Allow multiple rows to be selected
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	private ObservableList<tableBook> getBooks() {

		ObservableList<tableBook> books = FXCollections.observableArrayList();

		for (int i = 0; i < Library.systemLibrary.size(); i++) {

			String title = Library.systemLibrary.get(i).getTitle();
			String uniqueWords = Integer.toString(Library.systemLibrary.get(i).getUniqueWords().size());
			String totalWords = Integer.toString(Library.systemLibrary.get(i).getTotalWords());
			String age = Library.systemLibrary.get(i).getAge();
			String author = Library.systemLibrary.get(i).getAuthor();
			String ISBN = Library.systemLibrary.get(i).getISBN();
			String genre = Library.systemLibrary.get(i).getGenre();

			books.add(new tableBook(title, uniqueWords, totalWords, age, author, ISBN, genre));
		}
		return books;
	}

}
