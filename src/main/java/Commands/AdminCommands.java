package Commands;

public class AdminCommands implements MenuPrintOptions {

    private final UserInput userInput;
    private final String[] menuOptions = {"", ""};
    private boolean isActive = true;

    public AdminCommands(UserInput input) {
        this.userInput = input;
    }

    private final MenuPrintOptions delegate = new MenuPrintOptions.Implementation();
    public void printOptions(UserInput userInput, String[] menuOptions) {
        delegate.printOptions(userInput, menuOptions);
    }

    public void runMenu() {
        System.out.println("admin menu");
    }
}
