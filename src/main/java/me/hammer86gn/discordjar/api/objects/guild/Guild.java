package me.hammer86gn.discordjar.api.objects.guild;

import com.google.gson.JsonObject;
import me.hammer86gn.discordjar.api.DJAR;

import java.net.URL;

public interface Guild {

    void changeGuildName(String name);

    String getGuildName();

    String getIconHash();

    String getSplashHash();

    URL getIconURL();

    URL getSplashURL();

    long getOwnerID();

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

    JsonObject getGuildAsJson();



    /**
     * Gets your DJAR instance
     *
     * @return the DJAR instance inuse
     * @see {@link DJAR}
     */
    DJAR getDJAR();

}
