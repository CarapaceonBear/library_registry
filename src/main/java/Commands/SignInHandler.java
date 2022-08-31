package Commands;

import Interfaces.MenuPrintOptions;
import User.UserSearch;
import Utilities.SignedInUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SignInHandler implements MenuPrintOptions {

    private final UserInput userInput;
    private final String[] menuOptions = {"Sign in", "Register new user"};
    private UserSearch search;

    public SignInHandler(UserInput input) {
        this.userInput = input;
    }

    public void addSearcher(UserSearch search) {
        this.search = search;
    }

    private final MenuPrintOptions delegate = new MenuPrintOptions.Implementation();
    public void printOptions(UserInput userInput, String[] menuOptions) {
        delegate.printOptions(userInput, menuOptions);
    }

    public void runMenu() {
        boolean isActive = true;

        while (isActive) {
            printOptions(userInput, menuOptions);
            int response = userInput.getIntegerInput(menuOptions.length + 1);

            switch (response) {
                case 1:
                    signIn();
                    break;
                case 2:
                    System.out.println("option 2");
                    break;
                default:
                    isActive = false;
            }
        }
    }

    public void signIn() {
        Map<String, String> userQuery = new HashMap<>();
        String responseUsername = userInput.getStringInput("Enter username:");
        userQuery.put("name", responseUsername);
        String responsePassword = userInput.getStringInput("Enter password:");
        userQuery.put("password", responsePassword);
        Optional<Map<String, String>> foundUser = search.searchUsers(userQuery);
        if (foundUser.isPresent()) {
            Map<String, String> signedInUser = foundUser.get();
            SignedInUser.setName(signedInUser.get("name"));
//            run next menu
        } else {
            userInput.printMessage("User not found");
        }
    }

//    public void registerUser() {
//
//    }

}
