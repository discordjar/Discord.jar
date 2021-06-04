package me.hammer86gn.discordjar.api.connection.websocket.intents;

public enum GatewayIntents {
    GUILDS,
    GUILD_MEMBERS,
    GUILD_BANS,
    GUILD_EMOJIS,
    GUILD_INTEGRATIONS,
    GUILD_WEBHOOKS,
    GUILD_INVITES,
    GUILD_VOICE_STATES,
    GUILD_PRESENCES,
    GUILD_MESSAGES,
    GUILD_MESSAGE_REACTIONS,
    GUILD_MESSAGE_TYPING,
    DIRECT_MESSAGES,
    DIRECT_MESSAGE_REACTIONS,
    DIRECT_MESSAGE_TYPING
    ;

    int value() {
        return 1 << this.ordinal();
    }

    public static int summarize(GatewayIntents... intents) {
        int sum = 0;
        for (GatewayIntents intent : intents) {
            sum |= intent.value();
        }
        return sum;
    }

}
