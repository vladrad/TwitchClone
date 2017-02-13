package streamer.com.myapplication.http.models.streams;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by Vladi on 2/12/17.
 */

public class Stream {

    @SerializedName("_id") //main structure for stream object
    private long id;
    private String game;
    private long viewers;
    @SerializedName("video_height")
    private int videoHeight;
    private long delay;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("is_playlist")
    private boolean isPlaylist;
    private HashMap<String,String> preview;
    @SerializedName("channel")
    private Channel channel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public long getViewers() {
        return viewers;
    }

    public void setViewers(long viewers) {
        this.viewers = viewers;
    }

    public int getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isPlaylist() {
        return isPlaylist;
    }

    public void setPlaylist(boolean playlist) {
        isPlaylist = playlist;
    }

    public HashMap<String, String> getPreview() {
        return preview;
    }

    public void setPreview(HashMap<String, String> preview) {
        this.preview = preview;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
