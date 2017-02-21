package streamer.com.myapplication.fragments;


import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Vladi on 2/12/17.
 */

public class BaseEventFragment extends Fragment {
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this); // register event
    }


    @Override
    public void onStop() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); // unregister event
    }


}
