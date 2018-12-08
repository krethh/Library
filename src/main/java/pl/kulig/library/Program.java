package pl.kulig.library;

import java.util.HashSet;

public class Program {

    public static void main(String[] args) {
        // pl.kulig.Library examples
        Library library = new Library();

        System.out.println("Library just created - check that there's zero books...");
        System.out.println("Books size: " + library.getFilteredBooks(null).size());

        System.out.println();

        System.out.println("Now adding some books...");
        library.addBook(new BookType("Harry Potter and the Philosopher's Stone", 1999, "J. K. Rowling"));
        library.addBook(new BookType("Harry Potter and the Philosopher's Stone", 1999, "J. K. Rowling"));
        library.addBook(new BookType("Don Kichote", 1963, "Sancho Pansa"));

        library.lendBook(0, new LibraryClient("Pawel", "Kulig"));

        System.out.println("Printing information about all books...");
        library.printAllBooksInformation();

        System.out.println();

        System.out.println("Printing details of sample books...");
        library.printBookDetails(0);
        library.printBookDetails(1);

        System.out.println();

        System.out.println("Finding a book before lending...");
        // try to find a book
        HashSet<Book> filteredBooks = (HashSet<Book>) library.getFilteredBooks(new BookType("Don Kichote", null, null));
        filteredBooks.forEach(book -> library.printBookDetails(book.getID()));

        System.out.println(System.lineSeparator() + "Finding a book after lending...");
        // try to find same book, but it's lent now
        library.lendBook(2, new LibraryClient("John", "Smith"));
        filteredBooks = (HashSet<Book>) library.getFilteredBooks(new BookType("Don Kichote", null, null));
        filteredBooks.forEach(book -> library.printBookDetails(book.getID()));
    }
}
