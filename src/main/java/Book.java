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

	public static void AddBook(File bookFile) throws IOException {

		boolean insystem = CompareBook(bookFile);

		if (insystem == true) {

			System.out.println(setTitle(bookFile) + " is already in the system library.");

		}

		else {

			int i = booksList.size();
			String author = getAuthor(bookFile);
			int ISBN = getISBN(bookFile);
			String title = setTitle(bookFile);
			int age = getAge(bookFile);
			int numWords = getNumWords(bookFile);
			int uniqueWords = getUniqueWords(bookFile).size();
			Map<String, Integer> frequency = getUniqueWords(bookFile);

			Book book = new Book(bookFile, author, ISBN, title, age, numWords, uniqueWords, frequency);
			booksList.add(i, book);
		}
	}

	public static boolean CompareBook(File bookFile2) throws FileNotFoundException {

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

	public static void DeleteFromSystem(String title)

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

	public static Map<String, Integer> getUniqueWords(File f) throws IOException {
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
		// File newBook = new File("C:\\Users\\Mike\\Desktop/fuck.txt");

		AddBook(newBook);

		System.out.println("Number of books: " + booksList.size() + "\n");
		for (int i = 0; i < booksList.size(); i++) {
			Book book = booksList.get(i);
			System.out.println("Index: " + i);
			System.out.println("Title: " + book.title);
			System.out.println("Unique Words: " + book.uniqueWords);
			System.out.println("Total Words: " + book.numWords + "\n");
			System.out.println("Unique words and there frequencies: " + book.frequency);
		}

	}

	public static String setTitle(File f) {

		String title = f.getName();
		int pos = title.lastIndexOf(".");
		if (pos > 0) {
			title = title.substring(0, pos);
		}
		return title;

	}

	private int uniqueWords;

	private int numWords;
	private String author;
	private int ISBN;
	private int age;
	private String title;
	private File bookFile;
	private Map<String, Integer> frequency;

	public Book(File bookFile, String author, int ISBN, String title, int age, int numWords, int uniqueWords,
			Map<String, Integer> frequency) {
		this.title = title;
		this.numWords = numWords;
		this.age = age;
		this.author = author;
		this.ISBN = ISBN;
		this.uniqueWords = uniqueWords;
		this.frequency = frequency;

	}

}