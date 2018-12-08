package pl.kulig.library;

/**
 * Created by Paweł Kulig on 08.12.2018.
 */
public class BookActionResult {

    public boolean wasSuccessful;

    public String errorMessage;

    public BookActionResult(boolean wasSuccessful, String errorMessage) {
        this.wasSuccessful = wasSuccessful;
        this.errorMessage = errorMessage;
    }

    public boolean wasSuccessful() {
        return wasSuccessful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookActionResult that = (BookActionResult) o;

        if (wasSuccessful != that.wasSuccessful) return false;
        return errorMessage != null ? errorMessage.equals(that.errorMessage) : that.errorMessage == null;
    }

    @Override
    public int hashCode() {
        int result = (wasSuccessful ? 1 : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        return result;
    }
}
