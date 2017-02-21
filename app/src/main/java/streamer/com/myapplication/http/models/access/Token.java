package streamer.com.myapplication.http.models.access;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by Vladi on 2/20/17.
 */

public class Token {
    @SerializedName("user_id")
    private String userId;
    private String channel;
    private long expires;
    @SerializedName("chansub")
    private ChanSub chanSub;
    @SerializedName("private")
    private HashMap<String, Boolean> privateInfo;
    @SerializedName("privileged")
    private HashMap<String, Boolean> privileged;

    public String getUserId() {
        return userId;
    }

    public String getChannel() {
        return channel;
    }

    public long getExpires() {
        return expires;
    }

    public ChanSub getChanSub() {
        return chanSub;
    }

    public HashMap<String, Boolean> getPrivateInfo() {
        return privateInfo;
    }

    public HashMap<String, Boolean> getPrivileged() {
        return privileged;
    }

}
