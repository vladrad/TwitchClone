package streamer.com.myapplication.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import streamer.com.myapplication.BuildConfig;

/**
 * Created by Vladi on 2/12/17.
 */

public class UsherInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException { //going to intercept the http call
        Request originalRequest = chain.request();

        Request newRequest = originalRequest.newBuilder() //found via darvin traffic sniffing
                .header("Content-Type", "application/vnd.apple.mpegurl") //
                .header("Remote Address", "usher.twitch.tv/23.160.0.254")
                .build();

        return chain.proceed(newRequest);
    }

}



