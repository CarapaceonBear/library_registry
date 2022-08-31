import Admin.AdminRegistry;
import Book.BookRegistry;
import Commands.SignInHandler;
import Commands.UserInput;
import User.UserRegistry;
import Utilities.CsvToJsonConverter;

import java.io.File;

public class EnvironmentSetup {

    public void setup() {

        File root = new File("/Actual Documents/NologyCourse/library_registry");
        File originalFile = new File(root, "data/books_data.csv");
        File newFile = new File(root, "data/books_data.json");

        CsvToJsonConverter converter = new CsvToJsonConverter(originalFile, newFile);
        converter.convert();

        // -----------

        UserRegistry userRegistry = new UserRegistry();
        AdminRegistry adminRegistry = new AdminRegistry();
        BookRegistry bookRegistry = new BookRegistry(newFile);

        UserInput userInput = new UserInput();
        SignInHandler signInHandler = new SignInHandler(userInput);
        signInHandler.addSearchers(userRegistry, adminRegistry);

        // -----------

        bookRegistry.compileLibrary();

    //    temporary users
        userRegistry.createUser(10000, "Bob", "password");
        userRegistry.createUser(10001, "Rick", "wordpass");
        adminRegistry.createAdmin(20000, "Beth", "admin");
        adminRegistry.createAdmin(20001, "Dave", "admin1");

        // -----------

        signInHandler.runMenu();
    }
}
