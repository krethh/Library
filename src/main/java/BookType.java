/**
 * Created by Paweł Kulig on 08.12.2018.
 */
public class BookType {

    public BookType(String title, Integer year, String author) {
        this.title = title;
        this.year = year;
        this.author = author;
    }

    private String title;

    private Integer year;

    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookType bookType = (BookType) o;

        if (title != null ? !title.equals(bookType.title) : bookType.title != null) return false;
        if (year != null ? !year.equals(bookType.year) : bookType.year != null) return false;
        return author != null ? author.equals(bookType.author) : bookType.author == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                '}';
    }
}
