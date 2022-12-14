package Book;

import Utilities.JsonToBookListCompiler;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookRegistry implements BookRegistrySearch, BookRegistryLoan {

    private List<Book> library = new ArrayList<>();
    private final File dataFile;
    private final BookLoaner loaner = new BookLoaner();

    public BookRegistry(File dataFile) {
        this.dataFile = dataFile;
    }

    public void compileLibrary() {
        JsonToBookListCompiler compiler = new JsonToBookListCompiler(dataFile);
        library = compiler.compile();
    }

    private List<JSONObject> listBooks() {
        List<JSONObject> exportedBooks = new ArrayList<>();
        for (Book book : library) {
            BookJsonExporter exporter = new BookJsonExporter();
            book.export(exporter);
            exportedBooks.add(exporter.exportData());
        }
        return exportedBooks;
    }

    public Optional<JSONObject> findBookById(String id) {
        List<JSONObject> bookList = listBooks();
        return bookList.stream().filter(
                b -> b.getString("Number").equals(id))
                .findFirst();
    }

    public List<JSONObject> searchBooks(String searchTerm) {
        List<JSONObject> bookList = listBooks();
        return bookList.stream().filter(
                b -> b.getString("Title").toLowerCase().contains(searchTerm.toLowerCase())
                        || b.getString("Author").toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean isBookInLibrary(String id) {
        return loaner.isBookInLibrary(library, id);
    }

    public boolean isBookOnLoan(String id) {
        return loaner.isBookOnLoan(library, id);
    }

    public String checkOutBook(String id) {
        return loaner.checkOutBook(library, id);
    }

    public String returnBook(String id) {
        return loaner.returnBook(library, id);
    }
}
