package me.hammer86gn.discordjar;

import me.hammer86gn.discordjar.api.DJAR;
import me.hammer86gn.discordjar.api.DJARManager;
import me.hammer86gn.discordjar.api.users.activity.Activity;
import me.hammer86gn.discordjar.api.users.activity.Status;

public class Main {

    public static void main(String[] args) {

        DJAR djar = new DJARManager(Hidden.TOKEN).setActivity(new Activity(new Status("With Discord.jar", Status.ActivityType.GAME), Status.StatusType.IDLE)).buildClient();

    }

}
