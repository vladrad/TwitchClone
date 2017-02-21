package streamer.com.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jibble.pircbot.PircBot;


import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import streamer.com.myapplication.models.CurrentStream;
import streamer.com.myapplication.services.TwitchService;
import streamer.com.myapplication.tools.M3U8Parse;
import streamer.com.myapplication.views.adapters.ChatAdapter;

import static streamer.com.myapplication.services.TwitchService.TwitchServiceEvent.GET_ACCESS;
import static streamer.com.myapplication.services.TwitchService.TwitchServiceEvent.GET_M3U8;

public class StreamActivity extends AppCompatActivity {

    private VideoView videoStream;
    private MediaController videoController;
    private Thread chatThread;
    private MyBot chatClient;
    private ListView chatView;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        chatView = (ListView) findViewById(R.id.chat_list);
        chatAdapter = new ChatAdapter();
        chatView.setAdapter(chatAdapter);
        videoStream = (VideoView) findViewById(R.id.video_stream);
        videoController = new MediaController(this);
        TwitchService.get().getAccessToken(CurrentStream.get().currentChannel);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart(){
        super.onStart();
        setUpChat();
    }

    @Override
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
        try {
            chatClient.disconnect();
            chatThread.join();
            chatThread = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TwitchService.TwitchEvent twitchEvent) { // grab access for token
        if(twitchEvent.event.equals(GET_ACCESS)){ // getApi token
            String token = TwitchService.get().getAccess().getToken();
            String sig = TwitchService.get().getAccess().getSig();
            String channel = CurrentStream.get().currentChannel;
            TwitchService.get().getM3U8links(channel,token,sig); //getApi m3u8 links
        }
        if(twitchEvent.event.equals(GET_M3U8)){ // got playlist now process
            videoStream.setMediaController(videoController);
            final String streamLink = M3U8Parse.getFirstLink(TwitchService.get().getM3u8());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    videoStream.setVideoURI(Uri.parse(streamLink));
                    videoStream.start();
                }
            });
        }
    }


    public void setUpChat(){
        chatThread = new Thread(new Runnable() {
            @Override
            public void run() {
                chatClient = new MyBot();
                // Enable debugging output.
                chatClient.setVerbose(true);
                // Connect to the IRC server.
                try {
                    chatClient.connect("irc.chat.twitch.tv",6667,"oauth:kd1m645aizep2kavciwe4b5mrz2tgn"); //pre generated auth token
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (org.jibble.pircbot.IrcException e) {
                    e.printStackTrace();
                }
                // Join the #pircbot channel.
                chatClient.joinChannel("#"+CurrentStream.get().currentChannel);
            }
        });

        chatThread.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatAdapter.notifyDataSetChanged();
                    }
                });
            }
        },0,1000);

    }


    public class MyBot extends PircBot {

        public MyBot() {
            this.setName("Vladmeerkat");
        }
        @Override
        public void onMessage(String channel, final String sender,
                              String login, String hostname, final String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatAdapter.addMessage(sender +": " + message);
                        chatAdapter.notifyDataSetChanged();
                        chatView.setSelection(chatAdapter.getCount() - 1);
                    }
                });
        }

    }




}
