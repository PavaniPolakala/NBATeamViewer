package com.example.nbateamviewer.networkservices.nbamodel;

/**
 * This is a plain java class to hold team details.
 */
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TeamModel {

    @SerializedName("id")
    private int id;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("wins")
    private int wins;

    @SerializedName("losses")
    private int losses;

    @SerializedName("players")
    private List<PlayerModel> playersList;

    public TeamModel(int id, String fullName, int wins, int losses, List<PlayerModel> playersList) {
        this.id = id;
        this.fullName = fullName;
        this.wins = wins;
        this.losses = losses;
        this.playersList = playersList;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public List<PlayerModel> getPlayersList() {
        return playersList;
    }
}
