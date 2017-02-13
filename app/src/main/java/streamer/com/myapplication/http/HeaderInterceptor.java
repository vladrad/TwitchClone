package streamer.com.myapplication.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import streamer.com.myapplication.BuildConfig;

/**
 * Created by Vladi on 2/12/17.
 */

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException { //going to intercept the http call
        Request originalRequest = chain.request();

        Request newRequest = originalRequest.newBuilder() //going to add header + client id
                .header("Accept", "application/vnd.twitchtv.v5+json")
                .header("Client-ID", BuildConfig.TWITCH_TOKEN)
                .build();

        return chain.proceed(newRequest);
    }
}



