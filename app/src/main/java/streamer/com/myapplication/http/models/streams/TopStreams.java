package streamer.com.myapplication.http.models.streams;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladi on 2/12/17.
 */

public class TopStreams {

    @SerializedName("streams")
    private Stream[] streams;

    public Stream[] getStreams() {
        return streams;
    }

}
