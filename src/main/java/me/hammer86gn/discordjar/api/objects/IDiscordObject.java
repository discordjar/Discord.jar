package me.hammer86gn.discordjar.api.objects;

import me.hammer86gn.discordjar.api.DJAR;

public interface IDiscordObject {

    /**
     * Get the id of the object
     *
     * @return long id of the object
     */
    long getID();

    /**
     * Get the id of the object
     *
     * @return String id of object
     */
    String getIDAsString();

    /**
     * Gets your DJAR instance
     *
     * @return the DJAR instance inuse
     * @see {@link DJAR}
     */
    DJAR getDJAR();

}
