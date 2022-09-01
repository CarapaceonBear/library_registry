package Book;

import org.json.JSONObject;

import java.util.List;

public class BookLoaner {

    public String checkOutBook(List<Book> library, String id) {
        for (int i = 0; i < library.size(); i++) {
            BookJsonExporter exporter = new BookJsonExporter();
            library.get(i).export(exporter);
            JSONObject exportedBook = exporter.exportData();

            if (exportedBook.getString("Number").equals(id)) {
                if (exportedBook.getString("IsOnLoan").equals("false")) {
                    int timesLoaned = Integer.parseInt(exportedBook.getString("TimesLoaned"));
                    exportedBook.put("TimesLoaned", Integer.toString(timesLoaned + 1));
                    exportedBook.put("IsOnLoan", "true");
                    library.remove(i);
                    BookJsonImporter importer = new BookJsonImporter(exportedBook);
                    Book updatedBook = new Book(importer);
                    library.add(updatedBook);
                    return "Book now loaned, enjoy\n";
                } else {
                    return "Selected book is on loan\n";
                }
            }
        }
        return "Book with given id not found\n";
    }

    public String returnBook(List<Book> library, String id) {
        for (int i = 0; i < library.size(); i++) {
            BookJsonExporter exporter = new BookJsonExporter();
            library.get(i).export(exporter);
            JSONObject exportedBook = exporter.exportData();

            if (exportedBook.getString("Number").equals(id)) {
                if (exportedBook.getString("IsOnLoan").equals("true")) {
                    exportedBook.put("IsOnLoan", "false");
                    library.remove(i);
                    BookJsonImporter importer = new BookJsonImporter(exportedBook);
                    Book updatedBook = new Book(importer);
                    library.add(updatedBook);
                    return "Book now returned, thank you\n";
                } else {
                    return "Selected book is not on loan\n";
                }
            }
        }
        return "Book with given id not found\n";
    }
}
