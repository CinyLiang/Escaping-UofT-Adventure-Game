package data_access;

import org.json.JSONArray;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.play_card_game.CardGameDataAccessInterface;
import entity.Card;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class CardGameDataAccessObject implements CardGameDataAccessInterface {
    /**
     * The DAO for the Play Card Game Use Case.
     */
    private final OkHttpClient client = new OkHttpClient();
    private int cardNum;
    public CardGameDataAccessObject(int cardNum) {
        this.cardNum = cardNum;
    }

    @Override
    public List<Card> drawCards() {
        int cardNum = this.cardNum;
        try {
            String urlString = "https://deckofcardsapi.com/api/deck/new/draw/?count=" + cardNum;
            Request request = new Request.Builder()
                    .url(urlString)
                    .build();

            List<Card> cards = new ArrayList<>();
            try (Response response = client.newCall(request).execute()) {
                String jsondata = response.body().string();
                JSONObject subinfo = new JSONObject(jsondata);
                JSONArray fourCards = subinfo.getJSONArray("cards");

                Map<String, Integer> cardMap = new HashMap<>();
                cardMap.put("ACE", 1);
                cardMap.put("JACK", 11);
                cardMap.put("QUEEN", 12);
                cardMap.put("KING", 13);

                for (int i = 0; i < cardNum; i++) {
                    JSONObject card = fourCards.getJSONObject(i);
                    String cardVal = card.getString("value").trim();

                    if (cardVal.matches("\\d+")) {
                        int val = Integer.parseInt(cardVal);
                        cards.add(new Card(val));
                    } else if (cardMap.containsKey(cardVal)) {
                        cards.add(new Card(cardMap.get(cardVal)));
                    } else {
                        throw new RuntimeException("Invalid card value: " + cardVal);
                    }
                }
            }

            return cards;

        } catch (IOException e) {
            throw new RuntimeException("Failed to get cards from API", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while drawing cards", e);
        }
    }
}