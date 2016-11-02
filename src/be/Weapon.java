package be;

import be.Item;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Weapon extends Item {

    private final int mDamage;

    /**
     * Creates a weapon
     *
     * @param name
     * @param description
     * @param weight
     * @param damage
     */
    public Weapon(String name, String description, int weight, int damage) {
        super(name, description, weight);
        mDamage = damage;
    }

    /**
     * Gets the damage of the weapon
     *
     * @return
     */
    public int getWeaponDamage() {
        return mDamage;
    }

}
