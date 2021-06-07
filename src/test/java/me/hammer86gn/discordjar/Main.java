package me.hammer86gn.discordjar;

import me.hammer86gn.discordjar.api.DJAR;
import me.hammer86gn.discordjar.api.DJARManager;

public class Main {

    public static void main(String[] args) {

        DJAR djar = new DJARManager(Hidden.TOKEN).buildShardedClient(2);

    }

}
