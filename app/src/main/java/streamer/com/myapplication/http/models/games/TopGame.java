package streamer.com.myapplication.http.models.games;

/**
 * Created by Vladi on 2/9/17.
 */

public class TopGame {
    private Game game;
    private long viewers;
    private long channels;

    public Game getGame() {
        return game;
    }

    public long getViewers() {
        return viewers;
    }

    public long getChannels() {
        return channels;
    }
}
