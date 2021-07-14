package me.hammer86gn.discordjar.api.connection.websocket;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.hammer86gn.discordjar.api.DJAR;
import me.hammer86gn.discordjar.api.connection.websocket.exception.RateLimitOverflowException;
import me.hammer86gn.discordjar.api.connection.websocket.payload.PayloadBuilder;
import me.hammer86gn.discordjar.api.objects.activity.Activity;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.logging.Level;

public class DiscordWebsocketClient extends WebSocketClient {

    private final DJAR djar;
    private long heartbeat_int;

    private long seqNum;
    private boolean hasSeq = false;

    private String session_id;

    private boolean resume = false;

    private long requestsSent = 0;

    private Activity activity;

    public DiscordWebsocketClient(DJAR djar) throws URISyntaxException {
        super(new URI("wss://gateway.discord.gg/?v=9&encoding=json"));
        this.djar = djar;
    }

    public DiscordWebsocketClient(DJAR djar, Activity activity) throws URISyntaxException {
        super(new URI("wss://gateway.discord.gg/?v=9&encoding=json"));
        this.djar = djar;
        this.activity = activity;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        if (!resume) {
            djar.getLogger().log(Level.INFO, "Connected to Discord's Websockets");
        }else {
            resume(djar.getBotToken(), session_id,seqNum);
        }

        new Thread(() -> {
            while(!this.isClosed()) {
                requestsSent = 0;
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"RATELIMIT-WEBSOCKET").start();

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
            if (activity != null) {
                updatePresence(activity.getActivity().toJson(), activity.getStatus().getId(),false);
            }
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

        if (remote) {
            resume = true;
            this.connect();
        }

    }

    @Override
    public void onError(Exception ex) {
        djar.getLogger().log(Level.SEVERE,"An error occurred: " + ex.getMessage());
        ex.printStackTrace();
    }

    public void identify(JsonObject message) {
//        JsonObject payload = new JsonObject();
//        payload.addProperty("op",2);

        PayloadBuilder payload = new PayloadBuilder(2);

        JsonObject data = new JsonObject();
        data.addProperty("token",djar.getBotToken());
        data.addProperty("intents",djar.getGatewayIntents());

        JsonObject properties = new JsonObject();
        properties.addProperty("$os","linux");
        properties.addProperty("$browser","djar");
        properties.addProperty("$device","djar");

        data.add("properties",properties);


        payload.addData(data);

        try {
            sendPayload(payload.build());
        } catch (RateLimitOverflowException e) {
            e.printStackTrace();
        }
    }

    public void sendPayload(String payload) throws RateLimitOverflowException {
        if (requestsSent >= 120) {
            throw new RateLimitOverflowException("You may only send 120 requests per 60 seconds");
        } else {
            this.send(payload);
        }
    }

    private void heartbeat(JsonObject message) {
        heartbeat_int = message.get("d").getAsJsonObject().get("heartbeat_interval").getAsLong();
        new Thread(() -> {
            while(!getConnection().isClosed()) {
                if (!hasSeq) {
//                    Payload heartbeat = new Payload(1).addData((Long) null);

                    PayloadBuilder heartbeat = new PayloadBuilder(1);

                    try {
                        sendPayload(heartbeat.build((Long) null));
                    } catch (RateLimitOverflowException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(heartbeat_int);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                   // Payload heartbeat = new Payload(1).addData(seqNum);

                    PayloadBuilder heartbeatPayload = new PayloadBuilder(1);

                    try {
                        sendPayload(heartbeatPayload.build(seqNum));
                    } catch (RateLimitOverflowException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(heartbeat_int);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"CONNECTION_KEEPALIVE").start();
    }

    private void resume(String token,String session_id,long seqNum) {
        JsonObject payload = new JsonObject();
        payload.addProperty("op",6);

        JsonObject data = new JsonObject();
        data.addProperty("token",token);
        data.addProperty("session_id",session_id);
        data.addProperty("seq",seqNum);

        payload.add("d",data);

        try {
            this.sendPayload(payload.toString());
        } catch (RateLimitOverflowException e) {
            e.printStackTrace();
        }
    }

    private void requestGuildMembers(long guild_id, String query, int limit, boolean presences, long user_id[], String nonce) {
        JsonObject details = new JsonObject();
        details.addProperty("guild_id",guild_id);
        if (!(query == null)) {
            details.addProperty("query",query);
            details.addProperty("limit",limit);
        }
        if (presences) {
            details.addProperty("presences",presences);
        }
        if (user_id != null && query == null) {
            details.addProperty("user_ids", Arrays.toString(user_id));
        }
        if (nonce != null) {
            details.addProperty("nonce",nonce);
        }

        PayloadBuilder payloadBuilder = new PayloadBuilder(8).addData(details);

        try {
            this.sendPayload(payloadBuilder.build());
        } catch (RateLimitOverflowException e) {
            e.printStackTrace();
        }
    }

    private void updateVoiceStatus(long guild_id,long channel_id, boolean self_mute, boolean self_deaf) {
        JsonObject data = new JsonObject();
        data.addProperty("guild_id",guild_id);
        if (channel_id == 0) {
            JsonNull jsonNull = JsonNull.INSTANCE;
            data.add("channel_id",jsonNull);
        }else {
            data.addProperty("channel_id",channel_id);
        }
        data.addProperty("self_mute",self_mute);
        data.addProperty("self_deaf",self_deaf);

        PayloadBuilder payloadBuilder = new PayloadBuilder(4).addData(data);

        try {
            this.sendPayload(payloadBuilder.build());
        } catch (RateLimitOverflowException e) {
            e.printStackTrace();
        }
    }

    public void updatePresence(long since, JsonObject activities, String status, boolean afk) {
        JsonArray array = new JsonArray();
        array.add(activities);

        JsonObject object = new JsonObject();
        object.addProperty("since",since);
        object.add("activities",array);
        object.addProperty("status",status);
        object.addProperty("afk",afk);


        PayloadBuilder builder = new PayloadBuilder(3)
                .addData(object);

        try {
            this.sendPayload(builder.build());
        } catch (RateLimitOverflowException e) {
            e.printStackTrace();
        }
    }

    public void updatePresence(JsonObject activities, String status, boolean afk) {
        JsonArray array = new JsonArray();
        array.add(activities);

        JsonObject object = new JsonObject();
        object.add("since",JsonNull.INSTANCE);
        object.add("activities",array);
        object.addProperty("status",status);
        object.addProperty("afk",afk);


        PayloadBuilder builder = new PayloadBuilder(3)
                .addData(object);
        try {
            this.sendPayload(builder.build());
        } catch (RateLimitOverflowException e) {
            e.printStackTrace();
        }
    }

    //TODO: IDK WORK OUT A SOLUTION FOR READING PACKETS EVEN THOUGH IT CURRENTLY WORKS FINE

}
