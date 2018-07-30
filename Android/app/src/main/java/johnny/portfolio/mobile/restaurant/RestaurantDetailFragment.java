package johnny.portfolio.mobile.restaurant;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import johnny.portfolio.mobile.restaurant.model.Comment;
import johnny.portfolio.mobile.restaurant.model.CommentList;
import johnny.portfolio.mobile.restaurant.model.Restaurant;
import johnny.portfolio.mobile.restaurant.model.RestaurantList;

/**
 * A fragment representing a single Restaurant detail screen.
 * This fragment is either contained in a {@link RestaurantListActivity}
 * in two-pane mode (on tablets) or a {@link RestaurantDetailActivity}
 * on handsets.
 */
public class RestaurantDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Restaurant restaurant;
    private float restrating = 0.0f;
    private static final int INTENT_COMMENT = 100;

    public interface DetailCallbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemChanged();
    }

    private DetailCallbacks mCallbacks;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RestaurantDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            restaurant = RestaurantList.Restaurant_Map.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (restaurant != null) {
            TextView name = (TextView) rootView.findViewById(R.id.text1);
            TextView location = (TextView) rootView.findViewById(R.id.location);
            ImageView icon = (ImageView) rootView.findViewById(R.id.image);
            RatingBar rating = (RatingBar) rootView.findViewById(R.id.rating);
            TextView reviews = (TextView) rootView.findViewById(R.id.reviews);
            Button btnGotoComment = (Button) rootView.findViewById(R.id.btnAddComment);

            name.setText(restaurant.getName());
            location.setText(restaurant.getLocation());
            icon.setImageResource(Restaurant.getIconResource(restaurant.getType()));
            rating.setRating(restaurant.getRating());
            reviews.setText(restaurant.getLongReviewText());
            rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    try {
                        restrating = v;
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("RestId", String.valueOf(restaurant.getId()));
                        params.put("Rating", String.valueOf((int) v));

                        AsyncSetRating setRatingTask = new AsyncSetRating();
                        setRatingTask.params = params;
                        setRatingTask.execute("http://10.0.2.2:8080/api/Restaurant/SetRate");

                    } catch (Exception e) {
                        // response body is no valid JSON string
                    }

                    /*
                    restaurant.setRating(v);
                    if (mCallbacks != null) {
                        mCallbacks.onItemChanged();
                    }*/
                }
            });

            btnGotoComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //SignInActivity.UserName = "Johnny";
                    if (SignInActivity.UserName.equals("")) {
                        Toast.makeText(getActivity(), "Please login first!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent commentIntent = new Intent(getActivity(), CommentAddActivity.class);
                        commentIntent.putExtra("RestId", restaurant.getId());
                        commentIntent.putExtra("UserName", SignInActivity.UserName);
                        startActivityForResult(commentIntent, INTENT_COMMENT);
                    }
                }
            });

            // show The Image
            if (restaurant.getImage1().length() != 0) {
                new DownloadImageTask((ImageView) rootView.findViewById(R.id.image1), (TextView) rootView.findViewById(R.id.download1))
                        .execute(restaurant.getImage1());
            }
            // show The Image
            if (restaurant.getImage2().length() != 0) {
                new DownloadImageTask((ImageView) rootView.findViewById(R.id.image2), (TextView) rootView.findViewById(R.id.download2))
                        .execute(restaurant.getImage2());
            }
            // show The Image
            if (restaurant.getImage3().length() != 0) {
                new DownloadImageTask((ImageView) rootView.findViewById(R.id.image3), (TextView) rootView.findViewById(R.id.download3))
                        .execute(restaurant.getImage3());
            }

            try {
                Log.d("Get comment", "restid=" + restaurant.getId());
                new AsyncCommentList().execute("http://10.0.2.2:8080/api/comment/GetListByRestaurant?restid="+restaurant.getId());
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                Log.d("Exception Get comment", "message=" + e1.getMessage());
            }
        }

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == INTENT_COMMENT ) {
            if (resultCode == getActivity().RESULT_OK) {
                try {
                    Log.d("Get comment", "restid=" + restaurant.getId());
                    new AsyncCommentList().execute("http://10.0.2.2:8080/api/comment/GetListByRestaurant?restid="+restaurant.getId());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    Log.d("Exception Get comment", "message=" + e1.getMessage());
                }
            }
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        TextView txtDownload;

        public DownloadImageTask(ImageView bmImage, TextView txtDown) {
            this.bmImage = bmImage;
            this.txtDownload = txtDown;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            txtDownload.setVisibility(View.GONE);
            bmImage.setVisibility(View.VISIBLE);
            bmImage.setImageBitmap(result);
        }
    }

    private class AsyncCommentList extends AsyncTask<String, Void, JSONArray> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //your code
        }
        @Override
        protected JSONArray doInBackground(String... params) {
            JSONArray object = HttpHelper.GetList(params[0]);
            return object;
        }

        @Override
        protected void onPostExecute(JSONArray result) {

            Log.d("onPostExecute", "onPostExecute");
            try {
                super.onPostExecute(result);
                if (result!=null) {
                    ArrayList<Comment> list = new ArrayList<Comment>();
                    JSONArray jsonArray = result;
                    if (jsonArray != null) {
                        int len = jsonArray.length();
                        JSONObject jsonObj = null;
                        for (int i=0;i<len;i++){
                            jsonObj = (JSONObject)jsonArray.get(i);
                            if (jsonObj != null) {
                                list.add(
                                    new Comment(jsonObj.getInt("Id"),
                                            jsonObj.getString("Content"),
                                            jsonObj.getInt("RestId"),
                                            jsonObj.getString("RestName"),
                                            jsonObj.getInt("UserId"),
                                            jsonObj.getString("UserName")));
                            }

                        }
                    }
                    Log.d("onPostExecute activity", "list=" + list.size());
                    CommentList.updateList(list);
                    CommentListFragment fragComment = (CommentListFragment) getChildFragmentManager().findFragmentById(R.id.comment_list);
                    if (fragComment!=null) {
                        fragComment.Refresh();
                    }
                }
            }
            catch (JSONException e) {
                int i = 1;
                i = 2;
                Log.d("JSONException", "message=" + e.getMessage());
            }
            catch (Exception e) {
                int i = 1;
                i = 2;
                Log.d("Exception", "message=" + e.getMessage());
            }
        }

    }

    private class AsyncSetRating extends AsyncTask<String, Void, JSONObject> {

        private Exception exception;
        public HashMap<String, String> params = new HashMap<String, String>();

        protected JSONObject doInBackground(String... urls) {
            try {
                JSONObject retJson = HttpHelper.Post(urls[0], params);
                return retJson;
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(JSONObject feed) {
            // TODO: check this.exception
            // TODO: do something with the feed
            restaurant.setRating(restrating);
            if (mCallbacks != null) {
                mCallbacks.onItemChanged();
            }

        }

        protected void showMSG(String msg){
            //Toast.makeText(SignInActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        FragmentManager fragmentManager = activity.getFragmentManager();
        Fragment restaurantListFragment	= fragmentManager.findFragmentById(R.id.restaurant_list);
        if (restaurantListFragment instanceof DetailCallbacks) {
            mCallbacks = (DetailCallbacks) restaurantListFragment;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mCallbacks = null;
    }


}
