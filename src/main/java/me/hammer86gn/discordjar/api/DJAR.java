package me.hammer86gn.discordjar.api;

import okhttp3.OkHttpClient;

import java.util.logging.Logger;

public interface DJAR {

    Logger getLogger();

    String getBotToken();

    long getGatewayIntents();

    OkHttpClient getHTTPClient();
}
