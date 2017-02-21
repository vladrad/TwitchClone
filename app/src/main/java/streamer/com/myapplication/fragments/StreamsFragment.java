package streamer.com.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;

import streamer.com.myapplication.R;
import streamer.com.myapplication.http.models.streams.Stream;
import streamer.com.myapplication.services.TwitchService;
import streamer.com.myapplication.views.adapters.StreamAdapter;

import static streamer.com.myapplication.services.TwitchService.TwitchServiceEvent.TOP_STREAMS_EVENT;

/**
 * Created by Vladi on 2/9/17.
 */

public class StreamsFragment extends BaseEventFragment {

    protected GridView channelList;
    protected String game;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.channel_streams, container, false);
        channelList = (GridView) view.findViewById(R.id.channel_list);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        doHttpRequest();
    }

    protected void doHttpRequest() {
        TwitchService.get().getTopStreams();
    } // getApi the top streams

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TwitchService.TwitchEvent twitchEvent) {
        if (twitchEvent.event.equals(TOP_STREAMS_EVENT)) { // wait for event
            Stream[] streams = TwitchService.get().getCurrentTopStreams();
            StreamAdapter topStreamAdapter = new StreamAdapter();
            topStreamAdapter.setList(Arrays.asList(streams));
            channelList.setAdapter(topStreamAdapter);
        }
    }


    public void setGame(String game) {
        this.game = game;
    }


}
