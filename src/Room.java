
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  For each existing exit, the room
 * stores a reference to the neighboring room.
 */
/**
 *
 * @author Adamino
 */
public class Room {

    private final String mDescription;
    private final boolean mLocked;
    private boolean mHasChallenge;
    private boolean mHasMonster;
    private final List<Item> mItems;
    private final List<Challenge> mChallenges;
    private final List<Monster> mMonsters;
    private final HashMap mExits;        // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court
     * yard".
     *
     * @param description
     * @param locked
     */
    public Room(String description, boolean locked) {
        mDescription = description;
        mExits = new HashMap();
        mItems = new ArrayList();
        mChallenges = new ArrayList();
        mMonsters = new ArrayList();
        mLocked = locked;

    }

    /**
     * Define an exit from this room.
     *
     * @param direction
     * @param neighbor
     */
    public void setExit(String direction, Room neighbor) {
        mExits.put(direction, neighbor);
    }

    /**
     * Adds a challenge to the room
     *
     * @param name
     * @param description
     * @param rightAnswer
     */
    public void addChallenge(String name, String description, String rightAnswer) {
        Challenge challenge = new Challenge(name, description, rightAnswer);
        mChallenges.add(challenge);
        mHasChallenge = true;
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     *
     * @return
     */
    public String getShortDescription() {
        return mDescription;
    }

    /**
     * Return a long description of this room, in the form: You are in the
     * kitchen. Exits: north west
     *
     * @return
     */
    public String getLongDescription() {
        String longDescription = "";
        longDescription += "You are in " + mDescription + ".\nHere you see ";
        if (!mItems.isEmpty()) {
            longDescription += getAllItems();
        } else {
            longDescription += "nothing...";
        }
        longDescription += "\n" + getExitString();
        return longDescription;
    }

    /**
     * Return a string describing the room's exits, for example "Exits: north
     * west".
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = mExits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     *
     * @param direction
     * @return
     */
    public Room getExit(String direction) {
        return (Room) mExits.get(direction);
    }

    /**
     * Adds another item to the room
     *
     * @param name
     * @param description
     * @param weight
     */
    public void addItem(String name, String description, int weight) {
        Item item = new Item(name, description, weight);
        mItems.add(item);
    }

    /**
     * Gets all the items in the room
     */
    private String getAllItems() {
        String allItems = "";
        for (Item mItem : mItems) {
            allItems += mItem.getItemDescription() + " (" + mItem.getItemName() + ") "
                    + " that weighs " + mItem.getItemWeight() + " Kg.\n";
        }
        return allItems;
    }

    /**
     * Gets the list of items
     *
     * @return
     */
    public List<Item> getItems() {
        return mItems;
    }

    /**
     * Tells if the room is locked
     *
     * @return
     */
    public boolean isLocked() {
        return mLocked;
    }

    /**
     * Gets the hasChallenge boolean
     *
     * @return
     */
    public boolean hasChallenge() {
        return mHasChallenge;
    }

    /**
     * Gets the challenge for the room
     *
     * @return
     */
    public String getChallenge() {
        String challenge;
        challenge = (mChallenges.get(0).getChallenge());
        return challenge;
    }

    /**
     * Check if challenge is passed
     *
     * @return
     */
    public boolean isChallengePassed() {
        boolean challengePassed;
        challengePassed = mChallenges.get(0).isChallengePassed();
        if (challengePassed) {
            mChallenges.remove(0);
            mHasChallenge = false;
        }
        return challengePassed;
    }

    /**
     * Gets the list of challenges
     *
     * @return
     */
    public List<Challenge> getChallenges() {
        return mChallenges;
    }

    /**
     * Adds a Monster to the Room
     *
     * @param name
     * @param health
     * @param damage
     */
    public void addMonster(String name, int health, int damage) {
        Monster newMonster = new Monster(name, health, damage);
        mMonsters.add(newMonster);
        mHasMonster = true;
    }

    /**
     * Gets the Monster
     *
     * @return
     */
    public List<Monster> getMonster() {
        return mMonsters;
    }

    /**
     * Add loot to the Monster in the Room in form of a weapon
     *
     * @param name
     * @param description
     * @param weight
     * @param damage
     */
    public void addMonsterLoot(String name, String description, int weight, int damage) {
        Item loot = new Weapon(name, description, weight, damage);
        mMonsters.get(0).setLoot(loot);
    }

    /**
     * Checks if the room has a monster
     *
     * @return
     */
    public boolean hasMonster() {
        return mHasMonster;
    }

}
