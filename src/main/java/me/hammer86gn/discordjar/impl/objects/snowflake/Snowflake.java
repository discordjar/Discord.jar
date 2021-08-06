package me.hammer86gn.discordjar.impl.objects.snowflake;

/**
 * A wrapper class for Discord's Snowflake values which can be parsed into various data types
 * <br>
 * This reduces the amount of getters required to make
 */
public class Snowflake {

    private final long snowflake;

    /**
     * A constructor that takes a long and parses it into a Snowflake
     *
     * @param snowflake long value of the snowflake
     */
    public Snowflake(long snowflake) {
        this.snowflake = snowflake;
    }

    /**
     * A constructor that takes a String and parses it into a Snowflake
     *
     * @param snowflake String value of the snowflake
     */
    public Snowflake(String snowflake) {
        this(Long.parseLong(snowflake));
    }

    /**
     * Returns a long representation of the Snowflake
     *
     * @return a long representation of the object
     */
    public long toLong() {
        return snowflake;
    }

    /**
     * Returns a string representation of the object. In general, the toString method returns a string that "textually represents" this object. The result should be a concise but informative representation that is easy for a person to read. It is recommended that all subclasses override this method.
     * The toString method for class Object returns a string consisting of the name of the class of which the object is an instance, the at-sign character `@', and the unsigned hexadecimal representation of the hash code of the object. In other words, this method returns a string equal to the value of:
     *        getClass().getName() + '@' + Integer.toHexString(hashCode())
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return Long.toString(snowflake);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Snowflake) {
            return ((Snowflake) obj).snowflake == this.snowflake;
        }
        return false;
    }
}
