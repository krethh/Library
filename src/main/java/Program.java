public class Program {

    public static void main(String[] args) {
        // Library examples
        Library library = new Library();

        library.addBook(new BookType("Harry Potter and the Philosopher's Stone", 1999, "J. K. Rowling"));
        library.addBook(new BookType("Harry Potter and the Philosopher's Stone", 1999, "J. K. Rowling"));
        library.addBook(new BookType("Don Kichote", 1963, "Sancho Pansa"));

        library.lendBook(0, new LibraryClient("Pawel", "Kulig"));

        library.printAllBooksInformation();
    }
}
