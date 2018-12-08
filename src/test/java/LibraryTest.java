import org.junit.Test;
import pl.kulig.library.BookActionResult;
import pl.kulig.library.BookType;
import pl.kulig.library.Library;
import pl.kulig.library.LibraryClient;

import static org.junit.Assert.assertEquals;

/**
 * Created by Paweł Kulig on 08.12.2018.
 */
public class LibraryTest {

    @Test
    public void testAddBook() {
        Library library = new Library();

        BookType bookType = new BookType(null, 2016, "John Smith");

        BookActionResult result = library.addBook(bookType);

        // assert you cannot add a book without a title
        assertEquals(result.wasSuccessful, false);

        bookType = new BookType("ABC", null, "John Smith");

        result = library.addBook(bookType);

        // assert you cannot add a book without a year
        assertEquals(result.wasSuccessful, false);

        bookType = new BookType("ABC", 2016, null);

        result = library.addBook(bookType);

        // assert you cannot add a book without an author
        assertEquals(result.wasSuccessful, false);

        bookType = new BookType("ABC", 2016, "Me");

        result = library.addBook(bookType);

        // this is fine, should be added
        assertEquals(result.wasSuccessful, true);
    }

    @Test
    public void testLendBook() {
        Library library = new Library();

        BookType bookType = new BookType("1984", 1973, "George Orwell");

        library.addBook(bookType);

        // book should be available to lend
        BookActionResult lendResult = library.lendBook(0, new LibraryClient("Pawel", "Kulig"));

        assertEquals(lendResult.wasSuccessful, true);

        // book shouldn't be available to lend again
        lendResult = library.lendBook(0, new LibraryClient("Pawel", "Kulig"));

        assertEquals(lendResult.wasSuccessful, false);

        library.addBook(bookType);

        // but now available again
        lendResult = library.lendBook(1, new LibraryClient("Pawel", "Kulig"));
        assertEquals(lendResult.wasSuccessful, true);

        library.addBook(bookType);
        lendResult = library.lendBook(2, null);
        assertEquals(lendResult.wasSuccessful, false);
    }

    @Test
    public void testRemoveBook() {
        Library library = new Library();

        BookType bookType = new BookType("1984", 1973, "George Orwell");

        // add and remove book
        library.addBook(bookType);
        BookActionResult result = library.removeBook(0);
        assertEquals(result.wasSuccessful, true);

        // try to remove same book again
        result = library.removeBook(0);
        assertEquals(result.wasSuccessful, false);

        // try to remove lent book
        library.addBook(bookType);
        library.lendBook(0, new LibraryClient("John", "Smith"));
        result = library.removeBook(0);
        assertEquals(result.wasSuccessful, false);
    }

    @Test
    public void testGetFilteredBooks() {
        Library library = new Library();

        library.addBook(new BookType("1984", 1973, "George Orwell"));
        library.addBook(new BookType("1984", 1973, "George Orwell"));
        library.addBook(new BookType("1985", 1973, "George Orwell"));
        library.addBook(new BookType("1986", 1973, "George Orwell"));
        library.addBook(new BookType("Harry Potter 1", 2003, "J. K. Rowling"));
        library.addBook(new BookType("Harry Potter 2", 2004, "J. K. Rowling"));
        library.addBook(new BookType("Harry Potter 3", 2005, "J. K. Rowling"));
        library.addBook(new BookType("Harry Potter 4", 2006, "J. K. Rowling"));
        library.addBook(new BookType("Don Kichote", 1965, "Cervantes"));

        assertEquals(library.getFilteredBooks(new BookType("1984", null, null)).size(), 2);
        assertEquals(library.getFilteredBooks(new BookType("1984", null, "George Orwell")).size(), 2);
        assertEquals(library.getFilteredBooks(new BookType(null, null, "George Orwell")).size(), 4);
        assertEquals(library.getFilteredBooks(new BookType("1984", null, "J. K. Rowling")).size(), 0);
        assertEquals(library.getFilteredBooks(new BookType(null, 1965, null)).size(), 1);
        assertEquals(library.getFilteredBooks(new BookType(null, null, null)).size(), 9);
        assertEquals(library.getFilteredBooks(null).size(), 9);
    }

    @Test
    public void testGetAllBooksOfType() {
        Library library = new Library();

        library.addBook(new BookType("1984", 1973, "George Orwell"));
        library.addBook(new BookType("1984", 1973, "George Orwell"));
        library.addBook(new BookType("1985", 1973, "George Orwell"));
        library.addBook(new BookType("1986", 1973, "George Orwell"));

        assertEquals(2, library.getAllBooksOfType(new BookType("1984", 1973, "George Orwell")).longValue());
        assertEquals(0, library.getAllBooksOfType(null).longValue());
    }

    @Test
    public void testGetAllLentBooksOfType() {
        Library library = new Library();

        library.addBook(new BookType("1984", 1973, "George Orwell"));
        library.addBook(new BookType("1984", 1973, "George Orwell"));
        library.addBook(new BookType("1985", 1973, "George Orwell"));
        library.addBook(new BookType("1986", 1973, "George Orwell"));

        library.lendBook(0, new LibraryClient("John", "Smith"));

        assertEquals(1, library.getAllLentBooksOfType(new BookType("1984", 1973, "George Orwell")).longValue());
        assertEquals(0, library.getAllLentBooksOfType(null).longValue());

        // try to lend same book again
        library.lendBook(0, new LibraryClient("John", "Smith"));
        assertEquals(1, library.getAllLentBooksOfType(new BookType("1984", 1973, "George Orwell")).longValue());

        library.lendBook(1, new LibraryClient("John", "Smith"));
        assertEquals(2, library.getAllLentBooksOfType(new BookType("1984", 1973, "George Orwell")).longValue());
    }
}