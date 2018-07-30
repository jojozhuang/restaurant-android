package johnny.portfolio.mobile.restaurant;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import johnny.portfolio.mobile.restaurant.model.Restaurant;
import johnny.portfolio.mobile.restaurant.model.RestaurantList;

/**
 * A list fragment representing a list of Restaurants. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link RestaurantDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class RestaurantListFragment extends ListFragment
        implements RestaurantDetailFragment.DetailCallbacks{

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RestaurantListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: replace with a real list adapter.
        setListAdapter(new RestaurantAdapter(getActivity()));
        //setHasOptionsMenu(true);
     }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(RestaurantList.Restaurants.get(position).getName());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    // For Handset
    @Override
    public void onResume() {
        super.onResume();
        ((RestaurantAdapter) getListAdapter()).notifyDataSetChanged();
    }

    public void Refresh() {
        ((RestaurantAdapter) getListAdapter()).notifyDataSetChanged();
    }

    ///// Callback from WineDetailFragment. For two-pane layout

    @Override
    public void onItemChanged() {
        ((RestaurantAdapter) getListAdapter()).notifyDataSetChanged();
    }

    // Restaurant Adapter
    static class RestaurantAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Map<Restaurant.Category, Bitmap> icons;
        private Map<Integer, Bitmap> ratings;

        RestaurantAdapter(Context context) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            icons = new HashMap<Restaurant.Category, Bitmap>();

            for (Restaurant.Category category : Restaurant.Category.values()) {
                icons.put(category, BitmapFactory.decodeResource(context.getResources(),
                        Restaurant.getIconResource(category)));
            }

            ratings = new HashMap<Integer, Bitmap>();
            ratings.put(1, BitmapFactory.decodeResource(context.getResources(), R.drawable.rating1));
            ratings.put(2, BitmapFactory.decodeResource(context.getResources(), R.drawable.rating2));
            ratings.put(3, BitmapFactory.decodeResource(context.getResources(), R.drawable.rating3));
            ratings.put(4, BitmapFactory.decodeResource(context.getResources(), R.drawable.rating4));
            ratings.put(5, BitmapFactory.decodeResource(context.getResources(), R.drawable.rating5));
        }

        @Override
        public int getCount() {
            return RestaurantList.Restaurants.size();
        }

        @Override
        public Object getItem(int i) {
            return RestaurantList.Restaurants.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            View row = convertView;
            if (row == null) {
                row = inflater.inflate(R.layout.restaurant_list_item, parent, false);
                holder = new ViewHolder();
                holder.icon = (ImageView) row.findViewById(R.id.image);
                holder.name = (TextView) row.findViewById(R.id.text1);
                holder.location = (TextView) row.findViewById(R.id.location);
                holder.rating = (ImageView) row.findViewById(R.id.rating);
                holder.reviews = (TextView) row.findViewById(R.id.reviews);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            Restaurant restaurant = RestaurantList.Restaurants.get(position);
            holder.name.setText(restaurant.getName());
            holder.location.setText(restaurant.getLocation());
            holder.icon.setImageBitmap(icons.get(restaurant.getType()));
            int ratingvalue = (int)(Math.round(restaurant.getRating()));
            holder.rating.setImageBitmap(ratings.get(ratingvalue));
            holder.reviews.setText(restaurant.getReviewText());
            return row;
        }

        static class ViewHolder {
            TextView name;
            TextView location;
            ImageView rating;
            TextView reviews;
            ImageView icon;
        }

    }

}
