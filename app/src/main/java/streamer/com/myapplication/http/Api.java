package streamer.com.myapplication.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import streamer.com.myapplication.http.models.access.Access;
import streamer.com.myapplication.http.models.games.TopGames;
import streamer.com.myapplication.http.models.streams.TopStreams;
import streamer.com.myapplication.http.models.videos.Videos;

/**
 * Created by Vladi on 2/9/17.
 */

public interface Api {

    @GET("games/top?limit=20")
        //base url plus this and the client key
    Call<TopGames> getTopGames();

    @GET("streams?limit=25&offset=0&broadcaster_language=&on_site=1")
    Call<TopStreams> getChannels();

    @GET("streams?limit=25&hls=true&avc_profile=High&avc_level=4.1")
    Call<TopStreams> searchStreams(@Query("game") String game);

    @GET("videos/top?limit=25&offset=0&broadcast_type=archive,highlight")
    Call<Videos> searchPastBroadCasts(@Query("game") String game);

    @GET("videos/top?limit=25&offset=0&broadcast_type=upload")
    Call<Videos> searchUploaded(@Query("game") String game);

    @GET("/channels/{channel}/access_token")
    Call<Access> getAccessToken(@Path("channel") String channel);

    @GET("api/channel/hls/{channel}.m3u8")
    Call<String> getM3U8Links(@Path("channel") String channel,@Query("allow_spectre") boolean spectre, @Query("max_level") long level, @Query("token") String token, @Query("sig") String sig);

}
