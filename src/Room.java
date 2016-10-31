
import java.util.HashMap;
import java.util.Iterator;
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
    private final HashMap mExits;        // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court
     * yard".
     */
    public Room(String description) {
        mDescription = description;
        mExits = new HashMap();
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
        return "You are " + mDescription + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example "Exits: north
     * west".
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set keys = mExits.keySet();
        for (Iterator iter = keys.iterator(); iter.hasNext();) {
            returnString += " " + iter.next();
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
}
