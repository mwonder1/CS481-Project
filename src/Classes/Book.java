package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Book {

	public static void addBook(File bookFile) throws IOException {

		boolean insystem = CompareBook(bookFile);

		if (insystem == true) {

			System.out
					.println("Unable to add book: " + setTitle(bookFile) + "...\n" + ("Already in system library.\n"));

		}

		else {
			File file = bookFile;
			int i = Library.systemLibrary.size();
			String author = setAuthor(bookFile);
			String ISBN = setISBN(bookFile);
			String title = setTitle(bookFile);
			String age = setAge(bookFile);
			int numWords = getNumWords(bookFile);
			Map<String, Integer> uniqueWords = getUniqueWords(bookFile);

			Book book = new Book(file, author, ISBN, title, age, numWords, uniqueWords);
			Library.systemLibrary.add(i, book);
			System.out.println(
					"Successfully added book: " + setTitle(bookFile) + "...\n" + ("into the System Library.\n"));

		}
	}

	static boolean CompareBook(File bookFile2) throws FileNotFoundException {

		if (Library.systemLibrary.size() == 0)
			return false;
		else {

			for (int i = 0; i < Library.systemLibrary.size(); i++) {

				Book value = Library.systemLibrary.get(i);

				if (setTitle(bookFile2).equals(value.title))
					return true;
			}

		}
		return false;

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

				String[] words = strLine.split("[\\W]+");

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

		Map<String, Integer> unsortMap = bookWords;
		Map<String, Integer> treeMap = new TreeMap<>();
		new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}

		};
		treeMap.putAll(unsortMap);
		br.close();
		return treeMap;

	}

	protected static String setAge(File f) {
		String age = "";
		return age;
	}

	protected static String setAuthor(File f) {
		String author = "";
		return author;
	}

	protected static String setISBN(File f) {
		String ISBN = "";
		return ISBN;
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
	private String author;
	private String ISBN;
	private String age;

	public Book(File file, String author, String ISBN, String title, String age, int numWords,
			Map<String, Integer> uniqueWords) {
		this.file = file;
		this.title = title;
		this.numWords = numWords;
		this.age = age;
		this.author = author;
		this.ISBN = ISBN;
		this.uniqueWords = uniqueWords;

	}

	public String getAge() {
		return age;
	}

	public String getAuthor() {
		return author;
	}

	public File getFile() {

		return file;
	}

	public String getISBN() {
		return ISBN;
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

	public void setAge(String age) {
		this.age = age;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}