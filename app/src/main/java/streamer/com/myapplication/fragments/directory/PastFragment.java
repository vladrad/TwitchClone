package streamer.com.myapplication.fragments.directory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;

import streamer.com.myapplication.fragments.StreamsFragment;
import streamer.com.myapplication.http.models.videos.Video;
import streamer.com.myapplication.services.TwitchService;
import streamer.com.myapplication.views.adapters.ArchiveAdapter;

import static streamer.com.myapplication.services.TwitchService.TwitchServiceEvent.TOP_PAST_STREAMS_EVENT;

/**
 * Created by Vladi on 2/9/17.
 */

public class PastFragment extends StreamsFragment {

    @Override
    protected void doHttpRequest(){
        TwitchService.get().searchPastBroadcasts(game);
    } //search top archive

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TwitchService.TwitchEvent twitchEvent) { // wait for search event
        if(twitchEvent.event.equals(TOP_PAST_STREAMS_EVENT)){
            Video[] videos = TwitchService.get().getTopArchiveStreams();
            ArchiveAdapter archiveAdapter = new ArchiveAdapter();
            archiveAdapter.setList(Arrays.asList(videos));
            channelList.setAdapter(archiveAdapter);
        }
    }

}
