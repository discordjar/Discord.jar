package me.hammer86gn.discordjar.api.objects.activity;

import com.google.gson.JsonObject;

public class Status {

    private final String name;
    private final ActivityType type;

    public Status(String name, ActivityType type) {
        this.name = name;
        this.type = type;
    }

    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("name",name);
        object.addProperty("type",type.getValue());

        return object;
    }

    public enum StatusType {
        ONLINE("online"),
        DO_NOT_DISTURB("dnd"),
        IDLE("idle"),
        INVISIBLE("invisible");

        private final String id;

        StatusType(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public int getType() {
            return this.ordinal();
        }
    }

    public enum ActivityType {
        GAME,
        STREAMING,
        LISTENING,
        WATCHING,
        CUSTOM,
        COMPETING;

         public int getValue() {
            return this.ordinal();
        }
    }

}
