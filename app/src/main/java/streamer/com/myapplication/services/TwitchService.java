package streamer.com.myapplication.services;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streamer.com.myapplication.http.HttpService;
import streamer.com.myapplication.http.models.access.Access;
import streamer.com.myapplication.http.models.games.TopGame;
import streamer.com.myapplication.http.models.games.TopGames;
import streamer.com.myapplication.http.models.streams.Stream;
import streamer.com.myapplication.http.models.streams.TopStreams;
import streamer.com.myapplication.http.models.videos.Video;
import streamer.com.myapplication.http.models.videos.Videos;

/**
 * Created by Vladi on 2/12/17.
 */

public class TwitchService {
    private static TwitchService twitchHttpService;
    private TopGame[] topGames;
    private Stream[] topStreams;
    private Stream[] topSearchStreams;
    private Video[] topArchiveStreams;
    private Video[] topUploadedStreams;
    private Access access;
    private String m3u8;

    public enum TwitchServiceEvent {TOP_GAMES_EVENT, TOP_STREAMS_EVENT, TOP_SEARCH_STREAM_EVENT, TOP_PAST_STREAMS_EVENT, TOP_UPLOAD_EVENTS, GET_ACCESS, GET_M3U8, DOWNLOAD_ERROR}

    public class TwitchEvent {
        public String errorMsg; // will be used for event handling
        public TwitchServiceEvent event;
    }

    public static TwitchService get() { //
        if (twitchHttpService == null) { //getApi top event
            twitchHttpService = new TwitchService();
            return twitchHttpService;
        } else {
            return twitchHttpService;
        }
    }


    public void getTopGames() {
        HttpService.getApi().getTopGames().enqueue(new Callback<TopGames>() { // getApi the top games, this is the default http call
            @Override
            public void onResponse(Call<TopGames> call, Response<TopGames> response) {
                topGames = response.body().getGames();
                TwitchEvent twitchEvent = new TwitchEvent();
                twitchEvent.event = TwitchServiceEvent.TOP_GAMES_EVENT;
                EventBus.getDefault().post(twitchEvent);
            }

            @Override
            public void onFailure(Call<TopGames> call, Throwable t) {

            }
        });
    }

    public void getTopStreams() {
        HttpService.getApi().getChannels().enqueue(new Callback<TopStreams>() { // this is called when user presses channels in the menu
            @Override
            public void onResponse(Call<TopStreams> call, Response<TopStreams> response) {
                topStreams = response.body().getStreams();
                TwitchEvent twitchEvent = new TwitchEvent();
                twitchEvent.event = TwitchServiceEvent.TOP_STREAMS_EVENT;
                EventBus.getDefault().post(twitchEvent);
            }

            @Override
            public void onFailure(Call<TopStreams> call, Throwable t) {

            }
        });
    }

    public void searchLiveStreams(String game) { //search live streams, first tab of view pager
        HttpService.getApi().searchStreams(game).enqueue(new Callback<TopStreams>() {
            @Override
            public void onResponse(Call<TopStreams> call, Response<TopStreams> response) {
                topSearchStreams = response.body().getStreams();
                TwitchEvent twitchEvent = new TwitchEvent();
                twitchEvent.event = TwitchServiceEvent.TOP_SEARCH_STREAM_EVENT;
                EventBus.getDefault().post(twitchEvent);
            }

            @Override
            public void onFailure(Call<TopStreams> call, Throwable t) {

            }
        });
    }

    public void searchPastBroadcasts(String game) {
        HttpService.getApi().searchPastBroadCasts(game).enqueue(new Callback<Videos>() { // search past broad casts
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                topArchiveStreams = response.body().getVideos();
                TwitchEvent twitchEvent = new TwitchEvent();
                twitchEvent.event = TwitchServiceEvent.TOP_PAST_STREAMS_EVENT;
                EventBus.getDefault().post(twitchEvent);
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

            }
        });
    }

    public void searchTopUploads(String game) {
        HttpService.getApi().searchUploaded(game).enqueue(new Callback<Videos>() { // search the top uploads tab in view pager
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                topUploadedStreams = response.body().getVideos();
                TwitchEvent twitchEvent = new TwitchEvent();
                twitchEvent.event = TwitchServiceEvent.TOP_UPLOAD_EVENTS;
                EventBus.getDefault().post(twitchEvent);
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

            }
        });
    }

    public void getAccessToken(String channel) {
        access = null; //clear the channel access token
        HttpService.getApi().getAccessToken(channel).enqueue(new Callback<Access>() { // search the top uploads tab in view pager
            @Override
            public void onResponse(Call<Access> call, Response<Access> response) {
                access = response.body();
                TwitchEvent twitchEvent = new TwitchEvent();
                twitchEvent.event = TwitchServiceEvent.GET_ACCESS;
                EventBus.getDefault().post(twitchEvent);
            }

            @Override
            public void onFailure(Call<Access> call, Throwable t) {

            }
        });
    }

    public void getM3U8links(String channel,String token, String sig) {
        m3u8 = null;
        HttpService.getUsherApi().getM3U8Links(channel,true,42,token,sig).enqueue(new Callback<String>() { // search the top uploads tab in view pager
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                m3u8 = response.body();
                TwitchEvent twitchEvent = new TwitchEvent();
                twitchEvent.event = TwitchServiceEvent.GET_M3U8;
                EventBus.getDefault().post(twitchEvent);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    // this is to getApi the current results

    public TopGame[] getCurrentTopGames() {
        return topGames;
    }

    public Stream[] getCurrentTopStreams() {
        return topStreams;
    }

    public Stream[] getTopSearchStreams() {
        return topSearchStreams;
    }

    public Video[] getTopArchiveStreams() {
        return topArchiveStreams;
    }

    public Video[] getTopUploadedStreams() {
        return topUploadedStreams;
    }

    public Access getAccess() {
        return access;
    }

    public String getM3u8() {
        return m3u8;
    }
}
