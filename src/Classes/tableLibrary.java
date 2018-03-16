package Classes;

import javafx.beans.property.SimpleStringProperty;

public class tableLibrary {

	private SimpleStringProperty title, uniqueWords, totalWords, numBooks;

	public tableLibrary(String title, String uniqueWords, String totalWords, String numBooks) {
		super();
		this.title = new SimpleStringProperty(title);
		this.uniqueWords = new SimpleStringProperty(uniqueWords);
		this.totalWords = new SimpleStringProperty(totalWords);
		this.numBooks = new SimpleStringProperty(numBooks);

	}

	public String getNumBooks() {
		return numBooks.get();
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

	public void setAge(String numBooks) {
		this.numBooks = new SimpleStringProperty(numBooks);
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
