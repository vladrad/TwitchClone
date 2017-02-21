package streamer.com.myapplication.http.models.access;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladi on 2/20/17.
 */

public class Access {

    private String token;
    private String sig;
    @SerializedName("mobile_restricted")
    private boolean mobileRestricted;

    public String getToken() {
        return token;
    }

    public String getSig() {
        return sig;
    }

    public boolean isMobileRestricted() {
        return mobileRestricted;
    }

    public String getTokenAsString(){
        Gson gson = new Gson();
        return gson.toJson(token);
    }

}
