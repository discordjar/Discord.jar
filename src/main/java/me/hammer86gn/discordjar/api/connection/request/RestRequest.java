package me.hammer86gn.discordjar.api.connection.request;

import com.google.gson.JsonObject;
import me.hammer86gn.discordjar.api.DJAR;
import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.IOException;
import java.net.URL;

public class RestRequest {

    private final String baseURL = "https://discord.com/api/v9/";

    private final DJAR djar;
    private final RequestType requestType;
    private final JsonObject data;
    private final String dest;

    private final OkHttpClient client;

    public RestRequest(DJAR djar, RequestType requestType, String dest, JsonObject data) {
        this.djar = djar;
        this.requestType = requestType;
        this.data = data;

        this.dest = baseURL + dest;

        this.client = djar.getHTTPClient();
    }

    public RestRequest(DJAR djar, RequestType requestType, String dest) {
        this.djar = djar;
        this.requestType = requestType;
        this.data = null;

        this.dest = baseURL + dest;

        this.client = djar.getHTTPClient();
    }

    public Response sendRequest() {
        Request.Builder reqBuilder = new Request.Builder().url(dest).addHeader("Authorization","Bot " +  djar.getBotToken());

        switch (requestType) {
            case GET:
                reqBuilder.get();
                break;
            case PUT:
                reqBuilder.put(RequestBody.create(data.toString(), MediaType.get("application/json")));
                break;
            case POST:
                reqBuilder.post(RequestBody.create(data.toString(), MediaType.get("application/json")));
                break;
            case PATCH:
                reqBuilder.patch(RequestBody.create(data.toString(), MediaType.get("application/json")));
                break;
            case DELETE:
                if(data == null) {
                    reqBuilder.delete();
                } else {
                    reqBuilder.delete(RequestBody.create(data.toString(), MediaType.get("application/json")));
                }
                break;
            default:
                reqBuilder.get();
                break;

        }

        if (RestManager.getInstance().canSend()) {
            Call call = client.newCall(reqBuilder.build());
            try {
                Response res = call.execute();
                RestManager.getInstance().addRequest();
                return res;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }



}
