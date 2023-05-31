public class BookClassData {

    String book_id, book_name, book_author;
    int book_quantity;


    public BookClassData(String id, String name, String author, int quantity) {
        this.book_id = id;
        this.book_name = name;
        this.book_author = author;
        this.book_quantity = quantity;
    }

    public String getBook_id() {
        return this.book_id;
    }

    public String getBook_name() {
        return this.book_name;
    }

    public String getBook_author() {
        return this.book_author;
    }

    public int getBook_quantity() {
        return this.book_quantity;
    }

}
