package streamer.com.myapplication.http.models.videos;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by Vladi on 2/12/17.
 */

public class Video {

    private HashMap<String, String> channel;
    private String title;
    private long views;
    private long length;
    private HashMap<String, String> preview;
    @SerializedName("created_at")
    private String createdAt;

    public HashMap<String, String> getChannel() {
        return channel;
    }

    public String getTitle() {
        return title;
    }

    public long getViews() {
        return views;
    }

    public long getLength() {
        return length;
    }

    public HashMap<String, String> getPreview() {
        return preview;
    }

    public String getCreatedAt() {
        return createdAt;
    }

}
