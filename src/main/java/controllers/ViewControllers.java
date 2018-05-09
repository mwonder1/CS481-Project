package main.java.controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import main.java.MainApp;

public class ViewControllers {

	static void showAbout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("controllers/AboutView.fxml"));
		MainApp.mainLayout = loader.load();
		Parent newContent = MainApp.mainLayout;
		MainApp.primaryStage.getScene().setRoot(newContent);
		MainApp.primaryStage.show();
	}

	static void showBooks() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("controllers/BookView.fxml"));
		MainApp.mainLayout = loader.load();
		Parent newContent = MainApp.mainLayout;
		MainApp.primaryStage.getScene().setRoot(newContent);
		MainApp.primaryStage.show();
	}

	static void showDictionary() throws IOException {
		// FXMLLoader loader = new FXMLLoader();
		// loader.setLocation(MainApp.class.getResource("controllers/DictionaryView.fxml"));
		// MainApp.mainLayout = loader.load();
		// Parent newContent = MainApp.mainLayout;
		// MainApp.primaryStage.getScene().setRoot(newContent);
		// MainApp.primaryStage.show();
	}

	static void showLibrary() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("controllers/LibraryView.fxml"));
		MainApp.mainLayout = loader.load();
		Parent newContent = MainApp.mainLayout;
		MainApp.primaryStage.getScene().setRoot(newContent);
		MainApp.primaryStage.show();
	}

	static void showSettings() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("controllers/SettingsView.fxml"));
		MainApp.mainLayout = loader.load();
		Parent newContent = MainApp.mainLayout;
		MainApp.primaryStage.getScene().setRoot(newContent);
		MainApp.primaryStage.show();
	}

}
