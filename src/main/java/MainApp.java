package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;

import Classes.Book;
import Classes.Library;
import Classes.javaPreferences;
import Classes.writeToFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static Stage primaryStage;
	public static BorderPane mainLayout;

	public static void main(String[] args) throws IOException {
		launch(args);
	}

	public void closeApp() throws FileNotFoundException {
		System.out.println("Saving libraries...");
		writeToFile.serializeAddress(Library.systemLibrary, new File(javaPreferences.getDestination()));
		System.out.println("File Saved. Closing...");
		primaryStage.close();
	}

	@Override
	public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
		MainApp.primaryStage = primaryStage;
		checkPreferences();

		loadBooks();
		startScreen();
		primaryStage.setOnCloseRequest(e -> {
			try {
				closeApp();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
	}

	private void checkPreferences() {
		if (javaPreferences.getDestination() == null) {

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER", "*.ser");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showSaveDialog(MainApp.primaryStage);
			String destination = file.getAbsolutePath();

			javaPreferences.setDestination(destination);
		}

	}

	private void loadBooks() throws ClassNotFoundException, IOException {

		ObjectInputStream objectinputstream = null;
		System.out.println("Loading books...");
		try {
			FileInputStream streamIn = new FileInputStream(javaPreferences.getDestination());
			objectinputstream = new ObjectInputStream(streamIn);
			@SuppressWarnings("unchecked")
			ArrayList<Book> booksList = (ArrayList<Book>) objectinputstream.readObject();
			for (int i = 0; i < booksList.size(); i++) {

				File file = booksList.get(i).getFile();
				String author = booksList.get(i).getAuthor();
				String ISBN = booksList.get(i).getISBN();
				String title = booksList.get(i).getTitle();
				String age = booksList.get(i).getAge();
				booksList.get(i);
				int numWords = Book.getNumWords(booksList.get(i).getFile());
				Map<String, Integer> uniqueWords = booksList.get(i).getUniqueWords();
				String genre = booksList.get(i).getGenre();
				String complete = booksList.get(i).getComplete();

				Library.systemLibrary
						.add(new Book(file, author, ISBN, title, age, numWords, uniqueWords, genre, complete));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objectinputstream != null) {
				objectinputstream.close();
			}
		}
		System.out.println("Done.");

	}

	private void startScreen() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("controllers/MainView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}