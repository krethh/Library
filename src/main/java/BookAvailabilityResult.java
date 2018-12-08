/**
 * Created by Paweł Kulig on 08.12.2018.
 */
public class BookAvailabilityResult {

    private boolean available;

    private String message;

    private Book book;

    public BookAvailabilityResult(boolean available, String message, Book book) {
        this.available = available;
        this.message = message;
        this.book = book;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getMessage() {
        return message;
    }

    public Book getBook() {
        return book;
    }
}
