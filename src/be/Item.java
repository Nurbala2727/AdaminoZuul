package be;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Item {

    private final String mName;
    private final String mDescription;
    private final int mWeight;

    /**
     * Creates the item with parsed arguments
     *
     * @param name
     * @param description
     * @param weight
     */
    public Item(String name, String description, int weight) {
        mDescription = description;
        mWeight = weight;
        mName = name;
    }

    /**
     * Gets the item description
     *
     * @return
     */
    public String getItemDescription() {
        return mDescription;
    }

    /**
     * Gets the item weight
     *
     * @return
     */
    public int getItemWeight() {
        return mWeight;
    }

    /**
     * Gets the item name
     *
     * @return
     */
    public String getItemName() {
        return mName;
    }

}
