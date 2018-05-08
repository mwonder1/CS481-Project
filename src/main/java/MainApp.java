package main.java;

import java.io.IOException;
import java.util.prefs.BackingStoreException;

import Classes.Book;
import Classes.Library;
import Classes.javaPreferences;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.controllers.MenuController;

public class MainApp extends Application {

	public static Stage primaryStage;
	public static BorderPane mainLayout;

	public static void main(String[] args) throws IOException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException, ClassNotFoundException, BackingStoreException {

		MainApp.primaryStage = primaryStage;
		primaryStage.setMaximized(true);
		Book.loadBooks();
		Library.loadLibraries();
		startScreen();
		javaPreferences.checkPreferences();
		primaryStage.setOnCloseRequest(e -> {
			MenuController.closeBtn();
		});
	}

	private void startScreen() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("controllers/BookView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}