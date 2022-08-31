package Commands;

import Admin.AdminRegistrySearch;
import Interfaces.MenuPrintOptions;
import User.UserRegistrySearch;
import Utilities.SignedInUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SignInHandler implements MenuPrintOptions {

    private final UserInput userInput;
    private final String[] menuOptions = {"Sign in", "Register new user"};
    private UserRegistrySearch userSearch;
    private AdminRegistrySearch adminSearch;

    public SignInHandler(UserInput input) {
        this.userInput = input;
    }

    public void addSearchers(UserRegistrySearch userSearch, AdminRegistrySearch adminSearch) {
        this.userSearch = userSearch;
        this.adminSearch = adminSearch;
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
        Optional<Map<String, String>> foundAdmin = adminSearch.searchAdmins(userQuery);
        Optional<Map<String, String>> foundUser = userSearch.searchUsers(userQuery);
        if (foundAdmin.isPresent()) {
            Map<String, String> signedInUser = foundAdmin.get();
            SignedInUser.setName(signedInUser.get("name"));
            SignedInUser.setAdminPriviledge(true);
            userInput.printMessage(String.format("Signed in as %s", signedInUser.get("name")));
//            run next menu
        } else if (foundUser.isPresent()) {
            Map<String, String> signedInUser = foundUser.get();
            SignedInUser.setName(signedInUser.get("name"));
            userInput.printMessage(String.format("Signed in as %s", signedInUser.get("name")));
//            run next menu
        } else {
            userInput.printMessage("User not found");
        }
    }

//    public void registerUser() {
//
//    }

}
