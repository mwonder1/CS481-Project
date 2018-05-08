package main.java.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.prefs.BackingStoreException;

import Classes.Book;
import Classes.Library;
import Classes.WritetoXML;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import main.java.MainApp;

public class LibraryController {
	public Library currentLib;
	@FXML
	private TableColumn<tableBook, String> titleCol, uniqueWordsCol, totalWordsCol, ageCol, authorCol, isbnCol,
			genreCol, completeCol;
	@FXML
	private BorderPane LibraryView;
	@FXML
	private Button createNewLib, dictBtn, booksBtn, homeBtn, librariesBtn, LibDeleteBtn, bookDeleteBtn, libDeleteBtn,
			mergeBtn, generateBtn;
	@FXML
	private Button addBooks, openLib;
	@FXML
	private TableView<tableBook> libView;
	@FXML
	private TableView<tableLibrary> tableView;
	@FXML
	private TableColumn<tableLibrary, String> libTitleCol, libUniqueWordsCol, libTotalWordsCol, numBooks;

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
		for (int i = 0; i < Library.libraries.size(); i++) {
			if (librarySelected.getTitle().equals(Library.libraries.get(i).getTitle())) {
				Library.libraries.get(i).setTitle(edditedCell.getNewValue().toString());
			}
		}
		librarySelected.setTitle(edditedCell.getNewValue().toString());
	}

	public void generateBtn() {

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
		completeCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("complete"));

		// Set up library table columns
		libTitleCol.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("title"));
		libUniqueWordsCol.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("uniqueWords"));
		libTotalWordsCol.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("totalWords"));
		numBooks.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("numBooks"));

		// Don't Allow multiple rows to be selected
		libView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		// Allow title col to be editied
		tableView.setEditable(true);
		libTitleCol.setCellFactory(TextFieldTableCell.forTableColumn());

		// Retrieve Books and allow the titleCol and ageCol to be editable
		tableView.setItems(getLibrary());
		tableView.setEditable(true);
		titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	public void libDeleteBtn() {

		tableLibrary selectedRows;
		selectedRows = tableView.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Removal");
		alert.setHeaderText("Removing Library: " + tableView.getSelectionModel().getSelectedItem().getTitle());
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// ... user chose OK

			Library.deleteLibrary(selectedRows);

		} else {
			// ... user chose CANCEL or closed the dialog
		}
		tableView.setItems(getLibrary());

	}

	public void mergeBtn() throws BackingStoreException {

		if (Library.libraries.size() == 0 || Library.libraries.size() == 1) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Unable to Merge");
			alert.setHeaderText(null);
			alert.setContentText("You don't have enough libraries to do this.");
			alert.showAndWait();
			return;
		}

		else {

			Library lib1 = null;
			Library lib2 = null;
			List<String> choices = new ArrayList<>();

			for (int i = 0; i < Library.libraries.size(); i++) {
				choices.add(Library.libraries.get(i).getTitle());
			}

			ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
			dialog.setTitle("Merge Libraries");
			dialog.setHeaderText(null);
			dialog.setContentText("Choose a Library.");

			Optional<String> result = dialog.showAndWait();
			String title = result.get();
			for (int i = 0; i < Library.libraries.size(); i++) {
				if (Library.libraries.get(i).getTitle().equals(title)) {
					lib1 = Library.libraries.get(i);
				}
			}
			if (result.isPresent()) {
				List<String> choices2 = new ArrayList<>();

				for (int i = 0; i < Library.libraries.size(); i++) {
					if (Library.libraries.get(i).getTitle().equals(title)) {

					} else {
						choices2.add(Library.libraries.get(i).getTitle());
					}
				}

				ChoiceDialog<String> dialog2 = new ChoiceDialog<>("", choices2);
				dialog2.setTitle("Merge Libraries");
				dialog2.setHeaderText(null);
				dialog2.setContentText("Now choose a different library to merge with.");

				Optional<String> result1 = dialog2.showAndWait();
				String title1 = result1.get();
				for (int i = 0; i < Library.libraries.size(); i++) {
					if (Library.libraries.get(i).getTitle().equals(title1)) {
						lib2 = Library.libraries.get(i);
					}
				}
				TextInputDialog dialog1 = new TextInputDialog("Choose a name...");
				dialog1.setTitle("New Library");
				dialog1.setHeaderText(null);
				dialog1.setContentText("Please name your newly merged library.");

				Optional<String> result11 = dialog1.showAndWait();
				if (result11.isPresent()) {

					Library.createLibrary(result11.get(), Library.mergeLibraries(lib1, lib2));
					// Library.deleteLibrary(lib1);
					// Library.deleteLibrary(lib2);
					tableView.setItems(getLibrary());
				}
			}
		}
	}

	public void newLibrary() throws FileNotFoundException {

		String name = null;
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("New Library");
		dialog.setHeaderText(null);
		dialog.setContentText("Enter a name for your library.");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			name = result.get();
		}

		if (name.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please enter a name.");
			alert.showAndWait();
			return;
		}
		if (Library.libraries.size() == 0) {
			Library.createLibrary(name);
			tableView.setItems(getLibrary());
			return;
		}

		for (int i = 0; i < Library.libraries.size(); i++) {

			if (Library.libraries.get(i).getTitle().equals(name)) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText(null);
				alert.setContentText("A Library with the same name already exists! Please try a different name.");
				alert.showAndWait();
				return;

			}

		}
		Library.createLibrary(name);
		tableView.setItems(getLibrary());
		return;
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
			String complete = lib.getBooksList().get(i).getComplete();

			books.add(new tableBook(title, uniqueWords, totalWords, age, author, ISBN, genre, complete));
		}
		return books;
	}

	private ObservableList<tableLibrary> getLibrary() {

		ObservableList<tableLibrary> libraries = FXCollections.observableArrayList();

		for (int i = 0; i < Library.libraries.size(); i++) {

			String title = Library.libraries.get(i).getTitle();
			String numBooks = Integer.toString(Library.libraries.get(i).getBooksList().size());
			int uni = 0, tot = 0;

			for (int z = 0; z < Library.libraries.get(i).getBooksList().size(); z++) {
				Book book = Library.libraries.get(i).getBooksList().get(z);
				uni += book.getUniqueWords().size();
				tot += book.getTotalWords();
			}
			String uniqueWords = Integer.toString(uni);
			String totalWords = Integer.toString(tot);
			libraries.add(new tableLibrary(title, uniqueWords, totalWords, numBooks));
		}
		return libraries;
	}
}
