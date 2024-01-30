package me.motasim.nanocraftdiscordlinked.models;

public class PlayerProfile {
    private String uuid;

    private String discordId;

    private String token;
    private Boolean verified;

    public PlayerProfile(String uuid, String discordId, String token, Boolean verified) {
        this.uuid = uuid;
        this.discordId = discordId;
        this.token = token;
        this.verified = verified;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

//    public void setVerified(Boolean state) {
//        this.verified = state;
//    }
//
//    public Boolean getVerified() {
//        return verified;
//    }
}
