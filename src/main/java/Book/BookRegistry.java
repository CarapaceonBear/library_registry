package Book;

import Utilities.JsonToBookListCompiler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BookRegistry {

    private List<Book> library = new ArrayList<>();
    private final File dataFile;

    public BookRegistry(File dataFile) {
        this.dataFile = dataFile;
    }

    public void compileLibrary() {
        JsonToBookListCompiler compiler = new JsonToBookListCompiler(dataFile);
        library = compiler.compile();
    }

    public void listBooks() {
        BookJsonExporter exporter = new BookJsonExporter();
        for (Book book : library) {
            book.export(exporter);
            System.out.println(exporter.exportData());
        }
    }

    public Book findBook(double id) {
        System.out.println("need to search each book, exporting one by one");
        return new Book();
    }
}
