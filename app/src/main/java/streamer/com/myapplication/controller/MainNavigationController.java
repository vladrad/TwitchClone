package streamer.com.myapplication.controller;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import streamer.com.myapplication.MainActivity;
import streamer.com.myapplication.R;
import streamer.com.myapplication.StreamActivity;
import streamer.com.myapplication.fragments.GameDirectoryFragment;
import streamer.com.myapplication.fragments.TopGamesFragment;
import streamer.com.myapplication.fragments.StreamsFragment;
import streamer.com.myapplication.models.CurrentStream;


/**
 * Created by Vladi on 2/9/17.
 */

public class MainNavigationController {

    private MainActivity activity;
    //Fragments
    private TopGamesFragment gamesFragment;
    private StreamsFragment topStreamsFragment;
    private GameDirectoryFragment gameDirectoryFragment;


    public enum  Navigation {GAME_DIRECTORY,STREAM_ACTIVITY}

    public static class NavigationEvent{ // our navigation event
        public Navigation navigation;
        public String game;
        public String channel;
    }

    public MainNavigationController(MainActivity context){
        activity = context;
    }

    public void setUpDefaultFragment(){//set up default fragment
        gamesFragment = new TopGamesFragment();
        gamesFragment.setRetainInstance(true);

        topStreamsFragment = new StreamsFragment();
        topStreamsFragment.setRetainInstance(true);

        showTopGames();
    }


    public void registerForEvents(){
        EventBus.getDefault().register(this);
    } // register for events

    public void unRegisterForEvents(){
        EventBus.getDefault().unregister(this);
    } // un register

    public void showTopGames(){ // show top games
        activity.setTitle(activity.getString(R.string.games));
        loadFragment(gamesFragment);
    }


    public void showTopChannels(){
        activity.setTitle(activity.getString(R.string.channels)); //show top channels
        loadFragment(topStreamsFragment);
    }

    public void loadFragment(Fragment fragment){ // load fragment
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout,fragment);
        fragmentTransaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NavigationEvent navigationEvent) {
        if(navigationEvent.navigation.equals(Navigation.GAME_DIRECTORY)){ //listen for game directory change... sends event via adapter to us know to change to view pager with these games
            activity.setTitle(navigationEvent.game);
            gameDirectoryFragment = new GameDirectoryFragment();
            gameDirectoryFragment.setGame(navigationEvent.game);
            loadFragment(gameDirectoryFragment);
        } else if (navigationEvent.navigation.equals(Navigation.STREAM_ACTIVITY)){ //handle launch stream activity
            CurrentStream.get().currentChannel = navigationEvent.channel;
            Intent steamActivity = new Intent(activity.getApplicationContext(), StreamActivity.class);
            activity.startActivity(steamActivity);
        }
    }

}
