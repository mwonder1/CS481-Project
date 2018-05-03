package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Book implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static List<String> success = new ArrayList<>();
	public static List<String> fail = new ArrayList<>();
	
	public static void loadBooks() throws ClassNotFoundException, IOException {

		ObjectInputStream objectinputstream = null;
		System.out.println("Loading books...");
		try {
			FileInputStream streamIn = new FileInputStream(javaPreferences.getDestination());
			objectinputstream = new ObjectInputStream(streamIn);
			@SuppressWarnings("unchecked")
			ArrayList<Book> booksList = (ArrayList<Book>) objectinputstream.readObject();
			for (int i = 0; i < booksList.size(); i++) {

				File file = booksList.get(i).getFile();
				String author = booksList.get(i).getAuthor();
				String ISBN = booksList.get(i).getISBN();
				String title = booksList.get(i).getTitle();
				String age = booksList.get(i).getAge();
				booksList.get(i);
				int numWords = Book.getNumWords(booksList.get(i).getFile());
				Map<String, Integer> uniqueWords = booksList.get(i).getUniqueWords();
				String genre = booksList.get(i).getGenre();
				String complete = booksList.get(i).getComplete();

				Library.systemLibrary
						.add(new Book(file, author, ISBN, title, age, numWords, uniqueWords, genre, complete));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objectinputstream != null) {
				objectinputstream.close();
			}
		}
		System.out.println("Done.");

	}

	public static void addBook(File bookFile) throws IOException {

		success.removeAll(success);
		fail.removeAll(fail);
		boolean insystem = CompareBook(bookFile);

		if (insystem == true) {

			String title = setTitle(bookFile);
			fail.add(title);
		} else {
			File file = bookFile;
			int i = Library.systemLibrary.size();

			String author = setAuthor(bookFile);
			String ISBN = setISBN(bookFile);
			String title = setTitle(bookFile);
			String age = setAge(bookFile);
			String genre = setGenre(bookFile);
			String complete = setComplete(bookFile);

			int numWords = getNumWords(bookFile);
			Map<String, Integer> uniqueWords = getUniqueWords(bookFile);

			Book book = new Book(file, author, ISBN, title, age, numWords, uniqueWords, genre, complete);
			Library.systemLibrary.add(i, book);
			success.add(title);
		}
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
				String[] words = strLine.split("[\\p{P} \\t\\n\\r]");

				for (String word : words) {
					if (word == null || word.trim().equals("") || word.trim().equals("(?<=.)") || word.trim().equals("p{Punct}") ||  word.trim().equals("s") || word.trim().equals("t")) {
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

	private static String setComplete(File bookFile) {
		String complete = "";
		return complete;
	}

	protected static String setAge(File f) {
		String age = "";
		return age;
	}

	protected static String setAuthor(File f) {
		String author = "";
		return author;
	}

	protected static String setGenre(File f) {
		String genre = "";
		return genre;
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

	private File file;
	private String title;
	private int numWords;
	protected Map<String, Integer> uniqueWords;
	private String author;
	private String ISBN;
	private String age;
	private String genre;
	private String complete;

	public Book(File file, String author, String ISBN, String title, String age, int numWords,
			Map<String, Integer> uniqueWords, String genre, String complete) {
		this.file = file;
		this.title = title;
		this.numWords = numWords;
		this.age = age;
		this.author = author;
		this.ISBN = ISBN;
		this.uniqueWords = uniqueWords;
		this.genre = genre;
		this.complete = complete;
	}

	public String getAge() {
		return age;
	}

	public String getAuthor() {
		return author;
	}

	public String getComplete() {
		return complete;
	}

	public File getFile() {

		return file;
	}

	public String getGenre() {
		return genre;
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

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public void setGenre(String string) {
		this.genre = string;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}