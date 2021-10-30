package ch.yth2021.charjar.API;

import ch.yth2021.charjar.API.Service.UserService;
import ch.yth2021.charjar.API.Service.model.Modify;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.discord.processor.model.EventListener;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            return service.getUser(userId).execute().body().balance;
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
        Response<Void> execute;
        try {
            execute = service.modifyPoints(userId, m).execute();

        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new APIRespondedBullshitException();
        }

        if (!execute.isSuccessful()) {
            throw new APIRespondedBullshitException();
        }
    }

}