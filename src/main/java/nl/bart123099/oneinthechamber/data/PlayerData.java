package nl.bart123099.oneinthechamber.data;

import java.util.UUID;

public class PlayerData {
    private UUID uuid;
    private String userName;
    private int kills;
    private int deaths;

    public PlayerData(UUID uuid, String userName, int kills, int deaths) {
        this.uuid = uuid;
        this.userName = userName;
        this.kills = kills;
        this.deaths = deaths;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
}
