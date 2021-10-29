package ch.yth2021.charjar.API.Service;

import ch.yth2021.charjar.API.Service.model.Modify;
import ch.yth2021.charjar.API.Service.model.UserApiResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface UserService {
    
    @GET("/users/{id}/points")
    public Call<UserApiResponse> getUser(@Path("id") String userId);

    @POST("/users/{id}/points")
    public Call<Void> modifyPoints(@Path("id") String userId, @Body Modify data);

}
