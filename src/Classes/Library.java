package Classes;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

	public static ArrayList<Book> systemLibrary = new ArrayList<>();
	public static List<Map<String, ArrayList<Book>>> libraries = new ArrayList<>();

	public static void addBooktoLibrary(ArrayList<Book> library, Book book) throws FileNotFoundException {

		boolean insystem = CompareBook(library, book);

		if (insystem == true) {

			// System.out.println("Unable to add book: " + library.getTitle() + "...\n" +
			// ("Already in this library.\n"));

		}

		else {
			int i = library.size();
			library.add(i, book);
		}

	}

	public static void createLibrary(String name) {

		Map<String, ArrayList<Book>> newLib = new HashMap<>();
		newLib.put(name, null);
		Library.libraries.add(newLib);

	}

	public static void deleteFromSystem(tableBook book) {

		for (int i = 0; i < Library.systemLibrary.size(); i++) {

			Book value = Library.systemLibrary.get(i);

			if (book.getUniqueWords().equals(Integer.toString(value.getUniqueWords().size()))) {

				Library.systemLibrary.remove(i);
				System.out.println(value.getTitle() + " successfully removed from system.");
			}
		}
	}

	static boolean CompareBook(ArrayList<Book> library, Book book) throws FileNotFoundException {

		if (library.size() == 0) {
			return false;
		} else {

			for (int i = 0; i < library.size(); i++) {

				Book value = library.get(i);

				if (book.getTitle().equals(value.getTitle())) {
					return true;
				}
			}

		}
		return false;

	}

	private String title;

	public String getTitle() {
		return title;
	}

	protected String setTitle(String name) {
		title = name;
		return title;
	}

}
