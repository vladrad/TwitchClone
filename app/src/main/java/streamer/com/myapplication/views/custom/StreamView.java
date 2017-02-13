package streamer.com.myapplication.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import streamer.com.myapplication.R;
import streamer.com.myapplication.http.models.streams.Stream;
import streamer.com.myapplication.tools.HtmlStringFormater;

/**
 * Created by Vladi on 2/12/17.
 */

public class StreamView extends RelativeLayout {

    private Stream stream;
    private ImageView streamPreview;
    private TextView title;
    private TextView description;
    private TextView playing;


    public StreamView(Context context) {
        super(context);
        initView(context);
    }

    public StreamView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public StreamView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        inflate(getContext(), R.layout.channel_view, this);
        streamPreview = (ImageView) findViewById(R.id.stream_preview);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        playing = (TextView) findViewById(R.id.playing_for);

    }

    public void setStream(Stream stream) {
        this.stream = stream;
        setUpUi();
    }


    public void setUpUi() {
        Picasso.with(getContext()).load(stream.getPreview().get("large")).into(streamPreview); // set slightly different info
        title.setText(stream.getChannel().getName().toUpperCase());
        description.setText(stream.getChannel().getStatus());
        playing.setText(HtmlStringFormater.createPlayingFor(stream, getContext()));
    }
}
