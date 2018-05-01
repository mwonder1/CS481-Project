package Classes;

import java.io.File;
import java.util.prefs.Preferences;

import javafx.stage.FileChooser;
import main.java.MainApp;

public class javaPreferences {

	public static String destination;

	public static void checkPreferences() {
		if (javaPreferences.getDestination() == null) {

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
		if (destination != null) {
			prefs.put(library, destination);

		} else {
			prefs.remove("destination");

		}
	}

}
