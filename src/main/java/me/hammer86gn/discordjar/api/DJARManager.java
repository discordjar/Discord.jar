package me.hammer86gn.discordjar.api;

import me.hammer86gn.discordjar.api.connection.websocket.DiscordWebsocketClient;
import me.hammer86gn.discordjar.api.connection.websocket.intents.GatewayIntents;
import me.hammer86gn.discordjar.impl.DJARimpl;

import java.net.URISyntaxException;

public class DJARManager {

    private String token;
    private int intents;

    private DiscordWebsocketClient dwsc;

    /**
     * Creates a new DJARManager with token
     *
     * @param token The bot's token
     */
    public DJARManager(String token) {
        this.token = token;
        this.intents = 513;
    }


    @Deprecated
    public DJARManager(String token,int intents) {
        this.token = token;
        this.intents = intents;
    }

    /**
     * Creates a new DJARManager with token and Gateway intents
     *
     * @param token The bot's token
     * @param intents the bot's gateway intents
     */
    public DJARManager(String token, GatewayIntents... intents) {
        this.token = token;
        this.intents = GatewayIntents.summarize(intents);
    }

    @Deprecated
    public DJARManager setUseBotSharding(boolean useBotSharding) {
        return this;
    }

    /**
     * Sets the token for the builder
     *
     * @param token the token for the bot
     * @return {@link DJARManager} The current DJARManager with the updated token
     */
    public DJARManager setBotToken(String token) {
        this.token = token;
        return this;
    }

    @Deprecated
    public DJARManager setGatewayIntents(int intents) {
        this.intents = intents;
        return this;
    }

    /**
     * Sets the intents for the <a href="https://discord.com/developers/docs/topics/gateway#list-of-intents">gateway intents</a>
     *
     * @param intents the intents you want to use
     * @return {@link DJARManager} The current DJARManager with the updated intents
     */
    public DJARManager setGatewayIntents(GatewayIntents... intents) {
        this.intents = GatewayIntents.summarize(intents);
        return this;
    }

    /**
     * Builds the client and connects to Discord
     *
     * @return {@link DJAR} the main DJAR class (this should be stored)
     */
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

    public DJAR buildShardedClient() {
        DJARimpl djarimpl = new DJARimpl(this.token,this.intents);


        return djarimpl;
    }



}
