package com.example.nbateamviewer.networkservices.nbamodel;

/**
 * This is a plain java class to hold player details.
 */

import com.google.gson.annotations.SerializedName;

public class PlayerModel {
    @SerializedName("id")
    private int id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("position")
    private String position;

    @SerializedName("number")
    private int number;

    public PlayerModel(int id, String firstName, String lastName, String position, int number) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }
}
