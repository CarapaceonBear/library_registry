package Book;

public interface BookRegistryLoan {
    boolean isBookInLibrary(String id);
    boolean isBookOnLoan(String id);
    String checkOutBook(String id);
    String returnBook(String id);
}
