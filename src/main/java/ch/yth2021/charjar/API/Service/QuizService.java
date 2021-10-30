package ch.yth2021.charjar.API.Service;

import ch.yth2021.charjar.API.Service.model.QuizApiResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface QuizService {
    @GET("/api.php")
    public Call<QuizApiResponse> getQuiz(@Body String params);
}
