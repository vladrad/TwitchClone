package streamer.com.myapplication.views.adapters;

import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import streamer.com.myapplication.controller.MainNavigationController;
import streamer.com.myapplication.http.models.streams.Stream;
import streamer.com.myapplication.models.CurrentStream;
import streamer.com.myapplication.views.adapters.touch.OnScaleTouchListener;
import streamer.com.myapplication.views.custom.StreamView;

import static streamer.com.myapplication.controller.MainNavigationController.Navigation.STREAM_ACTIVITY;


/**
 * Created by Vladi on 2/9/17.
 */

public class StreamAdapter extends ListBaseAdapter<Stream> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = new StreamView(parent.getContext()); // create new stream view
        }

        final Stream stream = getItem(position); // getApi stream in list
        ((StreamView) convertView).setStream(stream); //set the stream
        final View view = convertView;
        view.setOnTouchListener(new OnScaleTouchListener(view,convertView.getContext())); //scale on touch listener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainNavigationController.NavigationEvent navigationEvent = new MainNavigationController.NavigationEvent();
                navigationEvent.channel = stream.getChannel().getName();
                navigationEvent.navigation = STREAM_ACTIVITY;
                EventBus.getDefault().post(navigationEvent);
                view.setScaleY(1f); // reset size
                view.setScaleX(1f);
            }
        });
        return convertView;
    }



}
