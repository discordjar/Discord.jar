package me.hammer86gn.discordjar.api.objects.cache;

import java.util.Map;

public interface IDJARCache<K,V> {

    void cache(V v);

    void cacheAll(V[] vs);

    V getByKey(K query);

    Map<K,V> getAll();

}
