package ch.yth2021.charjar.API;

import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.API.model.QuizApiResponse;
import ch.yth2021.charjar.API.model.QuizResponse;
import com.google.gson.Gson;
import org.unbescape.html.HtmlEscape;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Quiz {
    public static String BASE_URL;

    private String getResponseFromAPI() throws IOException {
        URL url = new URL("https://opentdb.com/api.php?amount=1&type=multiple");
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return content.toString();
    }

    public Quiz() throws IOException, APIRespondedBullshitException {

        String responseFromAPI = getResponseFromAPI();
        Gson gson = new Gson();

        QuizApiResponse apiResp = gson.fromJson(responseFromAPI, QuizApiResponse.class);
        QuizResponse quizResponse = apiResp.results[0];
        List<String> resp = new ArrayList<>();
        for (String s : quizResponse.incorrect_answers) {
            resp.add(HtmlEscape.unescapeHtml(s));
        }
        answers = resp;
        correctAnswer = HtmlEscape.unescapeHtml(quizResponse.correct_answer);
        question = HtmlEscape.unescapeHtml(quizResponse.question);

    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    private String question;
    private List<String> answers;
    private String correctAnswer;
}
