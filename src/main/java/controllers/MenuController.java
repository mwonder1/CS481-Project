package main.java.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Classes.Book;
import Classes.Library;
import Classes.WritetoXML;
import Classes.javaPreferences;
import Classes.writeLibraries;
import Classes.writeSystemLibrary;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.FileChooser;
import main.java.MainApp;

public class MenuController {

	public static void aboutBtn() {
		// TODO Auto-generated method stub

	}

	public static void closeBtn() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Before we close...");
		alert.setHeaderText(null);
		alert.setContentText("Would you like to save your work before exiting?");

		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");

		alert.getButtonTypes().setAll(yes, no);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == yes) {
			saveBtn();
			MainApp.primaryStage.close();
		} else {
			MainApp.primaryStage.close();
		}

	}

	public static void genBtn() {

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

	public static void importBtn() throws IOException {
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
		// BookController.tableView.setItems(BookController.getBooks());
		ViewControllers.showBooks();

	}

	public static void saveBtn() {

		System.out.println("Saving libraries...");
		writeSystemLibrary.serializeAddress(Library.systemLibrary, new File(javaPreferences.getDestination()));

		if (Library.libraries.size() > 0) {
			for (int i = 0; i < Library.libraries.size(); i++) {

				writeLibraries.serializeAddress(Library.libraries.get(i),
						new File(javaPreferences.getDestination().concat(Integer.toString(i))));
				javaPreferences.setLibraryDest(Integer.toString(i + 1),
						javaPreferences.getDestination().concat(Integer.toString(i)));
			}
		}
		javaPreferences.numLibraries = Library.libraries.size();
		javaPreferences.setNumLibraries(Library.libraries.size());
		System.out.println("File Saved. Closing...");
	}

}
