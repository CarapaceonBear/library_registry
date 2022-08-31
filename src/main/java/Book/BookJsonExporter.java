package Book;

import org.json.JSONObject;

public class BookJsonExporter implements Book.Exporter {

    private String exportingNumber;
    private String exportingTitle;
    private String exportingAuthor;
    private String exportingGenre;
    private String exportingSubGenre;
    private String exportingPublisher;
    private String exportingTimesLoaned;
    private String exportingIsOnLoan;
    private final JSONObject exportingData = new JSONObject();

    public void storeNumber(String number) { this.exportingNumber = number; }

    public void storeTitle(String title) { this.exportingTitle = title; }

    public void storeAuthor(String author) { this.exportingAuthor = author; }

    public void storeGenre(String genre) { this.exportingGenre = genre; }

    public void storeSubGenre(String subGenre) { this.exportingSubGenre = subGenre; }

    public void storePublisher(String publisher) { this.exportingPublisher = publisher; }

    public void storeTimesLoaned(int timesLoaned) { this.exportingTimesLoaned = Integer.toString(timesLoaned); }

    public void storeIsOnLoan(boolean isOnLoan) { this.exportingIsOnLoan = Boolean.toString(isOnLoan); }

    public JSONObject exportData() {
        exportingData.put("Number", exportingNumber);
        exportingData.put("Title", exportingTitle);
        exportingData.put("Author", exportingAuthor);
        exportingData.put("Genre", exportingGenre);
        exportingData.put("SubGenre", exportingSubGenre);
        exportingData.put("Publisher", exportingPublisher);
        exportingData.put("TimesLoaned", exportingTimesLoaned);
        exportingData.put("IsOnLoan", exportingIsOnLoan);
        return exportingData;
    }
}
