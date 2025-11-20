package data_access;

import use_case.trivia_game.TriviaGameDataAccessInterface;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class OpenTriviaAPI implements TriviaGameDataAccessInterface {
    private static final String API_URL =
            "https://opentdb.com/api.php?amount=1&category=17&type=boolean";

    @Override
    public String[] fetchQuestion() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API_URL)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            JSONObject json = new JSONObject(responseBody);
            JSONArray results = json.getJSONArray("results");
            JSONObject questionObj = results.getJSONObject(0);

            String question = questionObj.getString("question");
            String correctAnswer = questionObj.getString("correct_answer");

            return new String[]{question, correctAnswer};

        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{"Error loading question", "True"};
        }
    }
}