package streamer.com.myapplication.tools;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import streamer.com.myapplication.R;
import streamer.com.myapplication.http.models.streams.Stream;
import streamer.com.myapplication.http.models.videos.Video;

/**
 * Created by Vladi on 2/12/17.
 */

public class HtmlStringFormater { // used to format thin text

    public static Spanned createPlayingFor(Stream stream, Context context) { //loads the bold format used in channel stream
        if (stream.getViewers() == 1) { //check for 1
            return Html.fromHtml(String.format(context.getResources().getString(R.string.playing_for_single), stream.getChannel().getGame(), NumberFormat.getIntegerInstance().format(stream.getViewers()))); // forever lonely
        } else {
            return Html.fromHtml(String.format(context.getResources().getString(R.string.playing_for_multi), stream.getChannel().getGame(), NumberFormat.getIntegerInstance().format(stream.getViewers())));
        }
    }

    public static Spanned createUploadedString(Video video, Context context) { //loads the bold format used in uploaded videos stream
        Date date = getDate(video.getCreatedAt());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        String[] days = context.getResources().getStringArray(R.array.days_of_week);
        return Html.fromHtml(String.format(context.getResources().getString(R.string.past_broadcast), days[dayOfWeek]));
    }

    public static Spanned createVideoLength(Video video, Context context) {
        return Html.fromHtml(String.format(context.getResources().getString(R.string.view_length), NumberFormat.getIntegerInstance().format(video.getViews()), formatSeconds(video.getLength())));
    }

    public static Spanned uploadFrom(Video video, Context context) {
        Date date = getDate(video.getCreatedAt());
        return Html.fromHtml(String.format(context.getResources().getString(R.string.upload_from), getFormatedUploadDate(date)));
    }

    private static Date getDate(String time) { //change twitch string to date using this format, can be moved to its own Dates Utility class
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        Date date = null;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static String getFormatedUploadDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        String formatedDate = dateFormat.format(date);
        return formatedDate;
    }

    //from stack overflow
    private static String formatSeconds(long timeInSeconds) { // have written something for the Gigwalk app, its very similar here
        long hours = timeInSeconds / 3600; // convert hours
        long secondsLeft = timeInSeconds - hours * 3600; // getApi how many seconds left
        long minutes = secondsLeft / 60; // getApi minutes
        long seconds = secondsLeft - minutes * 60; // getApi seconds

        String formattedTime = ""; // because it a long we need to check for trailing 0 eg. 01 not 1
        if (hours < 10)
            formattedTime += "0";
        formattedTime += hours + ":";

        if (minutes < 10)
            formattedTime += "0";
        formattedTime += minutes + ":";

        if (seconds < 10)
            formattedTime += "0";
        formattedTime += seconds;

        return formattedTime;
    }

}
