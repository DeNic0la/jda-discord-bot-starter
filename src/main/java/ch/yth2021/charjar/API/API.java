package ch.yth2021.charjar.API;

import java.net.http.HttpClient;

public class API {

    /**
     * Get the Point of a User over the REST-API
     * @param userId the id of the user to get Information from
     * @return the Points of the User
     */
    public static int getPoints(String userId){
        return 1;
    }

    /**
     * Modify the amount of Points a User has over the REST-API
     * @param userId the ID of the User
     * @param amount the Amount to Add, if the Number is Negative the value will be subtracted
     */
    public static void modPoints(String userId, int amount){

    }

}