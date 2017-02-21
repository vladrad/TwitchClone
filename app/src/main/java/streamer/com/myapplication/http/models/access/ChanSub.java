package streamer.com.myapplication.http.models.access;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladi on 2/20/17.
 */

public class ChanSub {
    @SerializedName("view_until")
    private long viewUntil;
    @SerializedName("restricted_bitrates")
    private String restrictedBitrates;

}
