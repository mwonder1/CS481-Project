package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {

	public static ArrayList<Book> booksList = new ArrayList<>();

	public static void AddBook(File bookFile) throws FileNotFoundException {

		boolean insystem = CompareBook(bookFile);

		if (insystem == true) {

			System.out.println(setTitle(bookFile) + " is already in the system library.");

		}

		else {

			int i = booksList.size();
			String author = setAuthor(bookFile);
			int ISBN = setISBN(bookFile);
			String title = setTitle(bookFile);
			int age = setAge(bookFile);
			int numWords = setNumWords(bookFile);

			Book book = new Book(bookFile, author, ISBN, title, age, numWords);
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

	public static void main(String[] args) throws IOException

	{

		File newBook = new File("C:\\Users\\mwond\\Desktop/fuck.txt");
		File newBook2 = new File("C:\\Users\\mwond\\Desktop/shit.txt");
		// File newBook = new File("C:\\Users\\Mike\\Desktop/fuck.txt");
		// File newBook2 = new File("C:\\Users\\Mike\\Desktop/shit.txt");
		AddBook(newBook);
		AddBook(newBook2);
		AddBook(newBook2);

		System.out.println("Size of array is: " + booksList.size() + "\n");
		for (int i = 0; i < booksList.size(); i++) {
			Book book = booksList.get(i);
			System.out.println("Index " + i);
			System.out.println("Title: " + book.title);
			System.out.println("Number of Words: " + book.numWords + "\n");
		}
		DeleteFromSystem("shit");
		System.out.println("Size of array is: " + booksList.size());

	}

	private static int setAge(File bookFile) {
		int age = 0;
		return age;
		// TODO Auto-generated method stub

	}

	private static String setAuthor(File bookFile) {
		String author = null;
		return author;
		// TODO Auto-generated method stub

	}

	private static int setISBN(File bookFile) {
		int ISBN = 0;
		return ISBN;
		// TODO Auto-generated method stub

	}

	public static int setNumWords(File f) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new FileInputStream(f))) {

			int numWords = 0;
			while (sc.hasNext()) {
				sc.next();
				numWords++;
			}
			return numWords;
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

	private int numWords;

	private String author;
	private int ISBN;
	private int age;
	private String title;
	private File bookFile;

	public Book(File bookFile, String author, int ISBN, String title, int age, int numWords) {
		this.title = title;
		this.numWords = numWords;
		this.age = age;
		this.author = author;
		this.ISBN = ISBN;

	}

}