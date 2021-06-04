package me.hammer86gn.discordjar.api;

import me.hammer86gn.discordjar.api.connection.websocket.DiscordWebsocketClient;
import me.hammer86gn.discordjar.api.connection.websocket.intents.GatewayIntents;
import me.hammer86gn.discordjar.impl.DJARimpl;

import java.net.URISyntaxException;

public class DJARManager {

    private String token;
    private boolean useBotSharding;
    private int intents;

    private DiscordWebsocketClient dwsc;

    public DJARManager(String token) {
        this.token = token;
        this.useBotSharding = false;
        this.intents = 513;
    }

    public DJARManager(String token,boolean useBotSharding) {
        this.token = token;
        this.useBotSharding = useBotSharding;
        this.intents = 513;
    }

    @Deprecated
    public DJARManager(String token,boolean useBotSharding,int intents) {
        this.token = token;
        this.useBotSharding = useBotSharding;
        this.intents = intents;
    }

    public DJARManager(String token, boolean useBotSharding, GatewayIntents... intents) {
        this.token = token;
        this.useBotSharding = useBotSharding;
        this.intents = GatewayIntents.summarize(intents);
    }

    public DJARManager setUseBotSharding(boolean useBotSharding) {
        this.useBotSharding = useBotSharding;
        return this;
    }

    public DJARManager setBotToken(String token) {
        this.token = token;
        return this;
    }

    @Deprecated
    public DJARManager setGatewayIntents(int intents) {
        this.intents = intents;
        return this;
    }

    public DJARManager setGatewayIntents(GatewayIntents... intents) {
        this.intents = GatewayIntents.summarize(intents);
        return this;
    }

    public DJAR buildClient() {
        DJARimpl djar = new DJARimpl(this.token,this.intents);
        try {
            dwsc = new DiscordWebsocketClient(djar);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        dwsc.connect();

        return djar;
    }



}
