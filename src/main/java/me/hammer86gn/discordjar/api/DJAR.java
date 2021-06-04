package me.hammer86gn.discordjar.api;

import java.util.logging.Logger;

public interface DJAR {

    Logger getLogger();

    String getBotToken();

    long getGatewayIntents();

}
