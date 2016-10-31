
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Player {

    private final String mNAME;
    private final int mMAX_LOAD;

    private Room mCurrentRoom;
    private final Stack mPREVIOUS_ROOMS;

    /**
     * Creates the Player
     *
     * @param name
     * @param maxLoad
     */
    public Player(String name, int maxLoad) {
        this.mNAME = name;
        this.mMAX_LOAD = maxLoad;
        mPREVIOUS_ROOMS = new Stack();
    }

    /**
     * Gets the players name
     *
     * @return
     */
    public String getPlayerName() {
        return mNAME;
    }

    /**
     * Gets the players max load
     *
     * @return
     */
    public int getPlayerMaxLoad() {
        return mMAX_LOAD;
    }

    /**
     * Gets the current Room of the Player
     *
     * @return
     */
    public Room getCurrentRoom() {
        return mCurrentRoom;
    }

    public Stack getPreviousRooms() {
        return mPREVIOUS_ROOMS;
    }

    /**
     * Sets the current room of the player
     *
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        mCurrentRoom = currentRoom;
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    public void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            getPreviousRooms().add(getCurrentRoom());
            setCurrentRoom(nextRoom);
            System.out.println(getCurrentRoom().getLongDescription());
        }
    }

    /**
     * Player picks up the item
     *
     * @param itemName
     */
    public void takeItem(String itemName) {
        for (Item mItem : getCurrentRoom().getmItems()) {
            if (mItem.getItemName().equals(itemName)) {
                System.out.println("Took item " + itemName);
            }
        }
    }

    /**
     * Takes a look around the room and reports back the exits!
     */
    public void look() {
        System.out.println(getCurrentRoom().getLongDescription());
    }

    /**
     * Sends the player back to the previous room
     */
    public void goBack() {
        if (!getPreviousRooms().isEmpty()) {
            setCurrentRoom((Room) getPreviousRooms().lastElement());
            getPreviousRooms().remove(getPreviousRooms().lastElement());
            System.out.println(getCurrentRoom().getLongDescription());
        } else {
            System.out.println("You're at the beginning!");
        }
    }

}
