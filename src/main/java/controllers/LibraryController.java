package main.java.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import Classes.Book;
import Classes.Library;
import Classes.tableLibrary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

public class LibraryController {

	@FXML
	private BorderPane LibraryView;
	@FXML
	private TextField newLibTitle;
	@FXML
	private Button createNewLib;
	@FXML
	private TableView<tableLibrary> tableView;
	@FXML
	private TableColumn<tableLibrary, String> titleCol;
	@FXML
	private TableColumn<tableLibrary, String> uniqueWordsCol;
	@FXML
	private TableColumn<tableLibrary, String> totalWordsCol;
	@FXML
	private TableColumn<tableLibrary, String> numBooks;

	public void changeTitleCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableLibrary librarySelected = tableView.getSelectionModel().getSelectedItem();

		// for (int i = 0; i < Library.libraries.size(); i++) {
		// if (librarySelected.getTitle().equals(Library.libraries.get(i).getTitle())) {
		// Library.libraries.get(i).setTitle(edditedCell.getNewValue().toString());
		// }
		// }
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

		// Set up table columns
		titleCol.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("title"));
		uniqueWordsCol.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("uniqueWords"));
		totalWordsCol.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("totalWords"));
		numBooks.setCellValueFactory(new PropertyValueFactory<tableLibrary, String>("numBooks"));

		// Retrieve Books and allow the titleCol and ageCol to be editable
		tableView.setItems(getLibrary());
		tableView.setEditable(true);
		titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

		// Allow multiple rows to be selected
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public void newLibrary() throws FileNotFoundException {
		Library.createLibrary(newLibTitle.getText());
		System.out.println(newLibTitle.getText() + " library created.");
		System.out.println(Library.libraries.size());
		new Book(null, null, null, null, null, 0, null);
		for (int i = 0; i < Library.systemLibrary.size(); i++) {
			System.out.println(Library.systemLibrary.get(i).getTitle());
			// if (Library.systemLibrary.get(i).getTitle() == "Eagle Song") {
			// book = Library.systemLibrary.get(i);
			// Library.addBooktoLibrary(Library.libraries.get(i), book);
		}
	}
	// Library library = new Library();

	private ObservableList<tableLibrary> getLibrary() {

		ObservableList<tableLibrary> libraries = FXCollections.observableArrayList();

		for (int i = 0; i < Library.libraries.size(); i++) {
			Entry<String, ArrayList<Book>> entry = Library.libraries.get(i).entrySet().iterator().next();

			String title = entry.getKey();
			String numBooks = Integer.toString(entry.getValue().size());
			int uni = 0, tot = 0;

			for (int z = 0; z < Library.libraries.size(); z++) {
				Book book = entry.getValue().get(z);
				uni += book.getUniqueWords().size();
				tot += book.getTotalWords();
			}

			String uniqueWords = Integer.toString(uni);
			String totalWords = Integer.toString(tot);
			libraries.add(new tableLibrary(title, uniqueWords, totalWords, numBooks));
		}

		return libraries;
	}

	// tableView.setItems(getLibrary());
}
