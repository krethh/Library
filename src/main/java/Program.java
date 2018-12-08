public class Program {

    public static void main(String[] args) {
        // Library examples
        Library library = new Library();

        library.addBook(new BookType("Harry Potter", 1999, "J. K. Rowling"));

        library.printAllBooksInformation();

    }
}
