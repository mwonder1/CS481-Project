package main.java.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import main.java.Book;
import main.java.MainApp;
import main.java.WritetoXML;
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

	public void changeTitleCellEvent(CellEditEvent<?, ?> edditedCell) {

		tableBook bookSelected = tableView.getSelectionModel().getSelectedItem();
		bookSelected.setTitle(edditedCell.getNewValue().toString());
		Book.booksList.get(edditedCell.getTablePosition().getColumn()).setTitle(edditedCell.getNewValue().toString());

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

		titleCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("title"));
		uniqueWordsCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("uniqueWords"));
		totalWordsCol.setCellValueFactory(new PropertyValueFactory<tableBook, String>("totalWords"));

		tableView.setItems(getBooks());
		tableView.setEditable(true);
		titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
	}

}
