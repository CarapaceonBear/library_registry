package Utilities;

import Commands.UserInput;
import org.json.JSONObject;

import java.util.List;

public class BookListPrinter {

    private final UserInput userInput;
    private String loanValue;

    public BookListPrinter(UserInput input) {
        this.userInput = input;
    }

    public void printList(List<JSONObject> bookList) {
        for (JSONObject book : bookList) {
            loanValue = book.getString("IsOnLoan").equals("true") ? "yes" : "no";
            userInput.printMessage(String.format(
                    "ID: %s\n- Title: %s\n- Author: %s\n- Genre: %s\n- Currently on Loan? %s\n",
                    book.getString("Number"),
                    book.getString("Title"),
                    book.getString("Author"),
                    book.getString("Genre"),
                    loanValue));
        }
    }

    public void printFullData(JSONObject book) {
        loanValue = book.getString("IsOnLoan").equals("true") ? "yes" : "no";
        userInput.printMessage(String.format(
                "ID: %s\n- Title: %s\n- Author: %s\n- Genre: %s\n- Sub-Genre: %s\n- Publisher: %s\n- Times Loaned: %s\n- Currently on Loan? %s\n",
                book.getString("Number"),
                book.getString("Title"),
                book.getString("Author"),
                book.getString("Genre"),
                book.getString("SubGenre"),
                book.getString("Publisher"),
                book.getString("TimesLoaned"),
                loanValue));
    }
}
