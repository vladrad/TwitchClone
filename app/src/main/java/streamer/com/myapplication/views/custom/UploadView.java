package streamer.com.myapplication.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import streamer.com.myapplication.R;
import streamer.com.myapplication.http.models.videos.Video;
import streamer.com.myapplication.tools.HtmlStringFormater;

/**
 * Created by Vladi on 2/12/17.
 */

public class UploadView extends RelativeLayout {


    protected Video video;
    protected ImageView streamPreview;
    protected TextView title;
    protected TextView pastBroadcast;
    protected TextView uploadedFrom;


    public UploadView(Context context) {
        super(context);
        initView(context);
    }

    public UploadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public UploadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        inflate(getContext(), R.layout.video_view, this); //inflate our view
        streamPreview = (ImageView) findViewById(R.id.stream_preview);//set custom view
        title = (TextView) findViewById(R.id.title);
        pastBroadcast = (TextView) findViewById(R.id.past_broadcast);
        uploadedFrom = (TextView) findViewById(R.id.playing_for);

    }

    public void setVideo(Video video) {
        this.video = video;
        setUpUi();
    }

    protected void setUpUi() {
        Picasso.with(getContext()).load(video.getPreview().get("large")).into(streamPreview); // set all the important info for user to see
        title.setText(video.getChannel().get("display_name") + "-" + video.getTitle());
        uploadedFrom.setText(HtmlStringFormater.createVideoLength(video, getContext()));
        pastBroadcast.setText(HtmlStringFormater.uploadFrom(video, getContext()));
    }
}
