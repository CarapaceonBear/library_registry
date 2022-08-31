package Utilities;

import Commands.UserInput;
import org.json.JSONObject;

import java.util.List;

public class BookListPrinter {

    private final UserInput userInput;

    public BookListPrinter(UserInput input) {
        this.userInput = input;
    }

    public void printList(List<JSONObject> bookList) {
        for (JSONObject book : bookList) {
            userInput.printMessage(String.format(
                    "ID: %s\n- Title: %s\n- Author: %s\n- Genre: %s\n",
                    book.getString("Number"),
                    book.getString("Title"),
                    book.getString("Author"),
                    book.getString("Genre")));
        }
    }

    public void printFullData(JSONObject book) {
        userInput.printMessage(String.format(
                "ID: %s\n- Title: %s\n- Author: %s\n- Genre: %s\n- Sub-Genre: %s\n- Publisher: %s\n",
                book.getString("Number"),
                book.getString("Title"),
                book.getString("Author"),
                book.getString("Genre"),
                book.getString("SubGenre"),
                book.getString("Publisher")));
    }
}
