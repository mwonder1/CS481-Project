package Classes;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Library {

	public static ArrayList<Book> systemLibrary = new ArrayList<>();
	public static ArrayList<Library> libraries = new ArrayList<>();

	public static void createLibrary(String name) {

		String title = name;
		ArrayList<Book> booksList = new ArrayList<>();
		Library newLib = new Library(title, booksList);
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

	public static void deleteLibrary(tableLibrary library) {

		for (int i = 0; i < Library.libraries.size(); i++) {

			Library lib = Library.libraries.get(i);

			if (lib.getTitle().equals(Library.libraries.get(i).getTitle())) {

				Library.libraries.remove(i);
				System.out.println(lib.getTitle() + " successfully removed from system.");
			}
		}
	}

	static boolean CompareBook(Library library, Book book) throws FileNotFoundException {

		if (library.booksList.size() == 0)
			return false;
		else {

			for (int i = 0; i < library.booksList.size(); i++) {

				Book value = library.booksList.get(i);

				if (book.getTitle().equals(value.getTitle()))
					return true;
			}

		}
		return false;

	}

	private ArrayList<Book> booksList;

	private String title;

	public Library(String title, ArrayList<Book> booksList) {
		this.title = title;
		this.booksList = booksList;
	}

	public void addBooktoLibrary(Library library, Book book) throws FileNotFoundException {

		boolean insystem = CompareBook(library, book);

		if (insystem == true) {

			// System.out.println("Unable to add book: " + library.getTitle() + "...\n" +
			// ("Already in this library.\n"));

		}

		else {

			library.booksList.add(book);
		}

	}

	public ArrayList<Book> getBooksList() {
		return booksList;
	}

	public String getTitle() {
		return title;
	}

	protected String setTitle(String name) {
		title = name;
		return title;
	}

}
