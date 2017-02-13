# TwitchClone
This is a example of a Android app that uses the twitch api's to create the same flow as the offical App.<br />
You can check out the stream here https://www.twitch.tv/vladmeerkat/videos/all

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
A lot!!!!! <br />
There is currently no error handling for the TwitchService!<br />
I am not tracking <br />

