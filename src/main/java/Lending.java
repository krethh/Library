/**
 * Created by Paweł Kulig on 08.12.2018.
 */
public class Lending {

    private Book book;

    private LibraryClient lendingPerson;

    public Lending(Book book, LibraryClient lendingPerson) {
        this.book = book;
        this.lendingPerson = lendingPerson;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LibraryClient getLendingPerson() {
        return lendingPerson;
    }

    public void setLendingPerson(LibraryClient lendingPerson) {
        this.lendingPerson = lendingPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lending lending = (Lending) o;

        if (book != null ? !book.equals(lending.book) : lending.book != null) return false;
        return lendingPerson != null ? lendingPerson.equals(lending.lendingPerson) : lending.lendingPerson == null;
    }

    @Override
    public int hashCode() {
        int result = book != null ? book.hashCode() : 0;
        result = 31 * result + (lendingPerson != null ? lendingPerson.hashCode() : 0);
        return result;
    }
}
