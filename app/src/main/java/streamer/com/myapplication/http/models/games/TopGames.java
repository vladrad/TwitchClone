package streamer.com.myapplication.http.models.games;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladi on 2/9/17.
 */

public class TopGames {
    @SerializedName("top") //getApi top games
    private TopGame[] games; // creates the game objects

    public TopGame[] getGames() {
        return games;
    }
}
