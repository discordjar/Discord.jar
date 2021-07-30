package me.hammer86gn.discordjar.api.objects.guild;

import com.google.gson.JsonObject;
import me.hammer86gn.discordjar.api.DJAR;
import me.hammer86gn.discordjar.api.objects.IDiscordObject;

import java.net.URL;

public interface Guild extends IDiscordObject {

    /**
     * Changes the guilds name
     *
     * @param name the new name for the guild
     */
    void changeGuildName(String name);

    /**
     * Gets the name of the guild
     *
     * @return the name of the guild
     */
    String getGuildName();

    /**
     * Gets the icon hash for the guild
     *
     * @return The icon hash
     */
    String getIconHash();

    /**
     * Gets the splash hash for the guild
     *
     * @return The splash hash
     */
    String getSplashHash();

    /**
     * Gets the icon url of the Guild
     *
     * @return The Formatted icon url
     */
    URL getIconURL();


    /**
     * Gets the splash url of the Guild
     *
     * @return The Formatted splash url
     */
    URL getSplashURL();

    /**
     * Get the Owner of the guild's id
     *
     * @return the snowflake id of the owner
     */
    long getOwnerID();

    /**
     * Get the id of the guild
     *
     * @return String id of guild
     */
    @Override
    String getIDAsString();

    /**
     * Get the id of the guild
     *
     * @return long id of the guild
     */
    @Override
    long getID();

    /**
     * Gets the guild as a JSON
     *
     * @return The guild as a json object
     */
    JsonObject getGuildAsJson();



    /**
     * Gets your DJAR instance
     *
     * @return the DJAR instance inuse
     * @see {@link DJAR}
     */
    @Override
    DJAR getDJAR();

}
