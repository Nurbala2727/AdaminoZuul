package be;

import be.Item;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Monster {

    private final String mName;
    private final int mDamage;
    private String mSpeech;
    private int mHealth;
    private Item mLoot;

    /**
     * Creates the monster with the given arguments
     *
     * @param name
     * @param health
     * @param damage
     */
    public Monster(String name, int health, int damage) {
        mName = name;
        mHealth = health;
        mDamage = damage;
    }

    /**
     * Gets the name of the monster
     *
     * @return
     */
    public String getName() {
        return mName;
    }

    /**
     * Gets the speach of the monster
     *
     * @return
     */
    public String getSpeach() {
        return mSpeech;
    }

    /**
     * Gets the health of the monster
     *
     * @return
     */
    public int getHealth() {
        return mHealth;
    }

    /**
     * Sets the speech of the Monster
     *
     * @param speech
     */
    public void setSpeach(String speech) {
        mSpeech = speech;
    }

    /**
     * Damage the Monster
     *
     * @param damage
     */
    public void takeDamage(int damage) {
        mHealth -= damage;
    }

    /**
     * Check if monster is dead
     *
     * @return
     */
    public boolean isMonsterDead() {
        return mHealth <= 0;
    }

    /**
     * Get the damage of the Monster
     *
     * @return
     */
    public int getDamage() {
        return mDamage;
    }

    /**
     * Gives damage to the player!
     *
     * @return
     */
    public String damagePlayer() {
        return mName + " takes a monstrous swing at you for "
                + mDamage + " points!";
    }

    /**
     * Gives the Monster loot
     *
     * @param loot
     */
    public void setLoot(Item loot) {
        mLoot = loot;
    }

    /**
     * Gets the Monster loot
     *
     * @return
     */
    public Item getLoot() {
        return mLoot;
    }

}
