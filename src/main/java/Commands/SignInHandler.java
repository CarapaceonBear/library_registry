package Commands;

import Interfaces.MenuPrintOptions;

public class SignInHandler implements MenuPrintOptions {

    private final UserInput userInput;
    private final String[] menuOptions = {"test", "test"};

    public SignInHandler(UserInput input) {
        this.userInput = input;
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
                    System.out.println("option 1");
                    break;
                case 2:
                    System.out.println("option 2");
                    break;
                default:
                    isActive = false;
            }
        }
    }

}
