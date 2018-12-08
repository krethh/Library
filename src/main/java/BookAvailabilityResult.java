/**
 * Created by Paweł Kulig on 08.12.2018.
 */
public class BookAvailabilityResult {

    private boolean available;

    private String message;

    public BookAvailabilityResult(boolean available, String message) {
        this.available = available;
        this.message = message;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getMessage() {
        return message;
    }
}
