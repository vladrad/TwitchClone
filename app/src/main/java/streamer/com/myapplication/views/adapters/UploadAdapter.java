package streamer.com.myapplication.views.adapters;

import android.view.View;
import android.view.ViewGroup;

import streamer.com.myapplication.http.models.videos.Video;
import streamer.com.myapplication.views.adapters.touch.OnScaleTouchListener;
import streamer.com.myapplication.views.custom.UploadView;

/**
 * Created by Vladi on 2/9/17.
 */

public class UploadAdapter extends ListBaseAdapter<Video> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = new UploadView(parent.getContext()); // create new stream view
        }

        Video video = getItem(position); // getApi stream in list
        ((UploadView) convertView).setVideo(video); //set the stream
        final View view = convertView;
        view.setOnTouchListener(new OnScaleTouchListener(view,convertView.getContext())); //scale on touch listener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setScaleY(1f);
                view.setScaleX(1f);
            }
        });
        return convertView;
    }
}
