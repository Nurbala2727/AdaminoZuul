/*
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 */

/**
 *
 * @author Adamino
 */
class CommandWords {

    // a constant array that holds all valid command words
    private static final String VALID_COMMANDS[] = {
        "go", "quit", "help"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. Return true if it
     * is, false if it isn't.
     */
    public boolean isCommand(String aString) {
        for (String VALID_COMMANDS1 : VALID_COMMANDS) {
            if (VALID_COMMANDS1.equals(aString)) {
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /*
     * Print all valid commands to System.out.
     */
    public void showAll() {
        for (String VALID_COMMANDS1 : VALID_COMMANDS) {
            System.out.print(VALID_COMMANDS1 + "  ");
        }
        System.out.println();
    }
}
