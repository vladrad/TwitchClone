package streamer.com.myapplication.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import streamer.com.myapplication.R;

/**
 * Created by Vladi on 2/20/17.
 */

public class ChatAdapter extends ListBaseAdapter<String> {

    public class ChatHolder {
        public TextView message;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatHolder chatHolder = null;
        if (convertView == null) {
            chatHolder = new ChatHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.chat_message, null);
            chatHolder.message = (TextView) convertView.findViewById(R.id.chat_text);
            convertView.setTag(chatHolder);
        } else {
            chatHolder = (ChatHolder) convertView.getTag();
        }
        String message = getItem(position); // getApi stream in list
        if(message != null)
            chatHolder.message.setText(message);

        return convertView;
    }


    public void addMessage(String message){
        getList().add(message);
    }
}
