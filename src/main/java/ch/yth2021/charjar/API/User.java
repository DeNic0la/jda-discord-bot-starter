package ch.yth2021.charjar.API;

import ch.yth2021.charjar.API.Service.UserService;
import ch.yth2021.charjar.API.Service.model.Modify;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class User {
    public static String BASE_URL;
    private static final int RESPONSE_SUCCSESS = 200;

    private String userId;
    UserService service;

    public User(String userId) {
        this.userId = userId;

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(User.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        service = retrofit.create(UserService.class);
        
    }

    /**
     * Get the Point of a User over the REST-API
     *
     * @return the Points of the User
     */
    public int getPoints() throws IOException, APIRespondedBullshitException {
        try {
            return service.getUser(userId).execute().body().points;
        } catch (IOException ioException) {
            throw ioException;
        } catch (Exception e) {
            throw new APIRespondedBullshitException();
        }
    }

    /**
     * Modify the amount of Points a User has over the REST-API
     *
     * @param amount the Amount to Add, if the Number is Negative the value will be subtracted
     */
    public void modPoints(int amount) throws IOException, APIRespondedBullshitException {
        Modify m = new Modify(amount);
        int code;
        try {
            code = service.modifyPoints(userId, m).execute().code();

        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new APIRespondedBullshitException();
        }
        if (code == RESPONSE_SUCCSESS) {
            return;
        } else {
            throw new APIRespondedBullshitException();
        }

    }

}