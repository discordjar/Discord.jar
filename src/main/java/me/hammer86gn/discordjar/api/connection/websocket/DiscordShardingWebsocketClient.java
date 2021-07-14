package me.hammer86gn.discordjar.api.connection.websocket;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.hammer86gn.discordjar.api.DJAR;
import me.hammer86gn.discordjar.api.connection.websocket.exception.RateLimitOverflowException;
import me.hammer86gn.discordjar.api.connection.websocket.payload.PayloadBuilder;
import me.hammer86gn.discordjar.api.objects.activity.Activity;

import java.net.URISyntaxException;

public class DiscordShardingWebsocketClient extends DiscordWebsocketClient {

    private final DJAR djar;
    private final int id;
    private final int shardTotal;

    public DiscordShardingWebsocketClient(DJAR djar,int id, int shardTotal) throws URISyntaxException {
        super(djar);
        this.djar = djar;
        this.id = id;
        this.shardTotal = shardTotal;
    }

    public DiscordShardingWebsocketClient(DJAR djar, Activity activity, int id, int shardTotal) throws URISyntaxException {
        super(djar,activity);
        this.djar = djar;
        this.id = id;
        this.shardTotal = shardTotal;
    }


    @Override
    public void identify(JsonObject message) {

        PayloadBuilder payload = new PayloadBuilder(2);

        JsonObject data = new JsonObject();
        data.addProperty("token",djar.getBotToken());
        data.addProperty("intents",djar.getGatewayIntents());


        JsonArray sharding = new JsonArray();
        sharding.add(id);
        sharding.add(shardTotal);

        data.add("sharding",sharding);

        JsonObject properties = new JsonObject();
        properties.addProperty("$os",System.getProperty("os.name"));
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
}
