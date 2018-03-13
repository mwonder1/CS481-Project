package main.java;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static Stage primaryStage;
	public static BorderPane mainLayout;

	public static void main(String[] args) throws IOException {
		File newBook = new File("C:\\Users\\mwond\\Desktop/A Fruit is a Suitcase For Seeds.txt");
		File newBook2 = new File("C:\\Users\\mwond\\Desktop/A life like mine.txt");
		File newBook3 = new File("C:\\Users\\mwond\\Desktop/Eagle Song.txt");
		File newBook4 = new File("C:\\Users\\mwond\\Desktop/About Insects.txt");
		File newBook5 = new File("C:\\Users\\mwond\\Desktop/Aesop's Fables.txt");

		Book.addBook(newBook);
		Book.addBook(newBook2);
		Book.addBook(newBook3);
		Book.addBook(newBook4);
		Book.addBook(newBook5);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {

		MainApp.primaryStage = primaryStage;
		startScreen();
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