package Commands;

import Book.BookRegistrySearch;
import Interfaces.MenuPrintOptions;
import Utilities.BookListPrinter;
import org.json.JSONObject;

import java.util.List;
import java.util.Optional;

public class UserCommands implements MenuPrintOptions {

    private final UserInput userInput;
    private final BookListPrinter printer;
    private final String[] menuOptions = {"Search for a book", "Get full details of a book", "Check out a book", "Return a book"};
    private BookRegistrySearch bookSearch;
    private boolean isActive = true;

    public UserCommands(UserInput input, BookListPrinter printer) {
        this.userInput = input;
        this.printer = printer;
    }

    public void addSearchers(BookRegistrySearch bookSearch) {
        this.bookSearch = bookSearch;
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
                    Optional<JSONObject> result = bookSearch.findBook(id);
                    if (result.isPresent()) {
                        printer.printFullData(result.get());
                    } else {
                        userInput.printMessage("No result for given id\n");
                    }
                    break;
                case 3:
                    userInput.printMessage("check out");
                    break;
                case 4:
                    userInput.printMessage("return");
                    break;
                default:
                    isActive = false;
            }
        }
    }
}
