package Book;

public interface BookRegistryLoan {
    String checkOutBook(String id);
    String returnBook(String id);
}
