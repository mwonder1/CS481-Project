package main.java;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

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
	public void start(Stage primaryStage) throws IOException {
		MainApp.primaryStage = primaryStage;
		if (getDestination() == null) {

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER", "*.ser");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showSaveDialog(MainApp.primaryStage);
			String destination = file.getAbsolutePath();

			setDestination(destination);
		}
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