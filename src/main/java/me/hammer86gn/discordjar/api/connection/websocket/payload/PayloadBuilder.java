package me.hammer86gn.discordjar.api.connection.websocket.payload;

import com.google.gson.JsonObject;

public class PayloadBuilder {

    private final int opCode;

    private boolean data = false;
    private JsonObject dataObject;

    public PayloadBuilder(int opCode) {
        this.opCode = opCode;
    }

    public String build() {
        if (data) {
            JsonObject payload = new JsonObject();
            payload.addProperty("op",opCode);
            payload.add("d",dataObject);

            return payload.toString();
        } else {
            JsonObject payload = new JsonObject();
            payload.addProperty("op",opCode);

            return payload.toString();
        }
    }

    public String build(Long num) {
            JsonObject payload = new JsonObject();
            payload.addProperty("op",opCode);
            payload.addProperty("d",num);

            return payload.toString();
    }

    public PayloadBuilder addData(JsonObject data) {
        this.dataObject = data;
        this.data = true;

        return this;
    }

    public PayloadBuilder addData() {
        this.data = true;
        this.dataObject = new JsonObject();

        return this;
    }

    public PayloadBuilder addEntryToData(String key,Number value) {
        this.dataObject.addProperty(key, value);

        return this;
    }

    public PayloadBuilder addEntryToData(String key,char value) {
        this.dataObject.addProperty(key, value);

        return this;
    }

    public PayloadBuilder addEntryToData(String key,String value) {
        this.dataObject.addProperty(key, value);

        return this;
    }

    public PayloadBuilder addEntryToData(String key,boolean value) {
        this.dataObject.addProperty(key,value);

        return this;
    }


}
