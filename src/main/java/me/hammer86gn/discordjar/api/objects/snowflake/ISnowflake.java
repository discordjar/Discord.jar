package me.hammer86gn.discordjar.api.objects.snowflake;

import me.hammer86gn.discordjar.api.DJAR;
import me.hammer86gn.discordjar.impl.objects.snowflake.Snowflake;

/**
 * An internal interface used to show that an Object supports Snowflake IDs
 */
public interface ISnowflake {

    Snowflake getSnowflake();

    DJAR getDJAR();
}
