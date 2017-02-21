package streamer.com.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;

import streamer.com.myapplication.R;
import streamer.com.myapplication.http.models.streams.Stream;
import streamer.com.myapplication.services.TwitchService;
import streamer.com.myapplication.views.adapters.GameDirectoryViewPager;
import streamer.com.myapplication.views.adapters.StreamAdapter;

import static streamer.com.myapplication.services.TwitchService.TwitchServiceEvent.TOP_STREAMS_EVENT;

/**
 * Created by Vladi on 2/9/17.
 */

public class GameDirectoryFragment extends BaseEventFragment {

    private ViewPager viewPager;

    private GameDirectoryViewPager gameDirectoryViewPager;

    private String game;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.game_directory, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.game_pager);
        gameDirectoryViewPager = new GameDirectoryViewPager(getActivity().getSupportFragmentManager());
        gameDirectoryViewPager.setMenus(getResources().getStringArray(R.array.game_directory));
        gameDirectoryViewPager.setGame(game);
        viewPager.setAdapter(gameDirectoryViewPager); //set up view pager with correct game name
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        TwitchService.get().getTopStreams();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TwitchService.TwitchEvent twitchEvent) { // wait for event and then do view pager
        if (twitchEvent.event.equals(TOP_STREAMS_EVENT)) {
            Stream[] streams = TwitchService.get().getCurrentTopStreams();
            StreamAdapter topStreamAdapter = new StreamAdapter();
            topStreamAdapter.setList(Arrays.asList(streams));
        }
    }

    public void setGame(String game) {
        this.game = game;
    }

}
