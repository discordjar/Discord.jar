package me.hammer86gn.discordjar.api.objects.snowflake;

import me.hammer86gn.discordjar.api.DJAR;
import me.hammer86gn.discordjar.impl.objects.snowflake.Snowflake;

/**
 * An internal interface used to show that an Object supports Snowflake IDs
 */
public interface ISnowflake {

    /**
     * Gets the snowflake of the object
     *
     * @return the snowflake of the object
     */
    Snowflake getSnowflake();

    /**
     * Gets the current DJAR instance
     *
     * @return DJAR instance
     */
    DJAR getDJAR();
}
