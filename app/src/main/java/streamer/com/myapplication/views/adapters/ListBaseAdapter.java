package streamer.com.myapplication.views.adapters;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladi on 2/9/17.
 */

public abstract class ListBaseAdapter<T> extends BaseAdapter { //pass any class to list based on the <CLASS> in the extension deceleration

    List<T> list = new ArrayList<>();

    public ListBaseAdapter() {
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return list;
    }

    public void addItem(T item) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(item);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}