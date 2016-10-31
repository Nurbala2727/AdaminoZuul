
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
class Room {

    private final String mDescription;
    private final List<Item> mItems;
    private final HashMap mExits;        // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court
     * yard".
     */
    public Room(String description) {
        mDescription = description;
        mExits = new HashMap();
        mItems = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor) {
        mExits.put(direction, neighbor);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription() {
        return mDescription;
    }

    /**
     * Return a long description of this room, in the form: You are in the
     * kitchen. Exits: north west
     */
    public String getLongDescription() {
        return "You are " + mDescription
                + ".\nHere you see " + getAllItems()
                + "\n" + getExitString();
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
     */
    public Room getExit(String direction) {
        return (Room) mExits.get(direction);
    }

    /**
     * Adds another item to the room
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
    public List<Item> getmItems() {
        return mItems;
    }
}
