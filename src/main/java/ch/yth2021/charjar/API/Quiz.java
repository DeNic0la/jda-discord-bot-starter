package ch.yth2021.charjar.API;

import ch.yth2021.charjar.API.Service.QuizService;
import ch.yth2021.charjar.API.Service.model.QuizApiResponse;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Quiz {
    public static String BASE_URL;

    public Quiz() throws IOException, APIRespondedBullshitException {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        QuizService service = retrofit.create(QuizService.class);

        Response<QuizApiResponse> response = service.getQuiz("?amount=1&type=multiple").execute();
        if (!response.isSuccessful()) {
            throw new APIRespondedBullshitException();
        }
        System.out.println(response);


    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    private String question;
    private String[] answers;
    private String correctAnswer;
}
