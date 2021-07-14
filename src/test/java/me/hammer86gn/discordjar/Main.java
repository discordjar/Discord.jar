package me.hammer86gn.discordjar;

import me.hammer86gn.discordjar.api.DJAR;
import me.hammer86gn.discordjar.api.DJARManager;
import me.hammer86gn.discordjar.api.objects.activity.Activity;
import me.hammer86gn.discordjar.api.objects.activity.Status;

public class Main {

    public static void main(String[] args) {

        DJARManager manager = new DJARManager(Hidden.TOKEN).setActivity(new Activity(new Status("With Discord.jar", Status.ActivityType.GAME), Status.StatusType.IDLE));

        for (int i=0;i<3;i++) {
            manager.buildShardedClient(i,4);
        }
    }

}
