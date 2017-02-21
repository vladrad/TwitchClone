# TwitchClone
This is a example of a Android app that uses the twitch api's to create the same flow as the official App.<br />
You can check out the stream here https://www.twitch.tv/vladmeerkat/videos/all

# Update #1 - Video + Chat 2/22/17
![alt tag](http://i.giphy.com/l44QqcHHJVz38ydz2.gif) <br />
I spent time yesterday sniffing traffic on Charles to see how video links are generated in the Twitch App.<br />
In order to get the current stream there are two http requests that you need to do: <br />
In Charles I noticed the Twitch app was doing a request to https://api.twitch.tv/api/channels/{channel}/access_token. 
This will return a couple of fields, a token (a giant json string), and a sig (String). <br />
![Alt text](http://i.imgur.com/PvxppJc.png “Video playlist”)<br />
This is where the confusion came in, I was looking for rtpm links but twitch uses m3u8 links. After looking at Charles for a bit I noticed calls to user.twitch.tv. After inspecting the curl I noticed that the token and sig were passed to api/channel/hls/{channel}.m3u8. This will generate a m3u8 playlist which you can use to stream the video. I grab the playlist and slip it by new line, I loop through the lines and take the first line that starts with http. This our video link.<br />
![Alt text](http://i.imgur.com/4pqMnii.png “Video playlist”)<br />

#Libraries Used
Retrofit - used for doing http requests<br />
Gson - google json parser<br />
EventBus - used to send events through out the app<br />
Picasso - used to load images in the app <br />

#Whats here?
The main navigation from the Twitch production app is there. You can view channels but you cannot click on them 
to launch the intent to show the stream and chat.<br />
![Alt text](http://i.imgur.com/E4gfxTk.jpg "Layouts in the app")<br />

#Whats happening?
The MainActivity was the pre set default navigation menu activity. It has a Navigation controller which holds
and listens for events to change fargments. <br />
Hitting Games loads the TopGamesFragment<br />
Hitting Channels loads the Stream Fragment<br />
Hitting and tapping on a game will load a ViewPager that holds a directory of three fragments: LiveStreams
fragment, PastFragment, UploadFragment<br />
Events are fired and sent to the navigation controller which will load the approriate fragment<br />
The TwitchService handles all the http requets and sends out events when they are done.<br />

#Whats missing?
A lot!!!!! Here are some obvious things... <br />
There is currently no error handling for the TwitchService!<br />
While landscape mode works, I am not checking if the fragments are already created. When the app is turned it 
restarts in game channel. Bassically a full restart.<br />
The whole video streaming is not in the app. I have worked with rtpm and rtsp at my old job at Netpulse. We
would host our videos on a local server in the gyms where we would stream videos to our android devices. <br />
Chat is missing... I was looking at the chat specs. I was searching for some IRC libraries to connect to it but 
started to lose steam. <br />


#Code Clean Up
Half way through the app I realized how many of the views were shared and a lot of the classes can be condensed.
<br />
Example: Both UploadAdapter and ArchiveAdapter are similar. Both Archive View and Upload View are practically
the same and can be condensed. <br />
I should probably use a gesture detector when doing the on click animations so the on click handler can work
better. Currently its all in the adapter. <br />
Unit tests would be awesome <br />
Sooooo much more ... <br />
