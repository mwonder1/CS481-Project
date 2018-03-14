package main.java;

import javafx.beans.property.SimpleStringProperty;

public class tableBook {

	private SimpleStringProperty title, uniqueWords, totalWords, age;

	public tableBook(String title, String uniqueWords, String totalWords, String age) {
		super();
		this.title = new SimpleStringProperty(title);
		this.uniqueWords = new SimpleStringProperty(uniqueWords);
		this.totalWords = new SimpleStringProperty(totalWords);
		this.age = new SimpleStringProperty(age);
	}

	public String getAge() {
		return age.get();
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

	public void setAge(String age) {
		this.age = new SimpleStringProperty(age);
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
