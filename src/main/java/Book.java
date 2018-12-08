/**
 * Created by Paweł Kulig on 08.12.2018.
 */
public class Book {

    private Integer ID;

    private BookType bookType;

    public Book(Integer ID, BookType bookType) {
        this.ID = ID;
        this.bookType = bookType;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (ID != null ? !ID.equals(book.ID) : book.ID != null) return false;
        return bookType != null ? bookType.equals(book.bookType) : book.bookType == null;
    }

    @Override
    public int hashCode() {
        int result = ID != null ? ID.hashCode() : 0;
        result = 31 * result + (bookType != null ? bookType.hashCode() : 0);
        return result;
    }
}
