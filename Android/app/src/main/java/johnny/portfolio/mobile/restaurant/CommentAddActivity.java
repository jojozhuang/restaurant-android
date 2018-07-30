package johnny.portfolio.mobile.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class CommentAddActivity extends Activity {
    //public interface CommentCallbacks {
        /**
         * Callback for when a new comment is submitted
         */
    //    public void onCommentChanged();
    //}

    //private CommentCallbacks commentCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_add);

        final int RestId = getIntent().getIntExtra("RestId", 1);
        final String UserName = getIntent().getStringExtra("UserName");

        final TextView lblUserName = (TextView) findViewById(R.id.lblUserName);
        final EditText txtComment = (EditText) findViewById(R.id.txtComment);
        final Button btnSubmit = (Button) findViewById(R.id.btnSubmitComment);
        final Button btnCancel = (Button) findViewById(R.id.btnCommentCancel);

        lblUserName.setText(UserName);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String strUserName = lblUserName.getText().toString();
                    String strComment = txtComment.getText().toString();

                    if(strComment.equals("")){

                        showMSG("Please input comments!");
                        return;
                    }

                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("RestId", String.valueOf(RestId));
                    params.put("UserName", strUserName);
                    params.put("Content", strComment);

                    AsyncSubmitComment submitTask = new AsyncSubmitComment();
                    submitTask.params = params;
                    submitTask.execute("http://10.0.2.2:8080/api/comment/create");

                } catch (Exception e) {
                    // response body is no valid JSON string
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void showMSG(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, RestaurantListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class AsyncSubmitComment extends AsyncTask<String, Void, JSONObject> {

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
                        setResult(RESULT_OK);
                        finish();
                    }
                    else {
                    }
                }
            }
            catch (JSONException e) {

            }
        }

        protected void showMSG(String msg){
            Toast.makeText(CommentAddActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }
}
