package Book;

import org.json.JSONObject;

public class BookJsonImporter implements Book.Importer {

    private final String importingNumber;
    private final String importingTitle;
    private final String importingAuthor;
    private final String importingGenre;
    private final String importingSubGenre;
    private final String importingPublisher;
    private final String importingTimesLoaned;
    private final String importingIsOnLoan;

    public BookJsonImporter(JSONObject bookObject) {
        this.importingNumber = bookObject.getString("Number");
        this.importingTitle = bookObject.getString("Title");
        this.importingAuthor = bookObject.getString("Author");
        this.importingGenre = bookObject.getString("Genre");
        this.importingSubGenre = bookObject.getString("SubGenre");
        this.importingPublisher = bookObject.getString("Publisher");
        this.importingTimesLoaned = bookObject.getString("TimesLoaned");
        this.importingIsOnLoan = bookObject.getString("IsOnLoan");
    }

    public String fetchNumber() { return importingNumber; }

    public String fetchTitle() { return importingTitle; }

    public String fetchAuthor() { return importingAuthor; }

    public String fetchGenre() { return importingGenre; }

    public String fetchSubGenre() { return importingSubGenre; }

    public String fetchPublisher() { return importingPublisher; }

    public int fetchTimesLoaned() { return Integer.parseInt(importingTimesLoaned); }

    public boolean fetchIsOnLoan() { return Boolean.parseBoolean(importingIsOnLoan); }
}
