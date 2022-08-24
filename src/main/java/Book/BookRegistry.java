package Book;

import java.util.ArrayList;
import java.util.List;

public class BookRegistry {

    private final List<Book> library = new ArrayList<>();

    public BookRegistry() {}

    public void compileLibrary() {
        System.out.println("read dataset, add to list");
    }

    public Book findBook(double id) {
        System.out.println("need to search each book, exporting one by one");
        return new Book();
    }
}
