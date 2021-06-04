package me.hammer86gn.discordjar.api.connection.websocket;

import com.google.gson.JsonObject;

public class Payload {
    private JsonObject object = new JsonObject();

    public Payload(int op) {
        object.addProperty("op",op);
    }

    public Payload addData(JsonObject data) {
        this.object.add("d",object);
        return this;
    }
    public Payload addData(Long data) {
        this.object.addProperty("d",data);
        return this;
    }

    public String encode() {
        return this.object.toString();
    }

    @Override
    public String toString() {
        return this.encode();
    }
}
