package ch.yth2021.charjar.API;

import ch.yth2021.charjar.discord.bot.ApplicationProperties;

import java.net.http.HttpClient;

public class User {
    private String userId;

    public User(String userId){
        this.userId = userId;
    }

    /**
     * Get the Point of a User over the REST-API
     * @return the Points of the User
     */
    public int getPoints(){
        return 1;
    }

    /**
     * Modify the amount of Points a User has over the REST-API
     * @param amount the Amount to Add, if the Number is Negative the value will be subtracted
     */
    public void modPoints(int amount){

    }

}