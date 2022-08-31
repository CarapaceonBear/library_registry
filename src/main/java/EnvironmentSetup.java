import Admin.AdminRegistry;
import Book.BookRegistry;
import Commands.AdminCommands;
import Commands.SignInCommands;
import Commands.UserCommands;
import Commands.UserInput;
import User.UserRegistry;
import Utilities.BookListPrinter;
import Utilities.CsvToJsonConverter;
import Utilities.IdGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EnvironmentSetup {

    public void setup() {

        File root = new File("/Actual Documents/NologyCourse/library_registry");
        File originalFile = new File(root, "data/books_data.csv");
        File newFile = new File(root, "data/books_data.json");
        File originalFileModified = new File(root, "data/books_data_modified.csv");
        if (! newFile.exists()) {
            CsvToJsonConverter converter = new CsvToJsonConverter(originalFile, newFile, originalFileModified);
            converter.convert();
        }

        // -----------

        IdGenerator idGenerator = new IdGenerator();
        UserRegistry userRegistry = new UserRegistry(idGenerator);
        AdminRegistry adminRegistry = new AdminRegistry(idGenerator);
        BookRegistry bookRegistry = new BookRegistry(newFile);

        UserInput userInput = new UserInput();
        BookListPrinter bookListPrinter = new BookListPrinter(userInput);
        UserCommands userCommands = new UserCommands(userInput, bookListPrinter);
        userCommands.addSearchers(bookRegistry);
        AdminCommands adminCommands = new AdminCommands(userInput);
        SignInCommands signInCommands = new SignInCommands(userInput, userCommands, adminCommands);
        signInCommands.addSearchers(userRegistry, adminRegistry);
        signInCommands.addCreators(userRegistry);

        // -----------

        bookRegistry.compileLibrary();

    //    temporary users
        Map<String, String> bob = new HashMap<>();
        bob.put("name", "Bob");
        bob.put("password", "password");
        userRegistry.addUser(bob);

        Map<String, String> rick = new HashMap<>();
        rick.put("name", "Rick");
        rick.put("password", "wordpass");
        userRegistry.addUser(rick);

        Map<String, String> beth = new HashMap<>();
        beth.put("name", "Beth");
        beth.put("password", "admin");
        adminRegistry.addAdmin(beth);

        Map<String, String> dave = new HashMap<>();
        dave.put("name", "Dave");
        dave.put("password", "admin1");
        adminRegistry.addAdmin(dave);

        // -----------

        signInCommands.runMenu();
    }
}
