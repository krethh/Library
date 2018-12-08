import java.util.HashSet;

public class Program {

    public static void main(String[] args) {
        // Library examples
        Library library = new Library();

        library.addBook(new BookType("Harry Potter and the Philosopher's Stone", 1999, "J. K. Rowling"));
        library.addBook(new BookType("Harry Potter and the Philosopher's Stone", 1999, "J. K. Rowling"));
        library.addBook(new BookType("Don Kichote", 1963, "Sancho Pansa"));

        library.lendBook(0, new LibraryClient("Pawel", "Kulig"));

        library.printAllBooksInformation();

        System.out.println();

        library.printBookDetails(0);
        library.printBookDetails(1);

        System.out.println();

        // try to find a book
        HashSet<Book> filteredBooks = (HashSet<Book>) library.getFilteredBooks(new BookType("Don Kichote", null, null));
        filteredBooks.forEach(book -> library.printBookDetails(book.getID()));

        // try to find same book, but it's lent now
        library.lendBook(2, new LibraryClient("John", "Smith"));
        filteredBooks = (HashSet<Book>) library.getFilteredBooks(new BookType("Don Kichote", null, null));
        filteredBooks.forEach(book -> library.printBookDetails(book.getID()));
    }
}
