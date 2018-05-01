package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.prefs.Preferences;

import Classes.Book;
import Classes.Library;
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

	public String getDestination() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("destination", null);
		if (filePath != null)
			return filePath;
		else
			return null;
	}

	public void setDestination(String destination) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (destination != null) {
			prefs.put("destination", destination);

		} else {
			prefs.remove("destination");

		}
	}

	@Override
	public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
		MainApp.primaryStage = primaryStage;
		if (getDestination() == null) {

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER", "*.ser");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showSaveDialog(MainApp.primaryStage);
			String destination = file.getAbsolutePath();

			setDestination(destination);
		}
		loadBooks();
		startScreen();
		System.out.println(getDestination());
	}

	private void loadBooks() throws ClassNotFoundException, IOException {

		ObjectInputStream objectinputstream = null;
		try {
			FileInputStream streamIn = new FileInputStream(getDestination());
			objectinputstream = new ObjectInputStream(streamIn);
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