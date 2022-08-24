package Interfaces;

import Commands.UserInput;

public interface MenuPrintOptions {

    void printOptions(UserInput userInput, String[] menuOptions);

    class Implementation implements MenuPrintOptions {
        public void printOptions(UserInput userInput, String[] menuOptions) {
            for (int i = 0; i < menuOptions.length; i++) {
                userInput.printMessage(String.format("%d - %s", (i + 1), menuOptions[i]));
            }
            userInput.printMessage(String.format("%d - Exit program", menuOptions.length + 1));
        }
    }
}
