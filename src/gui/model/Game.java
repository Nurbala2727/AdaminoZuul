package gui.model;

import be.Player;
import be.Room;
import bll.Command;
import bll.CommandWord;
import bll.Parser;
import gui.view.OutputManager;

/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can walk around some
 * scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * To play this game, create an instance of this class and call the "play"
 * method.
 *
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 */
/**
 *
 * @author Adamino
 */
public class Game {

    private final Parser mParser;
    private final Player player;
    private static boolean isGameOver;
    private static boolean isGameWon;
    private final String GAME_TITLE = "Adamino's magical console adventure!";
    private final String QUIT_MESSAGE = "Thank you for playing";
    private static final String UNKNOWN = "I don't know what you mean...";
    private static final String LAST_BOSS = "Dracula";
    private static final String FINAL_WEAPON = "ancientSwordOfDracula";
    private static final String NORTH = "north";
    private static final String SOUTH = "south";
    private static final String EAST = "east";
    private static final String WEST = "west";

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        isGameOver = false;
        isGameWon = false;
        player = new Player("Adam", 25, 10);
        createRooms();
        mParser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room casleEntrance, castleMainHall, wineCellar, dungeon, tower, tortureRoom, creepyBedroom, courtRoom, sewars, attic, kitchen, laboratory, ballRoom, secretPassage, lavatory, boat;

        // create the rooms
        casleEntrance = new Room("the main entrance to the castle", false);
        castleMainHall = new Room("a castle main hall", false);
        wineCellar = new Room("the wine cellar", false);
        dungeon = new Room("a dungeon", false);
        tower = new Room("the beginning of stairs to the tower", true);
        tortureRoom = new Room("a dark torture room full of instruments used in the popular movie 'Fifty Shades of Grey'", false);
        creepyBedroom = new Room("a creepy bedroom riveting of lust and desire, but also despair...", false);
        courtRoom = new Room("a courtroom. The place of judgement. Truth will out!", false);
        sewars = new Room("a dank sewar filled with moist and a flowing river", false);
        attic = new Room("a mysterious attic with weird sound coming from the back of the room", false);
        kitchen = new Room("a huge kithen which smells awefully from... blood!?", false);
        laboratory = new Room("a scary laboratory containing a huge table!", false);
        ballRoom = new Room("a spectacular vast ballroom with a reminiscens of people dancing from another age", false);
        secretPassage = new Room("a hidden passage, which is quite narrow", true);
        lavatory = new Room("a very old lavatory for women. When you enter the room you hear a sobbing cry from a long forgotte ghost named 'Moaning Myrtle'", false);
        boat = new Room("a little poddle of water and some very old marks that looks like bloodstains...", false);

        // initialise room exits
        casleEntrance.setExit(NORTH, castleMainHall);

        castleMainHall.setExit(WEST, wineCellar);
        castleMainHall.setExit(NORTH, ballRoom);
        castleMainHall.setExit(SOUTH, casleEntrance);

        wineCellar.setExit(WEST, creepyBedroom);
        wineCellar.setExit(NORTH, dungeon);
        wineCellar.setExit(EAST, castleMainHall);

        creepyBedroom.setExit(NORTH, tortureRoom);
        creepyBedroom.setExit(EAST, wineCellar);

        dungeon.setExit(WEST, tortureRoom);
        dungeon.setExit(NORTH, sewars);
        dungeon.setExit(SOUTH, wineCellar);

        tortureRoom.setExit(NORTH, laboratory);
        tortureRoom.setExit(SOUTH, creepyBedroom);

        laboratory.setExit(SOUTH, tortureRoom);

        sewars.setExit(SOUTH, dungeon);
        sewars.setExit(NORTH, dungeon);

        ballRoom.setExit(EAST, kitchen);
        ballRoom.setExit(NORTH, courtRoom);
        ballRoom.setExit(SOUTH, castleMainHall);

        kitchen.setExit(NORTH, lavatory);
        kitchen.setExit(WEST, ballRoom);

        lavatory.setExit(WEST, courtRoom);
        lavatory.setExit(SOUTH, kitchen);

        courtRoom.setExit(EAST, lavatory);
        courtRoom.setExit(NORTH, secretPassage);
        courtRoom.setExit(SOUTH, ballRoom);

        secretPassage.setExit(NORTH, tower);
        secretPassage.setExit(SOUTH, courtRoom);

        tower.setExit(NORTH, attic);
        tower.setExit(SOUTH, secretPassage);

        attic.setExit(SOUTH, tower);

        // Add items to the rooms
        // Add the needed items to finish the game
        casleEntrance.addItem("vase1", "a beautiful vase", 2);
        casleEntrance.addItem("vase2", "a small very ugly vase", 1);

        castleMainHall.addItem("carpet1", "a huge carpet on the floor with a dragon on", 3);
        castleMainHall.addItem("vase3", "a huge blue vase with red dragon flames on", 2);

        wineCellar.addItem("wineRack", "a winerack with 200 different wines in", 150);
        wineCellar.addItem("secretKey", "a mysterious key that might grant you access to a certain location", 0);

        creepyBedroom.addItem("creepyBed", "a big and soft bed. On the bed there are old sheets with some mysterious spots...", 60);
        creepyBedroom.addItem("candlelight", "an old silver candlelight which, in former times, held many candlelights", 5);

        dungeon.addItem("skeleton", "a skeleton hanging from the ceiling", 1);

        tortureRoom.addItem("whip", "an old whip which may have been used for spectacular things", 1);

        laboratory.addItem("vessel", "a very old dusty vessel filled with a strange green substance", 0);

        courtRoom.addItem("gavel", "a very old gavel from a dead judge", 1);

        sewars.addItem("oars", "a pair of old dusty oars", 1);

        ballRoom.addItem("gobbelin", "a huge gobbelin hanging on the wall", 25);

        kitchen.addItem("pileOfBones", "a huge pile of bones is in the kitchen zink", 2);

        tower.addItem("chandelier", "an Old dusty chandelier", 50);

        // Add challenges to challengeRooms
        courtRoom.addChallenge("Justice or die", "For what was Jesus condemned to death on the cross?", "'blasphemy' (claiming to be god)");
        courtRoom.getChallenges().get(0).addFakeAnswer("Saying the king was wrong");
        courtRoom.getChallenges().get(0).addFakeAnswer("For killing a lamb");
        courtRoom.getChallenges().get(0).addFakeAnswer("For doing carpentry on a Sunday");

        laboratory.addChallenge("Master of the Monster", "Who is the author behind the well known book 'Frankenstein'", "Mary Shelley");
        laboratory.getChallenges().get(0).addFakeAnswer("Emily Bronte");
        laboratory.getChallenges().get(0).addFakeAnswer("William Blake");

        lavatory.addChallenge("A cry of despair", "What killed the character 'Moaning Myrtle' in the Harry Potter series?", "The Bassilisk");
        lavatory.getChallenges().get(0).addFakeAnswer("Voldemort");
        lavatory.getChallenges().get(0).addFakeAnswer("An 'Acromantula'");

        // Add Monster to a Room
        laboratory.addMonster("The Monster of Frankenstein", 10, 3);
        laboratory.getMonster().get(0).setSpeach("I am the long forgotten monster created by Frankenstein! Now you will die!");
        laboratory.addMonsterLoot("ancientSwordOfDracula", "a sword that was forged in another time and has a touch of destiny", 3, 50);

        attic.addMonster("Dracula", 100, 5);
        attic.getMonster().get(0).setSpeach("So you finally arrived... You will never get the princess!!!");

        // start game at the entrance
        player.setCurrentRoom(casleEntrance);
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
            if (isGameOver || isGameWon) {
                finished = true;
            }
        }
        OutputManager.outputString(QUIT_MESSAGE);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        OutputManager.outputString("\nWelcome to " + GAME_TITLE);
        OutputManager.outputString(GAME_TITLE + " is a new, incredibly exciting discovery game.");
        OutputManager.outputString("\nType 'help' if you need help.");
        OutputManager.outputString(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command. If this command
     * ends the game, true is returned, otherwise false is returned.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            OutputManager.outputString(UNKNOWN);
            return false;
        }

        CommandWord commandWord = command.getCommandWord();
        String secondWord = command.getSecondWord();

        switch (commandWord) {
            case UNKNOWN:
                OutputManager.outputString(UNKNOWN);
                break;
            case HELP:
                printHelp();
                break;
            case GO:
                player.goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case LOOK:
                player.look();
                break;
            case BACK:
                player.goBack();
                break;
            case TAKE:
                player.takeItem(secondWord);
                break;
            case DROP:
                player.dropItem(secondWord);
                break;
            case ITEMS:
                player.inventoryStatus();
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
        OutputManager.outputString("You are lost. You are alone. You wander");
        OutputManager.outputString("around at the castle.");
        OutputManager.outputString("Your command words are:");
        OutputManager.outputString(mParser.showCommands());
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game. Return true, if this command quits the game, false
     * otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            OutputManager.outputString("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Check if game is over
     *
     * @return
     */
    private boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Make game over if player dies
     */
    public static void gameOver() {
        OutputManager.outputString("\nBetter luck next time!");
        isGameOver = true;
    }

    /**
     * Win the game by freeing the princess!
     */
    public static void win() {
        OutputManager.outputString("\nCongratulations! You won the game!!!");
        isGameWon = true;
    }

    /**
     * Gets the name of the last boss
     *
     * @return
     */
    public static String getLAST_BOSS() {
        return LAST_BOSS;
    }

    /**
     * Gets the name of the final weapon
     *
     * @return
     */
    public static String getFINAL_WEAPON() {
        return FINAL_WEAPON;
    }
}
