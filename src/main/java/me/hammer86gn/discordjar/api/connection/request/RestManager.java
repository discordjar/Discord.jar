package me.hammer86gn.discordjar.api.connection.request;

public class RestManager {
    private static RestManager instance;
    private int requests = 0;

    private RestManager() {
        instance = this;

        new Thread(()-> {
            while (Thread.currentThread().isAlive()) {
                requests = 0;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"REST-API-RATELIMIT").start();
    }

    void addRequest() {
        requests++;
    }

    int getRequests() {
        return requests;
    }

    boolean canSend() {
        return requests < 50;
    }

    static RestManager getInstance() {
        return instance == null ? new RestManager() : instance;
    }

}
