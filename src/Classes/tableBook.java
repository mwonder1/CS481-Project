package Classes;

import javafx.beans.property.SimpleStringProperty;

public class tableBook {

	private SimpleStringProperty title, uniqueWords, totalWords, age, author, ISBN;

	public tableBook(String title, String uniqueWords, String totalWords, String age, String author, String ISBN) {
		super();
		this.title = new SimpleStringProperty(title);
		this.uniqueWords = new SimpleStringProperty(uniqueWords);
		this.totalWords = new SimpleStringProperty(totalWords);
		this.age = new SimpleStringProperty(age);
		this.author = new SimpleStringProperty(author);
		this.ISBN = new SimpleStringProperty(ISBN);
	}

	public String getAge() {
		return age.get();
	}

	public String getAuthor() {
		return author.get();
	}

	public String getISBN() {
		return ISBN.get();
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

	public void setAuthor(String author) {
		this.author = new SimpleStringProperty(author);
	}

	public void setISBN(String ISBN) {
		this.ISBN = new SimpleStringProperty(ISBN);
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