
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Player {

    private final String mNAME;
    private final int mMAX_LOAD;
    private boolean mHaveKey;
    private final Stack mPREVIOUS_ROOMS;
    private final List<Item> takenItems;

    private Room mCurrentRoom;
    private int mLoadLeft;

    /**
     * Creates the Player
     *
     * @param name
     * @param maxLoad
     */
    public Player(String name, int maxLoad) {
        mNAME = name;
        mMAX_LOAD = maxLoad;
        mLoadLeft = mMAX_LOAD;
        mPREVIOUS_ROOMS = new Stack();
        takenItems = new ArrayList<>();
        mHaveKey = false;

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
     *
     * @param command
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
            enterNextRoom(nextRoom);
        }
    }

    /**
     * If room isn't locked enter the next room If the room is locked, but we
     * have the key we can enter!
     *
     * @param nextRoom
     */
    private void enterNextRoom(Room nextRoom) {
        //Check if the room is locked and if so, if we have the secret key
        if (nextRoom.isLocked() && !mHaveKey) {
            System.out.println("You do not have the secret key to enter " + nextRoom.getShortDescription() + "!");
        } else {
            //Room is accessable and we will remember the last room we visited before entering
            getPreviousRooms().add(getCurrentRoom());
            setCurrentRoom(nextRoom);
            System.out.println(getCurrentRoom().getLongDescription());
        }
    }

    /**
     * Player picks up the item if it is not too heavy
     *
     * @param itemName
     */
    public void takeItem(String itemName) {
        for (Item mItem : getCurrentRoom().getmItems()) {
            if (mItem.getItemName().equals(itemName)) {
                if (mLoadLeft >= mItem.getItemWeight()) {
                    addItemToInventory(itemName, mItem);
                    checkForSecretKey(mItem);
                    inventoryStatus();
                    break;
                } else {
                    System.out.println("Sorry you don't have enough space to take " + mItem.getItemName());
                }
            }
        }
    }

    /**
     * Adds current item to the players inventory and reduces the total weigh
     * the player can hold
     *
     * @param itemName
     * @param mItem
     */
    private void addItemToInventory(String itemName, Item mItem) {
        System.out.println("Took item " + itemName);
        takenItems.add(mItem);
        mLoadLeft -= mItem.getItemWeight();
        getCurrentRoom().getmItems().remove(mItem);
    }

    /**
     * Checks if the player picked up the secret key!
     *
     * @param mItem
     */
    private void checkForSecretKey(Item mItem) {
        if (mItem.getItemName().equals("secretKey")) {
            System.out.println("You found the secret key and should now look for the locked door!");
            setSecretKey();
        }
    }

    /**
     * Drops the item to the mCurrentRoom
     *
     * @param itemName
     */
    public void dropItem(String itemName) {
        for (Item mItem : takenItems) {
            if (mItem.getItemName().equals(itemName)) {
                System.out.println("Dropped item " + itemName);
                takenItems.remove(mItem);
                mLoadLeft += mItem.getItemWeight();
                getCurrentRoom().getmItems().add(mItem);
                inventoryStatus();
                break;
            }
        }
    }

    /**
     * Prints information about the players inventory status
     */
    public void inventoryStatus() {
        if (takenItems.isEmpty()) {
            System.out.println("You don't currently hold any items");
        } else {
            System.out.println("You're currently holding " + getTakenItemsAsString());
        }
        System.out.println("Of your maximum capacity of " + mMAX_LOAD + " Kg" + " you have " + mLoadLeft + " Kg left!");
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

    /**
     * Gets a String of the items the player currently holds
     */
    private String getTakenItemsAsString() {
        String allItems = "";
        for (Item item : takenItems) {
            allItems += item.getItemName() + " ";
        }
        return allItems;
    }

    /**
     * Updates the player to have the secret key!
     */
    private void setSecretKey() {
        mHaveKey = true;
    }

}
