package streamer.com.myapplication.fragments.directory;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;

import streamer.com.myapplication.fragments.StreamsFragment;
import streamer.com.myapplication.http.models.streams.Stream;
import streamer.com.myapplication.services.TwitchService;
import streamer.com.myapplication.views.adapters.StreamAdapter;

import static streamer.com.myapplication.services.TwitchService.TwitchServiceEvent.TOP_SEARCH_STREAM_EVENT;

/**
 * Created by Vladi on 2/9/17.
 */

public class LiveStreamsFragment extends StreamsFragment {

    @Override
    protected void doHttpRequest() {
        TwitchService.get().searchLiveStreams(game);
    } // search top games

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TwitchService.TwitchEvent twitchEvent) { // search uploaded
        if (twitchEvent.event.equals(TOP_SEARCH_STREAM_EVENT)) {
            Stream[] streams = TwitchService.get().getTopSearchStreams();
            StreamAdapter topStreamAdapter = new StreamAdapter();
            topStreamAdapter.setList(Arrays.asList(streams));
            channelList.setAdapter(topStreamAdapter);
        }
    }


}
