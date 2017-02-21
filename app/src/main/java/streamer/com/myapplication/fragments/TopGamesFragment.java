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
import streamer.com.myapplication.http.models.games.TopGame;
import streamer.com.myapplication.services.TwitchService;
import streamer.com.myapplication.views.adapters.TopGameAdapter;

import static streamer.com.myapplication.services.TwitchService.TwitchServiceEvent.TOP_GAMES_EVENT;

/**
 * Created by Vladi on 2/9/17.
 */

public class TopGamesFragment extends BaseEventFragment {

    private GridView gameGrid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.game_fragment, container, false);
        gameGrid = (GridView) view.findViewById(R.id.game_grid);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        TwitchService.get().getTopGames();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TwitchService.TwitchEvent twitchEvent) {
        if (twitchEvent.event.equals(TOP_GAMES_EVENT)) {// wait for event
            TopGame[] topGames = TwitchService.get().getCurrentTopGames();
            TopGameAdapter topGameAdapter = new TopGameAdapter();
            topGameAdapter.setList(Arrays.asList(topGames));
            gameGrid.setAdapter(topGameAdapter);
        }
    }


}
