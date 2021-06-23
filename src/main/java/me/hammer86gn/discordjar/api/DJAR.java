package me.hammer86gn.discordjar.api;

import java.util.logging.Logger;

public interface DJAR {

    Logger getLogger();

    String getBotToken();

    long getGatewayIntents();

    enum StatusType {
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
}
