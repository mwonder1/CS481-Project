package Classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import main.java.MainApp;

public class Library implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static ArrayList<Book> systemLibrary = new ArrayList<>();
	public static ArrayList<Library> libraries = new ArrayList<>();

	public static void addBooktoLibrary(Library library, Book book) throws FileNotFoundException {

		boolean insystem = CompareBook(library, book);

		if (insystem == true)
			return;
		else {
			library.booksList.add(book);
		}
	}

	public static void createLibrary(String name) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		prefs.putInt("numLibraries", prefs.getInt("numLibraries", 0) + 1);

		String title = name;
		ArrayList<Book> booksList = new ArrayList<>();
		Library newLib = new Library(title, booksList);
		Library.libraries.add(newLib);

	}

	public static void createLibrary(String name, ArrayList<Book> booksList) {

		String title = name;
		Library newLib = new Library(title, booksList);
		Library.libraries.add(newLib);

	}

	public static void deleteFromSystem(tableBook book) {

		for (int i = 0; i < Library.systemLibrary.size(); i++) {

			Book value = Library.systemLibrary.get(i);

			if (book.getUniqueWords().equals(Integer.toString(value.getUniqueWords().size()))) {

				Library.systemLibrary.remove(i);
			}
		}
	}

	// public static void deleteLibrary(Library library) throws
	// BackingStoreException {
	// for (int i = 0; i < Library.libraries.size(); i++) {
	//
	// if (library.getTitle().equals(Library.libraries.get(i).getTitle())) {
	// Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	// prefs.putInt("numLibraries", prefs.getInt("numLibraries", 0) - 1);
	//
	// Library.libraries.remove(i);
	// }
	// }
	// }

	public static void deleteLibrary(tableLibrary library) {

		for (int i = 0; i < Library.libraries.size(); i++) {

			if (library.getTitle().equals(Library.libraries.get(i).getTitle())) {
				Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
				prefs.putInt("numLibraries", prefs.getInt("numLibraries", 0) - 1);

				Library.libraries.remove(i);

			}
		}
	}

	public static void loadLibraries() throws ClassNotFoundException, IOException, BackingStoreException {

		ObjectInputStream objectinputstream = null;
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);

		for (int i = 0; i < javaPreferences.getNumLibraries(); i++) {
			try {
				FileInputStream streamIn = new FileInputStream(
						prefs.get(javaPreferences.getDestination().concat(Integer.toString(i)),
								javaPreferences.getDestination().concat(Integer.toString(i))));
				objectinputstream = new ObjectInputStream(streamIn);
				Library library = (Library) objectinputstream.readObject();

				if (library.getTitle() != null) {
					Library.libraries.add(library);
				} else {
					break;
				}
				// for (int i = 0; i < Library.libraries.size(); i++) {
				// Library.libraries.add(library);
				// }

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (objectinputstream != null) {
					objectinputstream.close();
				}
			}
		}
		javaPreferences.numLibraries = Library.libraries.size();
	}

	public static ArrayList<Book> mergeLibraries(Library lib1, Library lib2) {

		lib1.booksList.removeAll(lib2.booksList);
		lib1.booksList.addAll(lib2.booksList);

		return lib1.booksList;

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

	public ArrayList<Book> getBooksList() {
		return booksList;
	}

	public String getTitle() {
		return title;
	}

	public String setTitle(String name) {
		title = name;
		return title;
	}

}
