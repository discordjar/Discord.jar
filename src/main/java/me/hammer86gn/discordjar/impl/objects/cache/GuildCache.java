package me.hammer86gn.discordjar.impl.objects.cache;

import me.hammer86gn.discordjar.api.objects.cache.IDJARCache;
import me.hammer86gn.discordjar.api.objects.guild.Guild;

import java.util.HashMap;
import java.util.Map;

public class GuildCache implements IDJARCache<Long, Guild> {
    private static GuildCache instance;
    private Map<Long,Guild> guildMap = new HashMap<>();

    private GuildCache() {
        instance = this;
    }

    @Override
    public void cache(Guild guild) {
        guildMap.put(guild.getID(),guild);
    }

    @Override
    public void cacheAll(Guild[] guilds) {
        for (Guild guild : guilds) {
            guildMap.put(guild.getID(), guild);
        }
    }

    @Override
    public Guild getByKey(Long query) {
        return guildMap.get(query);
    }

    @Override
    public Map<Long, Guild> getAll() {
        return guildMap;
    }

    public static GuildCache getInstance() {
        return instance == null ? new GuildCache() : instance;
    }
}
