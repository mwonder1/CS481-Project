package main.java.controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import main.java.MainApp;

public class ViewControllers {

	static void showBooks() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("controllers/BookView.fxml"));
		MainApp.mainLayout = loader.load();
		Scene scene = new Scene(MainApp.mainLayout);
		MainApp.primaryStage.setScene(scene);
		MainApp.primaryStage.show();
	}

	static void showDictionary() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("controllers/DictionaryView.fxml"));
		MainApp.mainLayout = loader.load();
		Scene scene = new Scene(MainApp.mainLayout);
		MainApp.primaryStage.setScene(scene);
		MainApp.primaryStage.show();
	}

	static void showHome() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("controllers/MainView.fxml"));
		MainApp.mainLayout = loader.load();
		Scene scene = new Scene(MainApp.mainLayout);
		MainApp.primaryStage.setScene(scene);
		MainApp.primaryStage.show();
	}

	static void showLibrary() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("controllers/LibraryView.fxml"));
		MainApp.mainLayout = loader.load();
		Scene scene = new Scene(MainApp.mainLayout);
		MainApp.primaryStage.setScene(scene);
		MainApp.primaryStage.show();
	}

}
