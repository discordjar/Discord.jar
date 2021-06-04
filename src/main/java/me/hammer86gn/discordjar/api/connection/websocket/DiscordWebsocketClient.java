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
    private long heartbeat_int;

    private long seqNum;
    private boolean hasSeq = false;

    private String session_id;

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
        int opCode = message.get("op").getAsInt();
        if (opCode == 10) {
            heartbeat(message);
            identify(message);
        }
        if (opCode == 0) {
            if (message.get("t").getAsString().equals("READY")) {
                JsonObject data = message.get("d").getAsJsonObject();
                session_id = data.get("session_id").getAsString();
            }
        }
        if (opCode != 11) {
            if (!message.get("s").isJsonNull()) {
                seqNum = message.get("s").getAsLong();
                hasSeq = true;
            }
        }


    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        djar.getLogger().log(Level.WARNING,"Connection Closed\n" + "Reason: " + reason + "\nClose Code: " + code);
    }

    @Override
    public void onError(Exception ex) {
        djar.getLogger().log(Level.SEVERE,"An error occurred: " + ex.getMessage());
        ex.printStackTrace();
    }

    public void identify(JsonObject message) {
        JsonObject payload = new JsonObject();
        payload.addProperty("op",2);


        JsonObject data = new JsonObject();
        data.addProperty("token",djar.getBotToken());
        data.addProperty("intents",djar.getGatewayIntents());

        JsonObject properties = new JsonObject();
        properties.addProperty("$os","linux");
        properties.addProperty("$browser","djar");
        properties.addProperty("$device","djar");

        data.add("properties",properties);


        payload.add("d",data);

        send(payload.toString());
    }

    private void heartbeat(JsonObject message) {
        heartbeat_int = message.get("d").getAsJsonObject().get("heartbeat_interval").getAsLong();
        new Thread(() -> {
            while(!getConnection().isClosed()) {
                if (!hasSeq) {
                    Payload heartbeat = new Payload(1).addData((Long) null);

                    send(heartbeat.encode());

                    try {
                        Thread.sleep(heartbeat_int);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    Payload heartbeat = new Payload(1).addData(seqNum);

                    send(heartbeat.encode());

                    try {
                        Thread.sleep(heartbeat_int);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"CONNECTION_KEEPALIVE").start();
    }
}
