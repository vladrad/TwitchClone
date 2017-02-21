package streamer.com.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jibble.pircbot.PircBot;


import java.io.IOException;

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
    private ChatClient chatClient;
    private ListView chatView;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        chatView = (ListView) findViewById(R.id.chat_list); //grab chat list
        chatAdapter = new ChatAdapter(); // do adapter
        chatView.setAdapter(chatAdapter); // set adapter
        videoStream = (VideoView) findViewById(R.id.video_stream);
        videoController = new MediaController(this); //create new controller
        TwitchService.get().getAccessToken(CurrentStream.get().currentChannel); //grab a token for the current channel
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(CurrentStream.get().currentChannel.toUpperCase());
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        setUpChat();
    }

    @Override
    public void onStop() {
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
        if (twitchEvent.event.equals(GET_ACCESS)) { // getApi token
            String token = TwitchService.get().getAccess().getToken();
            String sig = TwitchService.get().getAccess().getSig();
            String channel = CurrentStream.get().currentChannel;
            TwitchService.get().getM3U8links(channel, token, sig); //getApi m3u8 links
        }
        if (twitchEvent.event.equals(GET_M3U8)) { // got playlist now process
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    public void setUpChat() {
        chatThread = new Thread(new Runnable() {
            @Override
            public void run() {
                chatClient = new ChatClient();
                // Enable debugging output.
                chatClient.setVerbose(true);
                // Connect to the IRC server.
                try {
                    chatClient.connect("irc.chat.twitch.tv", 6667, "oauth:kd1m645aizep2kavciwe4b5mrz2tgn"); //pre generated auth token from twitch app, and I knew the chat server
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (org.jibble.pircbot.IrcException e) {
                    e.printStackTrace();
                }
                // Join the #pircbot channel.
                chatClient.joinChannel("#" + CurrentStream.get().currentChannel); //time to join our channel
            }
        });
        chatThread.start();

    }

    public class ChatClient extends PircBot {

        public ChatClient() {
            this.setName("Vladmeerkat"); //this would be my nick name
        }

        @Override
        public void onMessage(String channel, final String sender,
                              String login, String hostname, final String message) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    chatAdapter.addMessage(sender + ": " + message); // set the message
                    chatAdapter.notifyDataSetChanged();// notify data set is changed
                    chatView.setSelection(chatAdapter.getCount() - 1); //keep the scroll going to the bottom
                }
            });
        }
    }


}
