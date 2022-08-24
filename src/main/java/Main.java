import Admin.AdminRegistry;
import Book.BookRegistry;
import Commands.CsvToJsonConverter;
import User.UserRegistry;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;

// notes
// admin and user importer should autogenerate ids, not take as arguments
// need to implement sign in
// need to implement checking out / returning
// need to implement building reports

public class Main {
    public static void main(String[] args) {

        File root = new File("/Actual Documents/NologyCourse/library_registry");
//        String[] fileList = root.list();
//        for (String file : fileList) {
//            System.out.println(file);
//        }

        // -----------

        UserRegistry userRegistry = new UserRegistry();
        AdminRegistry adminRegistry = new AdminRegistry();

        userRegistry.createUser(10000, "Bob", "password");
        userRegistry.createUser(10001, "Rick", "wordpass");
        adminRegistry.createAdmin(20000, "Beth", "admin");
        adminRegistry.createAdmin(20001, "Dave", "admin1");

        userRegistry.listUsers();
        adminRegistry.listAdmins();

        // ------------

        File originalFile = new File(root, "data/books_data.csv");
        File newFile = new File(root, "data/books_data.json");

        CsvToJsonConverter converter = new CsvToJsonConverter(originalFile, newFile);
        converter.convert();

        // ------------

        BookRegistry bookRegistry = new BookRegistry(newFile);

        bookRegistry.compileLibrary();
        bookRegistry.listBooks();

    }
}