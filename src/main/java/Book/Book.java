package Book;

public class Book {

    private String number;
    private String title;
    private String author;
    private String genre;
    private String subGenre;
    private String publisher;
    private int timesLoaned = 0;
    private boolean isOnLoan = false;

    public Book() {}

    interface Importer {
        String fetchNumber();
        String fetchTitle();
        String fetchAuthor();
        String fetchGenre();
        String fetchSubGenre();
        String fetchPublisher();
        int fetchTimesLoaned();
        boolean fetchIsOnLoan();
    }
    public Book(Importer source) {
        number = source.fetchNumber();
        title = source.fetchTitle();
        author = source.fetchAuthor();
        genre = source.fetchGenre();
        subGenre = source.fetchSubGenre();
        publisher = source.fetchPublisher();
        timesLoaned = source.fetchTimesLoaned();
        isOnLoan = source.fetchIsOnLoan();
    }

    interface Exporter {
        void storeNumber(String number);
        void storeTitle(String title);
        void storeAuthor(String author);
        void storeGenre(String genre);
        void storeSubGenre(String subGenre);
        void storePublisher(String publisher);
        void storeTimesLoaned(int timesLoaned);
        void storeIsOnLoan(boolean isOnLoan);
    }
    public void export(Exporter destination) {
        destination.storeNumber(number);
        destination.storeTitle(title);
        destination.storeAuthor(author);
        destination.storeGenre(genre);
        destination.storeSubGenre(subGenre);
        destination.storePublisher(publisher);
        destination.storeTimesLoaned(timesLoaned);
        destination.storeIsOnLoan(isOnLoan);
    }


}
