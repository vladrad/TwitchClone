package streamer.com.myapplication.fragments.directory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;

import streamer.com.myapplication.fragments.StreamsFragment;
import streamer.com.myapplication.http.models.videos.Video;
import streamer.com.myapplication.services.TwitchService;
import streamer.com.myapplication.views.adapters.UploadAdapter;

import static streamer.com.myapplication.services.TwitchService.TwitchServiceEvent.TOP_UPLOAD_EVENTS;

/**
 * Created by Vladi on 2/9/17.
 */

public class UploadFragment extends StreamsFragment {


    @Override
    protected void doHttpRequest() {
        TwitchService.get().searchTopUploads(game);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TwitchService.TwitchEvent twitchEvent) {
        if (twitchEvent.event.equals(TOP_UPLOAD_EVENTS)) {
            Video[] videos = TwitchService.get().getTopUploadedStreams();
            UploadAdapter uploadAdapter = new UploadAdapter();
            uploadAdapter.setList(Arrays.asList(videos));
            channelList.setAdapter(uploadAdapter);
        }
    }

}
