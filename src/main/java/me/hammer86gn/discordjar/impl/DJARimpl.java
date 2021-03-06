package me.hammer86gn.discordjar.impl;

import me.hammer86gn.discordjar.api.DJAR;
import okhttp3.OkHttpClient;

import java.util.logging.Logger;

public class DJARimpl implements DJAR {

    private final String token;
    private final int intents;

    private final Logger LOGGER;

    private final OkHttpClient httpClient = new OkHttpClient();

    public DJARimpl(String token, int intents) {
        this.token = token;
        this.intents = intents;

        this.LOGGER = Logger.getLogger("DJAR.Logger");
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public long getGatewayIntents() {
        return intents;
    }

    @Override
    public OkHttpClient getHTTPClient() {
        return httpClient;
    }
}
