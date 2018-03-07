package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
			int uniqueWords = getUniqueWords(bookFile);

			Book book = new Book(bookFile, author, ISBN, title, age, numWords, uniqueWords);
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

	public static int getUniqueWords(File f) throws IOException {
		FileInputStream in = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine = br.readLine();
		Set<String> bookWords = new HashSet<>();

		while (strLine != null) {

			if (!strLine.trim().equals("")) {
				String[] words = strLine.split(" ");

				for (String word : words) {
					String cleanWord = word.toLowerCase().replace(",", "").replace("!", "").replace("?", "")
							.replace(".", "");

					bookWords.add(cleanWord);

				}
			}
			strLine = br.readLine();
		}
		br.close();
		return bookWords.size();

	}

	public static void main(String[] args) throws IOException

	{

		File newBook = new File("C:\\Users\\mwond\\Desktop/A Fruit is a Suitcase For Seeds.txt");
		// File newBook = new File("C:\\Users\\Mike\\Desktop/fuck.txt");

		AddBook(newBook);

		System.out.println("Size of array is: " + booksList.size() + "\n");
		for (int i = 0; i < booksList.size(); i++) {
			Book book = booksList.get(i);
			System.out.println("Index " + i);
			System.out.println("Title: " + book.title);
			System.out.println("Unique Words: " + book.uniqueWords);
			System.out.println("Total Words: " + book.numWords + "\n");
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

	public Book(File bookFile, String author, int ISBN, String title, int age, int numWords, int uniqueWords) {
		this.title = title;
		this.numWords = numWords;
		this.age = age;
		this.author = author;
		this.ISBN = ISBN;
		this.uniqueWords = uniqueWords;

	}

}