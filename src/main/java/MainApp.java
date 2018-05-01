package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import Classes.Book;
import Classes.Library;
import Classes.javaPreferences;
import Classes.writeLibraries;
import Classes.writeSystemLibrary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static Stage primaryStage;
	public static BorderPane mainLayout;

	public static void main(String[] args) throws IOException {
		launch(args);
	}

	public void closeApp() throws FileNotFoundException {

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
		primaryStage.close();
	}

	@Override
	public void start(Stage primaryStage) throws IOException, ClassNotFoundException, BackingStoreException {
		MainApp.primaryStage = primaryStage;
		javaPreferences.checkPreferences();

		loadBooks();
		loadLibraries();
		startScreen();
		primaryStage.setOnCloseRequest(e -> {
			try {
				closeApp();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
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

	private void loadLibraries() throws ClassNotFoundException, IOException, BackingStoreException {

		ObjectInputStream objectinputstream = null;
		System.out.println("Loading libraries...");
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);

		for (int i = 0; i < javaPreferences.getNumLibraries(); i++) {
			try {
				FileInputStream streamIn = new FileInputStream(
						prefs.get(javaPreferences.getDestination().concat(Integer.toString(i)),
								javaPreferences.getDestination().concat(Integer.toString(i))));
				objectinputstream = new ObjectInputStream(streamIn);
				Library library = (Library) objectinputstream.readObject();

				if (library.getTitle() != null) {
					Library.libraries.add(library);
				} else {
					break;
				}
				// for (int i = 0; i < Library.libraries.size(); i++) {
				// Library.libraries.add(library);
				// }

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (objectinputstream != null) {
					objectinputstream.close();
				}
			}
		}
		javaPreferences.numLibraries = Library.libraries.size();
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