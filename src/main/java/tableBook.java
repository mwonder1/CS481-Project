package main.java;

import javafx.beans.property.SimpleStringProperty;

public class tableBook {

	private SimpleStringProperty title, uniqueWords, totalWords;

	public tableBook(String title, String uniqueWords, String totalWords) {
		super();
		this.title = new SimpleStringProperty(title);
		this.uniqueWords = new SimpleStringProperty(uniqueWords);
		this.totalWords = new SimpleStringProperty(totalWords);
	}

	public String getTitle() {
		return title.get();
	}

	public String getTotalWords() {
		return totalWords.get();
	}

	public String getUniqueWords() {
		return uniqueWords.get();
	}

	public void setTitle(String title) {
		this.title = new SimpleStringProperty(title);
	}

	public void setTotalWords(String totalWords) {
		this.totalWords = new SimpleStringProperty(totalWords);
	}

	public void setUniqueWords(String uniqueWords) {
		this.uniqueWords = new SimpleStringProperty(uniqueWords);
	}

}
