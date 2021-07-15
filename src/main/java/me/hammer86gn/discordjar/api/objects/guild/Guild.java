package me.hammer86gn.discordjar.api.objects.guild;

import me.hammer86gn.discordjar.api.DJAR;

public interface Guild {

    /**
     * Get the id of the guild
     *
     * @return String id of guild
     */
    String getIDString();

    /**
     * Get the id of the guild
     *
     * @return long id of the guild
     */
    long getID();

    /**
     * Gets your DJAR instance
     *
     * @return the DJAR instance inuse
     * @see {@link DJAR}
     */
    DJAR getDJAR();

}
