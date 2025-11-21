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

            if (!json.has("results")) {
                System.err.println("API Error Response: " + responseBody);
                return new String[]{
                        "The trivia service is temporarily unavailable. Please try again in a moment.",
                        "True"
                };
            }

            JSONArray results = json.getJSONArray("results");

            if (results.length() == 0) {
                return new String[]{
                        "No questions available. Please try again.",
                        "True"
                };
            }

            JSONObject questionObj = results.getJSONObject(0);

            String question = decodeHtmlEntities(questionObj.getString("question"));
            String correctAnswer = questionObj.getString("correct_answer");

            return new String[]{question, correctAnswer};

        } catch (Exception e) {
            System.err.println("Error fetching trivia question: " + e.getMessage());
            e.printStackTrace();
            return new String[]{
                    "Unable to load question. Please try again. Click 'New Question'.",
                    "True"
            };
        }
    }

    private String decodeHtmlEntities(String text) {
        return text.replace("&quot;", "\"")
                .replace("&#039;", "'")
                .replace("&amp;", "&")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&rsquo;", "'")
                .replace("&ldquo;", "\"")
                .replace("&rdquo;", "\"")
                .replace("&hellip;", "...")
                .replace("&eacute;", "é")
                .replace("&nbsp;", " ");
    }
}