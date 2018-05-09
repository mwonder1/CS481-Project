package main.java.controllers;

import java.io.IOException;
import java.util.function.Consumer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import main.java.MainApp;

public class ViewControllers {

	public static Alert createAlertWithOptOut(AlertType type, String title, String headerText, String message,
			String optOutMessage, Consumer<Boolean> optOutAction, ButtonType... buttonTypes) {
		Alert alert = new Alert(type);
		// Need to force the alert to layout in order to grab the graphic,
		// as we are replacing the dialog pane with a custom pane
		alert.getDialogPane().applyCss();
		Node graphic = alert.getDialogPane().getGraphic();
		// Create a new dialog pane that has a checkbox instead of the hide/show details
		// button
		// Use the supplied callback for the action of the checkbox
		alert.setDialogPane(new DialogPane() {
			@Override
			protected Node createDetailsButton() {
				CheckBox optOut = new CheckBox();
				optOut.setText(optOutMessage);
				optOut.setOnAction(e -> optOutAction.accept(optOut.isSelected()));
				return optOut;
			}
		});
		alert.getDialogPane().getButtonTypes().addAll(buttonTypes);
		alert.getDialogPane().setContentText(message);
		// Fool the dialog into thinking there is some expandable content
		// a Group won't take up any space if it has no children
		alert.getDialogPane().setExpandableContent(new Group());
		alert.getDialogPane().setExpanded(true);
		// Reset the dialog graphic using the default style
		alert.getDialogPane().setGraphic(graphic);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		return alert;
	}

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
		Alert alert = createAlertWithOptOut(AlertType.CONFIRMATION, "Exit", null, "Are you sure you wish to exit?",
				"Do not ask again", null, ButtonType.YES, ButtonType.NO);
		if (alert.showAndWait().filter(t -> t == ButtonType.YES).isPresent()) {

		}

		// FXMLLoader loader = new FXMLLoader();
		// loader.setLocation(MainApp.class.getResource("controllers/SettingsView.fxml"));
		// MainApp.mainLayout = loader.load();
		// Parent newContent = MainApp.mainLayout;
		// MainApp.primaryStage.getScene().setRoot(newContent);
		// MainApp.primaryStage.show();
	}

}
