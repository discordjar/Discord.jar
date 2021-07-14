package me.hammer86gn.discordjar.api.objects.activity;

public class Activity {

    private final Status activity;
    private final Status.StatusType status;

    public Activity(Status activity, Status.StatusType status) {
        this.activity = activity;
        this.status = status;
    }

    public Status getActivity() {
        return activity;
    }

    public Status.StatusType getStatus() {
        return status;
    }
}
