package me.hammer86gn.discordjar.api.connection.websocket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.hammer86gn.discordjar.api.DJAR;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;

public class DiscordWebsocketClient extends WebSocketClient {

    private final DJAR djar;

    public DiscordWebsocketClient(DJAR djar) throws URISyntaxException {
        super(new URI("wss://gateway.discord.gg/?v=9&encoding=json"));
        this.djar = djar;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        djar.getLogger().log(Level.INFO,"Connected to Discord's Websockets");
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
        onMessage(JsonParser.parseString(message).getAsJsonObject());
    }

    public void onMessage(JsonObject message) {

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        djar.getLogger().log(Level.WARNING,"Connection Closed\n" + "Reason: " + reason + "\nClose Code: " + code);
    }

    @Override
    public void onError(Exception ex) {
        djar.getLogger().log(Level.SEVERE,"An error occurred: " + ex.getMessage());
    }
}
