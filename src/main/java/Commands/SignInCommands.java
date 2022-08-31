package Commands;

import Admin.AdminRegistry;
import Admin.AdminRegistrySearch;
import Interfaces.MenuPrintOptions;
import User.UserRegistry;
import User.UserRegistryAddUser;
import User.UserRegistrySearch;
import Utilities.SignedInUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SignInCommands implements MenuPrintOptions {

    private final UserInput userInput;
    private final UserCommands userCommands;
    private final AdminCommands adminCommands;
    private final String[] menuOptions = {"Sign in", "Register new user"};
    private UserRegistrySearch userSearch;
    private AdminRegistrySearch adminSearch;
    private UserRegistryAddUser userAdder;
    private boolean isActive = true;
    private final Map<String, String> userQuery = new HashMap<>();

    public SignInCommands(UserInput input, UserCommands userCommands, AdminCommands adminCommands) {
        this.userInput = input;
        this.userCommands = userCommands;
        this.adminCommands = adminCommands;
    }

    public void addSearchers(UserRegistrySearch userSearch, AdminRegistrySearch adminSearch) {
        this.userSearch = userSearch;
        this.adminSearch = adminSearch;
    }

    public void addCreators(UserRegistryAddUser userAdder) {
        this.userAdder = userAdder;
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
                    signIn();
                    break;
                case 2:
                    registerUser();
                    break;
                default:
                    isActive = false;
            }
        }
    }

    public void signIn() {
        getQueryInputs();
        Optional<Map<String, String>> foundAdmin = adminSearch.searchAdmins(userQuery);
        Optional<Map<String, String>> foundUser = userSearch.searchUsers(userQuery);
        if (foundAdmin.isPresent()) {
            Map<String, String> signedInUser = foundAdmin.get();
            SignedInUser.setName(signedInUser.get("name"));
            SignedInUser.setAdminPrivilege(true);
            userInput.printMessage(String.format("Signed in as %s\n", signedInUser.get("name")));
            nextMenu();
        } else if (foundUser.isPresent()) {
            Map<String, String> signedInUser = foundUser.get();
            SignedInUser.setName(signedInUser.get("name"));
            userInput.printMessage(String.format("Signed in as %s\n", signedInUser.get("name")));
            nextMenu();
        } else {
            userInput.printMessage("User not found\n");
        }
    }

    public void registerUser() {
        getQueryInputs();
        Optional<Map<String, String>> foundAdmin = adminSearch.searchAdmins(userQuery);
        Optional<Map<String, String>> foundUser = userSearch.searchUsers(userQuery);
        if (foundAdmin.isPresent() || foundUser.isPresent()) {
            userInput.printMessage("Username is already taken");
        } else {
            userAdder.addUser(userQuery);
        }
    }

    public void getQueryInputs() {
        String responseUsername = userInput.getStringInput("Enter username:");
        userQuery.put("name", responseUsername);
        String responsePassword = userInput.getStringInput("Enter password:");
        userQuery.put("password", responsePassword);
    }

    public void nextMenu() {
        isActive = false;
         if (SignedInUser.checkIfAdmin()) {
             adminCommands.runMenu();
         } else {
             userCommands.runMenu();
         }
    }

}
