package pl.kulig.library;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Paweł Kulig on 08.12.2018.
 */
public class Library {

    private HashSet<Book> books;

    private HashSet<Lending> currentLendings;

    public Library() {
        books = new HashSet<>();
        currentLendings = new HashSet<>();
    }

    public BookActionResult addBook(BookType bookType) {
        if (bookType.getAuthor() == null || bookType.getAuthor().isEmpty()) {
            return new BookActionResult(false, "Please supply valid author.");
        }

        if (bookType.getYear() == null || bookType.getYear() == null) {
            return new BookActionResult(false, "Please supply valid year");
        }

        if (bookType.getTitle() == null || bookType.getTitle().isEmpty()) {
            return new BookActionResult(false, "Please supply valid title");
        }

        int ID = generateNextID();

        books.add(new Book(ID, bookType));
        return new BookActionResult(true, null);
    }

    public BookActionResult lendBook(Integer ID, LibraryClient lendingPerson) {
        BookAvailabilityResult result = isBookAvailable(ID);

        if (!result.isAvailable()) {
            return new BookActionResult(false, result.getMessage());
        }

        if (lendingPerson == null) {
            return new BookActionResult(false, "Please supply lending person");
        }

        currentLendings.add(new Lending(result.getBook(), lendingPerson));

        return new BookActionResult(true, null);
    }

    public BookActionResult removeBook(Integer ID) {
        BookAvailabilityResult result = isBookAvailable(ID);

        if (!result.isAvailable()) {
            return new BookActionResult(false, result.getMessage());
        }

        books.remove(result.getBook());

        return new BookActionResult(true, null);
    }

    public void printAllBooksInformation() {
        String information = books.stream().map(Book::getBookType).distinct().map(bookType -> {
            String info = bookType.toString();

            info += System.lineSeparator() + "Total books: " + getAllBooksOfType(bookType);
            info += System.lineSeparator() + "Lent books: " + getAllLentBooksOfType(bookType);

            return info;
        }).collect(Collectors.joining(System.lineSeparator()));

        System.out.println(information);
    }

    public Set<Book> getFilteredBooks(BookType filterPredicate) {
        if (filterPredicate == null) {
            return books;
        }

        return books.stream()
                .filter(book -> filterPredicate.getTitle() == null || book.getBookType().getTitle().equals(filterPredicate.getTitle()))
                .filter(book -> filterPredicate.getYear() == null || book.getBookType().getYear().equals(filterPredicate.getYear()))
                .filter(book -> filterPredicate.getAuthor() == null || book.getBookType().getAuthor().equals(filterPredicate.getAuthor()))
                .collect(Collectors.toSet());
    }

    public void printBookDetails(Integer ID) {
        Optional<Book> bookOptional = books.stream().filter(book -> book.getID().equals(ID)).findFirst();

        if (!bookOptional.isPresent()) {
            System.out.println("No book with this ID found!");
            return;
        }

        Book book = bookOptional.get();

        Optional<Lending> lendingOptional = currentLendings.stream().filter(lending -> lending.getBook().getID().equals(ID)).findFirst();

        String info =
                "ID: " + ID + System.lineSeparator()
                + "Book title: " + book.getBookType().getTitle() + System.lineSeparator()
                + "Book year: " + book.getBookType().getYear() + System.lineSeparator()
                + "Book author: " + book.getBookType().getAuthor() + System.lineSeparator()
                + (lendingOptional.isPresent() ? "Currently lent to: " + lendingOptional.get().getLendingPerson() : "Currently not lent");

        System.out.println(info);
    }

    public Long getAllBooksOfType(BookType bookType) {
        return books.stream().filter(book -> book.getBookType().equals(bookType)).count();
    }

    public Long getAllLentBooksOfType(BookType bookType) {
        return currentLendings.stream().filter(lending -> lending.getBook().getBookType().equals(bookType)).count();
    }

    public BookAvailabilityResult isBookAvailable(Integer bookID) {
        Optional<Book> book = books.stream().filter(other -> Objects.equals(other.getID(), bookID))
                .findFirst();

        if (!book.isPresent()) {
            return new BookAvailabilityResult(false, "Book with this ID doesn't exist.", null);
        }

        boolean isLent = currentLendings.stream().anyMatch(lending -> Objects.equals(lending.getBook().getID(), bookID));

        if (isLent) {
            return new BookAvailabilityResult(false, "Book with this ID is currently lent", null);
        }

        return new BookAvailabilityResult(true, null, book.get());
    }

    private Integer generateNextID() {
        if (books.size() == 0) {
            return 0;
        }

        else {
            return books.stream().map(Book::getID).max(Integer::compare).get() + 1;
        }
    }
}
