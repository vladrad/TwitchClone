package streamer.com.myapplication.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Vladi on 2/9/17.
 */

public class HttpService {
    public static final String BASE_URL = "https://api.twitch.tv/kraken/";
    public static final String USHER_URL = "http://usher.twitch.tv/";

    public static Api getApi() {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new HeaderInterceptor()).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Api.class);
    }

    public static Api getUsherApi() {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new UsherInterceptor()).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(USHER_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit.create(Api.class);
    }
}
