package streamer.com.myapplication.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import streamer.com.myapplication.fragments.StreamsFragment;
import streamer.com.myapplication.fragments.directory.PastFragment;
import streamer.com.myapplication.fragments.directory.LiveStreamsFragment;
import streamer.com.myapplication.fragments.directory.UploadFragment;

/**
 * Created by Vladi on 2/12/17.
 */

public class GameDirectoryViewPager extends FragmentStatePagerAdapter {

    private static final int MAX_SIZE = 3;
    private String[] menu;
    private String game;

    public GameDirectoryViewPager(FragmentManager fm) {
        super(fm);
    }

    public void setMenus(String[] menu){
        this.menu = menu;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            LiveStreamsFragment liveStreamsFragment = new LiveStreamsFragment();
            liveStreamsFragment.setGame(game);
            return liveStreamsFragment;
        } else if(position == 1){
            PastFragment archiveFragment = new PastFragment();
            archiveFragment.setGame(game);
            return archiveFragment;
        } else if(position == 2){
            UploadFragment uploadFragment = new UploadFragment();
            uploadFragment.setGame(game);
            return uploadFragment;
        }

        return new StreamsFragment(); // default fragment
    }

    @Override
    public int getCount() {
        return MAX_SIZE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  menu[position];
    }

    public void setGame(String game) {
        this.game = game;
    }
}
