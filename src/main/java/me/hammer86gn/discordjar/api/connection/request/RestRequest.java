package me.hammer86gn.discordjar.api.connection.request;

import com.google.gson.JsonObject;
import me.hammer86gn.discordjar.api.DJAR;
import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.IOException;

public class RestRequest {

    private final String baseURL = "https://discord.com/api";
    private final DJAR djar;
    private final String apiURL;
    private final RequestType type;
    private final JsonObject body;

    public RestRequest(DJAR djar, String url, RequestType type) {
        this.djar = djar;
        this.apiURL = baseURL + url;
        this.type = type;
        this.body = null;
    }

    public RestRequest(DJAR djar, String url, RequestType type, JsonObject body) {
        this.djar = djar;
        this.apiURL = baseURL + url;
        this.type = type;
        this.body = body;
    }

    public enum RequestType {
        GET,
        POST,
        PATCH,
        PUT,
        DELETE
    }

    public Response sendRequest() {
        Request.Builder requestBuilder = new Request.Builder()
                .url(apiURL)
                .addHeader("Authorization","Bot " + djar.getBotToken());

        MediaType contentType = MediaType.parse("application/json");
        RequestBody requestBody = null;
        if (body != null) {
            requestBody = RequestBody.create(body.getAsString(),contentType);
        }

        switch (type) {
            case GET:
                requestBuilder.get();
                break;
            case POST:
                requestBuilder.post(requestBody);
                break;
            case PUT:
                requestBuilder.put(requestBody);
                break;
            case PATCH:
                requestBuilder.patch(requestBody);
                break;
            case DELETE:
                requestBuilder.delete(requestBody);
        }

        Call call = djar.getHTTPClient().newCall(requestBuilder.build());

        try {
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
