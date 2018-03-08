package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Book {

	public static ArrayList<Book> booksList = new ArrayList<>();

	public static void addBook(File bookFile) throws IOException {

		boolean insystem = CompareBook(bookFile);

		if (insystem == true) {

			System.out.println(
					"Unable to add book: \"" + setTitle(bookFile) + "\"...\n" + "Already in system library.\n");

		}

		else {
			File file = bookFile;
			int i = booksList.size();
			String author = getAuthor(bookFile);
			int ISBN = getISBN(bookFile);
			String title = setTitle(bookFile);
			int age = getAge(bookFile);
			int numWords = getNumWords(bookFile);
			Map<String, Integer> uniqueWords = getUniqueWords(bookFile);
			Map<String, Integer> frequency = getUniqueWords(bookFile);

			Book book = new Book(file, author, ISBN, title, age, numWords, uniqueWords, frequency);
			booksList.add(i, book);
		}
	}

	private static boolean CompareBook(File bookFile2) throws FileNotFoundException {

		if (booksList.size() == 0)
			return false;
		else {

			for (int i = 0; i < booksList.size(); i++) {

				Book value = booksList.get(i);

				if (setTitle(bookFile2).equals(value.title))
					return true;
			}

		}
		return false;

	}

	private static void deleteFromSystem(String title)

	{

		for (int i = 0; i < booksList.size(); i++) {

			Book value = booksList.get(i);

			if (title.equals(value.title)) {

				booksList.remove(i);
				System.out.println(value.title + " successfully removed from system.");

			}

		}

	}

	private static int getAge(File bookFile) {
		int age = 0;
		return age;
		// TODO Auto-generated method stub

	}

	private static String getAuthor(File bookFile) {
		String author = null;
		return author;
		// TODO Auto-generated method stub

	}

	private static int getISBN(File bookFile) {
		int ISBN = 0;
		return ISBN;
		// TODO Auto-generated method stub

	}

	public static int getNumWords(File f) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new FileInputStream(f))) {

			int numWords = 0;
			while (sc.hasNext()) {
				sc.next();
				numWords++;
			}
			return numWords;
		}

	}

	private static Map<String, Integer> getUniqueWords(File f) throws IOException {
		FileInputStream in = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine = br.readLine();
		Map<String, Integer> bookWords = new HashMap<>();

		while (strLine != null) {

			if (!strLine.trim().equals("")) {
				String[] words = strLine.split(" ");

				for (String word : words) {
					if (word == null || word.trim().equals("")) {
						continue;
					}
					String processed = word.toLowerCase();

					if (bookWords.containsKey(processed)) {
						bookWords.put(processed, bookWords.get(processed) + 1);
					} else {
						bookWords.put(processed, 1);
					}
				}
			}
			strLine = br.readLine();
		}
		br.close();
		return bookWords;

	}

	public static void main(String[] args) throws IOException

	{

		File newBook = new File("C:\\Users\\mwond\\Desktop/A Fruit is a Suitcase For Seeds.txt");
		File newBook2 = new File("C:\\Users\\mwond\\Desktop/A life like mine.txt");
		File newBook3 = new File("C:\\Users\\mwond\\Desktop/Eagle Song.txt");
		File newBook4 = new File("C:\\Users\\mwond\\Desktop/About Insects.txt");
		File newBook5 = new File("C:\\Users\\mwond\\Desktop/Aesop's Fables.txt");

		addBook(newBook);
		addBook(newBook2);
		// addBook(newBook3);
		// addBook(newBook4);
		// addBook(newBook5);
		//
		// addBook(newBook2);
		deleteFromSystem("Eagle Song");

		System.out.println("Number of books in system library: " + booksList.size() + "\n");
		for (int i = 0; i < booksList.size(); i++) {
			Book book = booksList.get(i);
			System.out.println("Library Index: " + i);
			System.out.println("Title: " + book.title);
			System.out.println("Unique Words: " + book.uniqueWords);
			System.out.println("Total Words: " + book.numWords + "\n");
			System.out.println("Unique words and their frequencies: \n" + book.frequency + "\n\n");
		}

		WritetoXML.writeOutput();

	}

	protected static String setTitle(File f) {

		String title = f.getName();
		int pos = title.lastIndexOf(".");
		if (pos > 0) {
			title = title.substring(0, pos);
		}
		return title;

	}

	private File file;

	private String title;

	private int numWords;

	protected Map<String, Integer> uniqueWords;
	@SuppressWarnings("unused")
	private String author;
	@SuppressWarnings("unused")
	private int ISBN;
	@SuppressWarnings("unused")
	private int age;

	private Map<String, Integer> frequency;

	public Book(File file, String author, int ISBN, String title, int age, int numWords,
			Map<String, Integer> uniqueWords, Map<String, Integer> frequency) {
		this.file = file;
		this.title = title;
		this.numWords = numWords;
		this.age = age;
		this.author = author;
		this.ISBN = ISBN;
		this.uniqueWords = uniqueWords;
		this.frequency = frequency;

	}

	public File getFile() {

		return file;
	}

	public String getTitle() {

		return title;
	}

	public int getTotalWords() {

		return numWords;
	}

	public Map<String, Integer> getUniqueWords() {

		return uniqueWords;
	}

}