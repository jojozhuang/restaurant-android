package johnny.portfolio.mobile.restaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import johnny.portfolio.mobile.restaurant.model.Restaurant;
import johnny.portfolio.mobile.restaurant.model.RestaurantList;


/**
 * An activity representing a list of Restaurants. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RestaurantDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link RestaurantListFragment} and the item details
 * (if present) is a {@link RestaurantDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link RestaurantListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class RestaurantListActivity extends AppCompatActivity
        implements RestaurantListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_restaurant_list);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (findViewById(R.id.restaurant_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((RestaurantListFragment) getFragmentManager()
                    .findFragmentById(R.id.restaurant_list))
                    .setActivateOnItemClick(true);
        }

        try {
            new AsyncList().execute("http://10.0.2.2:8080/api/Restaurant/GetAll");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link RestaurantListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(RestaurantDetailFragment.ARG_ITEM_ID, id);
            RestaurantDetailFragment fragment = new RestaurantDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.restaurant_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, RestaurantDetailActivity.class);
            detailIntent.putExtra(RestaurantDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                //searchItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!searchView.isIconified() && TextUtils.isEmpty(newText)) {
                    callSearch(newText);
                }
                return false;
            }

            public void callSearch(String query) {
                try {
                    Log.d("callSearch", "query=" + query);
                    if (query == null || query.equals("")) {
                        new AsyncList().execute("http://10.0.2.2:8080/api/Restaurant/GetAll");
                    } else {
                        new AsyncList().execute("http://10.0.2.2:8080/api/Restaurant/GetAll?keyword=" + query);
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    Log.d("Exception in callSearch", "message=" + e1.getMessage());
                }
                //RestaurantList.Search(query);
                //RestaurantListFragment fragment = (RestaurantListFragment) getFragmentManager().findFragmentById(R.id.restaurant_list);
                //fragment.Refresh();
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_signin:
                // User chose the "Favorite" action, mark the current item
                // as a favorite....
                Intent loginIntent = new Intent(this, SignInActivity.class);
                //loginIntent.putExtra(RestaurantDetailFragment.ARG_ITEM_ID, id);
                startActivity(loginIntent);
                return true;
            case R.id.action_logout:
                try{
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("UserName", SignInActivity.UserName);

                    AsyncLogout logoutTask = new AsyncLogout();
                    logoutTask.params = params;
                    logoutTask.execute("http://10.0.2.2:8080/api/User/Logout");

                } catch (Exception e) {
                    // response body is no valid JSON string
                }
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private class AsyncList extends AsyncTask<String, Void, JSONArray> {
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
                    ArrayList<Restaurant> list = new ArrayList<Restaurant>();
                    JSONArray jsonArray = result;
                    if (jsonArray != null) {
                        int len = jsonArray.length();
                        JSONObject jsonObj = null;
                        for (int i=0;i<len;i++){
                            jsonObj = (JSONObject)jsonArray.get(i);
                            if (jsonObj != null) {
                                list.add(
                                    new Restaurant(jsonObj.getInt("Id"),
                                            jsonObj.getString("Name"),
                                            Restaurant.getCategoryByNumber(jsonObj.getInt("Category")),
                                            jsonObj.getString("Location"),
                                            Float.parseFloat(jsonObj.getString("Rating")),
                                            jsonObj.getInt("Reviews"),
                                            "",
                                            jsonObj.getString("Image1"),
                                            jsonObj.getString("Image2"),
                                            jsonObj.getString("Image3")));
                            }

                        }
                    }
                    Log.d("onPostExecute activity", "list=" + list.size());
                    RestaurantList.updateList(list);
                    RestaurantListFragment fragment = (RestaurantListFragment) getFragmentManager().findFragmentById(R.id.restaurant_list);
                    fragment.Refresh();
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

    private class AsyncLogout extends AsyncTask<String, Void, JSONObject> {

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
            try {
                if (feed!=null) {
                    showMSG(feed.getString("Message"));
                    if (feed.getString("RetCode").equals("0")){
                        SignInActivity.UserName = "";
                    }
                }
            }
            catch (JSONException e) {

            }
        }

        protected void showMSG(String msg){
            Toast.makeText(RestaurantListActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }

    public void email(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "jojozhuang@gmail.com" } );
        intent.putExtra(Intent.EXTRA_SUBJECT, "Email from Implicit Intent Demo");
        intent.putExtra(Intent.EXTRA_TEXT, "-- Sent by my Android App");
        startActivity(intent);
    }
}
