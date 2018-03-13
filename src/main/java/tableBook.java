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

	public void setTitle(SimpleStringProperty title) {
		this.title = title;
	}

	public void setTotalWords(SimpleStringProperty totalWords) {
		this.totalWords = totalWords;
	}

	public void setUniqueWords(SimpleStringProperty uniqueWords) {
		this.uniqueWords = uniqueWords;
	}

}
