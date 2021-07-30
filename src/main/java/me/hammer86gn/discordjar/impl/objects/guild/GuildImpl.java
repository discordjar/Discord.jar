package me.hammer86gn.discordjar.impl.objects.guild;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.hammer86gn.discordjar.api.DJAR;
import me.hammer86gn.discordjar.api.connection.request.RestRequest;
import me.hammer86gn.discordjar.api.objects.guild.Guild;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GuildImpl implements Guild {

    private final long id;
    private final DJAR djar;

    public GuildImpl(long id, DJAR djar) {
        this.id = id;
        this.djar = djar;
    }

    @Override
    public void changeGuildName(String name) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",name);
        RestRequest request = new RestRequest(djar, "/guilds/" + getID(), RestRequest.RequestType.PATCH, jsonObject);

        request.sendRequest();
    }

    @Override
    public String getGuildName() {
        return getGuildAsJson().get("name").getAsString();
    }

    @Override
    public String getIconHash() {
        return getGuildAsJson().get("icon").getAsString();
    }

    @Override
    public String getSplashHash() {
        return getGuildAsJson().get("splash").getAsString();
    }

    @Override
    public URL getIconURL() {
        URL url = null;
        try {
            url = new URL("https://cdn.discordapp.com/icons/" + getID() + "/" + getIconHash() + ".png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    @Override
    public URL getSplashURL() {
        URL url = null;

        try {
            url = new URL("https://cdn.discordapp.com/splashes/" + getID() + "/" + getSplashHash() + ".png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    @Override
    public long getOwnerID() {
        return getGuildAsJson().get("owner_id").getAsLong();
    }

    @Override
    public String getIDAsString() {
        return String.valueOf(id);
    }

    @Override
    public long getID() {
        return id;
    }

    @Override
    public JsonObject getGuildAsJson() {
        RestRequest request = new RestRequest(this.djar,"/guilds/" + this.getID(), RestRequest.RequestType.GET);
        JsonObject object = null;

        try {
           object = JsonParser.parseString(request.sendRequest().body().string()).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return object;
    }

    @Override
    public DJAR getDJAR() {
        return djar;
    }
}
