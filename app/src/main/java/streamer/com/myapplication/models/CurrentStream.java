package streamer.com.myapplication.models;

/**
 * Created by Vladi on 2/20/17.
 */

public class CurrentStream {

    public String currentChannel;
    public static CurrentStream instance;

    public static CurrentStream get() {
        if (instance == null) {
            instance = new CurrentStream();
        }
        return instance;
    }

}
