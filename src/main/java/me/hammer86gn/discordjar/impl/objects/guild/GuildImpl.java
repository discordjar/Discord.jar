package me.hammer86gn.discordjar.impl.objects.guild;

import me.hammer86gn.discordjar.api.DJAR;
import me.hammer86gn.discordjar.api.objects.guild.Guild;

public class GuildImpl implements Guild {

    private final long id;
    private final DJAR djar;

    public GuildImpl(long id, DJAR djar) {
        this.id = id;
        this.djar = djar;
    }

    @Override
    public String getIDString() {
        return String.valueOf(id);
    }

    @Override
    public long getID() {
        return id;
    }

    @Override
    public DJAR getDJAR() {
        return djar;
    }
}
