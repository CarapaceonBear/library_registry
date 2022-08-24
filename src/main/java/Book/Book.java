package Book;

public class Book {

    private String number;
    private String title;
    private String author;
    private String genre;
    private String subGenre;
    private String publisher;

    public Book() {}

    interface Importer {
        String fetchNumber();
        String fetchTitle();
        String fetchAuthor();
        String fetchGenre();
        String fetchSubGenre();
        String fetchPublisher();
    }
    public Book(Importer source) {
        number = source.fetchNumber();
        title = source.fetchTitle();
        author = source.fetchAuthor();
        genre = source.fetchGenre();
        subGenre = source.fetchSubGenre();
        publisher = source.fetchPublisher();
    }

    interface Exporter {
        void storeNumber(String number);
        void storeTitle(String title);
        void storeAuthor(String author);
        void storeGenre(String genre);
        void storeSubGenre(String subGenre);
        void storePublisher(String publisher);
    }
    public void export(Exporter destination) {
        destination.storeNumber(number);
        destination.storeTitle(title);
        destination.storeAuthor(author);
        destination.storeGenre(genre);
        destination.storeSubGenre(subGenre);
        destination.storePublisher(publisher);
    }


}
