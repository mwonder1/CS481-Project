package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
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
		startScreen();
		Book.loadBooks();
		Library.loadLibraries();
		javaPreferences.checkPreferences();
		primaryStage.setOnCloseRequest(e -> {
			try {
				closeApp();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
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