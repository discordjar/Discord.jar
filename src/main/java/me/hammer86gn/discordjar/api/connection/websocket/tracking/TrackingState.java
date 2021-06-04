package me.hammer86gn.discordjar.api.connection.websocket.tracking;

import java.util.Collection;

public interface TrackingState<T> {

    Collection<T> getRecent();

    void addToCache(T t);


}
