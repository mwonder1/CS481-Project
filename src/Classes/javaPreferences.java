package Classes;

import java.io.File;
import java.util.prefs.Preferences;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import main.java.MainApp;

public class javaPreferences {

	public static int numLibraries;
	public static String destination;

	public static void checkPreferences() {
		if (javaPreferences.getDestination() == null) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Defaul Save Location");
			alert.setHeaderText("Save location has not been set!");
			alert.setContentText("Please select a default location and name for your save files.");

			alert.showAndWait();

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER", "*.ser");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showSaveDialog(MainApp.primaryStage);
			destination = file.getAbsolutePath();

			javaPreferences.setDestination(destination);
		}

	}

	public static String getDestination() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("destination", null);
		if (filePath != null)
			return filePath;
		else
			return null;
	}

	public static String getLibraryDest(String title) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get(title, null);
		if (filePath != null)
			return filePath;
		else
			return null;
	}

	public static int getNumLibraries() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		return prefs.getInt("numLibraries", 0);
	}

	public static void setDestination(String destination) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (destination != null) {
			prefs.put("destination", destination);

		} else {
			prefs.remove("destination");

		}
	}

	public static void setLibraryDest(String library, String destination) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		prefs.put(library, destination);

	}

	public static void setNumLibraries(int numLibraries) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);

		prefs.putInt("numLibraries", numLibraries);

	}
}
