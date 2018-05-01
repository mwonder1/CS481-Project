package Classes;

import java.util.prefs.Preferences;

import main.java.MainApp;

public class javaPreferences {

	public static String getDestination() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("destination", null);
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

}
