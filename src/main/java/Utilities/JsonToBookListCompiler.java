package Utilities;

import Book.Book;
import Book.BookJsonImporter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonToBookListCompiler {

    private final List<Book> library = new ArrayList<>();
    private final File jsonFile;

    public JsonToBookListCompiler(File input) {
        this.jsonFile = input;
    }

    public List<Book> compile() {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(jsonFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            JSONObject jsonData = new JSONObject(data);
            BookJsonImporter importer = new BookJsonImporter(jsonData);
            Book newBook = new Book(importer);

            library.add(newBook);
        }
        return library;
    }

}
