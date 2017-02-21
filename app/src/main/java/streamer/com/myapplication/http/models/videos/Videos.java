package streamer.com.myapplication.http.models.videos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladi on 2/12/17.
 */

public class Videos {

    @SerializedName("vods") // with android token from charles, seems to getApi more info on this api
    private Video[] videos;

    public Video[] getVideos() {
        return videos;
    }
}
