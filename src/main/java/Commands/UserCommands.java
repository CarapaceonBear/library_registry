package Commands;

import Book.BookRegistryLoan;
import Book.BookRegistrySearch;
import User.UserRegistry;
import User.UserRegistryUpdate;
import User.UserUpdater;
import User.UserRegistrySearch;
import Utilities.BookListPrinter;
import Utilities.SignedInUser;
import org.json.JSONObject;

import java.util.*;

public class UserCommands implements MenuPrintOptions {

    private final UserInput userInput;
    private final BookListPrinter printer;
    private final String[] menuOptions = {"Search for a book", "Get full details of a book", "Check out a book", "Return a book"};
    private BookRegistrySearch bookSearch;
    private UserRegistrySearch userSearch;
    private BookRegistryLoan bookLoaner;
    private UserRegistryUpdate userUpdater;
    private boolean isActive = true;

    private UserRegistry temporary;

    public UserCommands(UserInput input, BookListPrinter printer, UserRegistry temporary) {
        this.userInput = input;
        this.printer = printer;
        this.temporary = temporary;
    }

    public void addSearchers(BookRegistrySearch bookSearch, UserRegistrySearch userSearch) {
        this.bookSearch = bookSearch;
        this.userSearch = userSearch;
    }

    public void addLoaner(BookRegistryLoan bookLoaner, UserRegistryUpdate userUpdater) {
        this.bookLoaner = bookLoaner;
        this.userUpdater = userUpdater;
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
//                        need to refactor so we know here if the book is on loan already
                        Optional<Map<String, String>> currentUser = userSearch.searchById(SignedInUser.getId());

                        if (currentUser.isPresent()) {
                            Map<String, String> foundUser = currentUser.get();
                            List<String> currentBookList = new ArrayList<>(Arrays.asList(foundUser.get("bookIdList").split(",")));
                            if (currentBookList.get(0).equals("")) {
                                currentBookList.set(0, checkOutId);
                            } else {
                                currentBookList.add(checkOutId);
                            }

                            foundUser.put("bookIdList", String.join(",", currentBookList));
                            userUpdater.updateUser(SignedInUser.getId(), foundUser);

                            temporary.printUsers();
                        }

                    } else {
                        userInput.printMessage("Book with given id not found\n");
                    }
                    break;
                case 4:
                    String returnId = userInput.getStringInput("Enter the id of the book to return:");
                    Optional<JSONObject> returnedBook = bookSearch.findBookById(returnId);
                    if (returnedBook.isPresent()) {
                        Optional<Map<String, String>> currentUser = userSearch.searchById(SignedInUser.getId());
                        if (currentUser.isPresent()) {
                            Map<String, String> foundUser = currentUser.get();
                            List<String> currentBookList = new ArrayList<>(Arrays.asList(foundUser.get("bookIdList").split(",")));

                            if (currentBookList.contains(returnId)) {
                                currentBookList.remove(returnId);
                                foundUser.put("bookIdList", String.join(",", currentBookList));
                                userUpdater.updateUser(SignedInUser.getId(), foundUser);

                                userInput.printMessage(bookLoaner.returnBook(returnId));
                            } else {
                                userInput.printMessage("You are not the person loaning this book\n");
                            }

                            temporary.printUsers();
                        }
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
