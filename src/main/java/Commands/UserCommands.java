package Commands;

import Book.BookRegistryLoan;
import Book.BookRegistrySearch;
import Utilities.BookListPrinter;
import org.json.JSONObject;

import java.util.List;
import java.util.Optional;

public class UserCommands implements MenuPrintOptions {

    private final UserInput userInput;
    private final BookListPrinter printer;
    private final String[] menuOptions = {"Search for a book", "Get full details of a book", "Check out a book", "Return a book"};
    private BookRegistrySearch bookSearch;
    private BookRegistryLoan bookLoaner;
    private boolean isActive = true;

    public UserCommands(UserInput input, BookListPrinter printer) {
        this.userInput = input;
        this.printer = printer;
    }

    public void addSearchers(BookRegistrySearch bookSearch) {
        this.bookSearch = bookSearch;
    }

    public void addLoaner(BookRegistryLoan bookLoaner) {
        this.bookLoaner = bookLoaner;
    }

    private final MenuPrintOptions delegate = new MenuPrintOptions.Implementation();
    public void printOptions(UserInput userInput, String[] menuOptions) {
        delegate.printOptions(userInput, menuOptions);
    }

    public void runMenu() {
        while (isActive) {
            printOptions(userInput, menuOptions);
            int response = userInput.getIntegerInput(menuOptions.length + 1);

            switch (response) {
                case 1:
                    String searchTerm = userInput.getStringInput("Enter search term:");
                    List<JSONObject> foundBooks = bookSearch.searchBooks(searchTerm);
                    if (foundBooks.size() > 0) {
                        printer.printList(foundBooks);
                    } else {
                        userInput.printMessage("No results found\n");
                    }
                    break;
                case 2:
                    String id = userInput.getStringInput("Enter the id of the book:");
                    Optional<JSONObject> foundBook = bookSearch.findBookById(id);
                    if (foundBook.isPresent()) {
                        printer.printFullData(foundBook.get());
                    } else {
                        userInput.printMessage("No result for given id\n");
                    }
                    break;
                case 3:
                    String checkOutId = userInput.getStringInput("Enter the id of the book to check out:");
                    Optional<JSONObject> checkedOutBook = bookSearch.findBookById(checkOutId);
                    if (checkedOutBook.isPresent()) {
                        userInput.printMessage(bookLoaner.checkOutBook(checkOutId));
                    } else {
                        userInput.printMessage("Book with given id not found\n");
                    }
                    break;
                case 4:
                    String returnId = userInput.getStringInput("Enter the id of the book to return:");
                    Optional<JSONObject> returnedBook = bookSearch.findBookById(returnId);
                    if (returnedBook.isPresent()) {
                        userInput.printMessage(bookLoaner.returnBook(returnId));
                    } else {
                        userInput.printMessage("Book with given id not found\n");
                    }
                    break;
                default:
                    isActive = false;
            }
        }
    }
}
