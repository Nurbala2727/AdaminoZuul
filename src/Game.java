
/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 */
/**
 *
 * @author Adamino
 */
class Game {

    private final Parser mParser;
    private Room mCurrentRoom;
    private final String GAME_TITLE = "Adamino's magical console adventure!";
    private final String QUIT_MESSAGE = "Thank you for playing... Looser";
    private static final String UNKNOWN = "I don't know what you mean...";
    private static final String NORTH = "north";
    private static final String SOUTH = "south";
    private static final String EAST = "east";
    private static final String WEST = "west";

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        mParser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room entrance, castleMainHall, wineCellar, dungeon, tower;

        // create the rooms
        entrance = new Room("the main entrance to the castle");
        castleMainHall = new Room("in a castle main hall");
        wineCellar = new Room("in the wine cellar");
        dungeon = new Room("in a dungeon");
        tower = new Room("in stairs to the tower");

        // initialise room exits
        entrance.setExit(EAST, castleMainHall);
        entrance.setExit(SOUTH, dungeon);
        entrance.setExit(WEST, wineCellar);

        castleMainHall.setExit(WEST, entrance);

        wineCellar.setExit(EAST, entrance);

        dungeon.setExit(NORTH, entrance);
        dungeon.setExit(EAST, tower);

        tower.setExit(WEST, dungeon);

        mCurrentRoom = entrance;  // start game at the entrance
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (!finished) {
            Command command = mParser.getCommand();
            finished = processCommand(command);
        }
        System.out.println(QUIT_MESSAGE);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to " + GAME_TITLE);
        System.out.println(GAME_TITLE + " is a new, incredibly exciting discovery game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(mCurrentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command. If this command
     * ends the game, true is returned, otherwise false is returned.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println(UNKNOWN);
            return false;
        }

        String commandWord = command.getCommandWord();
        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
            default:
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        mParser.showCommands();
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = mCurrentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            mCurrentRoom = nextRoom;
            System.out.println(mCurrentRoom.getLongDescription());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game. Return true, if this command quits the game, false
     * otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
}
