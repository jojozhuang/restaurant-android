package johnny.portfolio.mobile.restaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        final EditText txtEmail = (EditText) findViewById(R.id.rgt_email);
        final EditText txtUserName = (EditText) findViewById(R.id.rgt_username);
        final EditText txtPassword = (EditText) findViewById(R.id.rgt_password);
        final Button btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String strEmail = txtEmail.getText().toString();
                    String strUserName = txtUserName.getText().toString();
                    String strPassword = txtPassword.getText().toString();

                    if(strEmail.equals("")||strUserName.equals("")||strPassword.equals("")){

                        showMSG("Please input Email, UserName and Password!");
                    }

                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("UserId", "0");
                    params.put("UserName", strUserName);
                    params.put("Email", strEmail);
                    params.put("Password", strPassword);

                    AsyncSign signUpTask = new AsyncSign();
                    signUpTask.params = params;
                    signUpTask.execute("http://10.0.2.2:8080/api/User/Register");

                } catch (Exception e) {
                    // response body is no valid JSON string
                }
            }
        });

    }

    protected void showMSG(String msg){
        Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_LONG).show();
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

    private class AsyncSign extends AsyncTask<String, Void, JSONObject> {

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
                        finish();
                    }
                }
            }
            catch (JSONException e) {

            }
        }

        protected void showMSG(String msg){
            Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_LONG).show();
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
