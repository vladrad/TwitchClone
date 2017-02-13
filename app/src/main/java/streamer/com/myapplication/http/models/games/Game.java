package streamer.com.myapplication.http.models.games;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by Vladi on 2/9/17.
 */

public class Game {
    private String name;
    private long popularity;
    @SerializedName("_id")
    private long id;
    @SerializedName("giantbomb_id")
    private long giantbombId;
    private HashMap<String, String> box;
    private HashMap<String, String> logo;

    public String getName() {
        return name;
    }

    public long getPopularity() {
        return popularity;
    }

    public long getId() {
        return id;
    }

    public long getGiantbombId() {
        return giantbombId;
    }

    public HashMap<String, String> getBox() {
        return box;
    }

    public HashMap<String, String> getLogo() {
        return logo;
    }

}
