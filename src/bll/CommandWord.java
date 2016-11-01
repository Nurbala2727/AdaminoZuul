package bll;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public enum CommandWord {
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), LOOK("look"), BACK("back"), TAKE("take"), DROP("drop"), ITEMS("items");

    // The command string.
    private final String commandString;

    /**
     * Initialise with the corresponding command string.
     *
     * @param commandString The command string.
     */
    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    /**
     * @return The command word as a string.
     */
    @Override
    public String toString() {
        return commandString;
    }
}
