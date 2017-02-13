package streamer.com.myapplication.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import streamer.com.myapplication.R;
import streamer.com.myapplication.controller.MainNavigationController;
import streamer.com.myapplication.http.models.games.TopGame;
import streamer.com.myapplication.views.adapters.touch.OnScaleTouchListener;

import static streamer.com.myapplication.controller.MainNavigationController.Navigation.GAME_DIRECTORY;

/**
 * Created by Vladi on 2/9/17.
 */

public class TopGameAdapter extends ListBaseAdapter<TopGame> {

    public class ViewHolder {
        public ImageView boxCover;
        public TextView boxName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.box_view, null);
            holder.boxCover = (ImageView) convertView.findViewById(R.id.box_image);
            holder.boxName = (TextView) convertView.findViewById(R.id.box_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final TopGame topGame = getItem(position); // grab game
        final View view = convertView;
        holder.boxName.setText(topGame.getGame().getName()); //set the game name
        Picasso.with(parent.getContext()).load(topGame.getGame().getBox().get("large")).into(holder.boxCover); //load the game in to image using large format
        view.setOnTouchListener(new OnScaleTouchListener(view, view.getContext()));//set zoom touch listener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainNavigationController.NavigationEvent navigationEvent = new MainNavigationController.NavigationEvent();
                navigationEvent.navigation = GAME_DIRECTORY;
                navigationEvent.game = topGame.getGame().getName();
                EventBus.getDefault().post(navigationEvent); // send navigation event
                view.setScaleY(1f);
                view.setScaleX(1f);
            }
        });
        return convertView;
    }
}
