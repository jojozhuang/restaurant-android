package johnny.portfolio.mobile.restaurant;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Johnny on 11/5/15.
 */
public class HttpHelper {
    public static JSONObject Get(String url) {
        HttpURLConnection urlConnection = null;
        JSONObject jsonObject = null;
        try {
            // create connection
            URL urlToRequest = new URL(url);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();
            urlConnection.setConnectTimeout(30 * 1000);
            urlConnection.setReadTimeout(30 * 1000);

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            jsonObject = new JSONObject(getResponseText(in));

        } catch (MalformedURLException e) {
            int a = 1;
            // URL is invalid
        } catch (SocketTimeoutException e) {
            int a = 1;
            // data retrieval or connection timed out
        } catch (IOException e) {
            int a = 1;
            // could not read response body
            // (could not create input stream)
        } catch (JSONException e) {
            int a = 1;
            // response body is no valid JSON string

        } catch (Exception e) {
            int a = 1;
            a = 2;
            // response body is no valid JSON string
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return jsonObject;
    }

    public static JSONObject Post (String requestURL,
                                   HashMap<String, String> postDataParams) {


        URL url;
        String response = "";
        HttpURLConnection conn = null;
        JSONObject jsonObject = null;
        try {
            url = new URL(requestURL);
            Log.d("HTTP Post", "requestURL=" + requestURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            String param = getPostDataString(postDataParams);
            Log.d("HTTP Post", "param=" + param);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(param);

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";

            }

            jsonObject = new JSONObject(response);
        } catch (MalformedURLException e) {
            // URL is invalid
        } catch (SocketTimeoutException e) {
            System.out.println(e);
            // data retrieval or connection timed out
        } catch (IOException e) {
            System.out.println(e);
            // could not read response body
            // (could not create input stream)
        } catch (JSONException e) {
            // response body is no valid JSON string
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return jsonObject;
    }

    public static JSONArray GetList(String url) {
        HttpURLConnection urlConnection = null;
        JSONArray jsonArray = null;
        try {
            Log.d("GetList in httphelper", "url=" + url);
            // create connection
            URL urlToRequest = new URL(url);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();
            urlConnection.setConnectTimeout(30 * 1000);
            urlConnection.setReadTimeout(30 * 1000);

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            String jsonString = getResponseText(in);
            jsonArray = new JSONArray(jsonString);
            //if (jsonString.startsWith("[")) {
                // We have a JSONArray
                //jsonArray = new JSONArray(jsonString);

            //}


        } catch (MalformedURLException e) {
            int a = 1;
            // URL is invalid
        } catch (SocketTimeoutException e) {
            int a = 1;
            // data retrieval or connection timed out
        } catch (IOException e) {
            int a = 1;
            // could not read response body
            // (could not create input stream)
        } catch (JSONException e) {
            int a = 1;
            a= 2;
            // response body is no valid JSON string

        } catch (Exception e) {
            int a = 1;
            a = 2;
            // response body is no valid JSON string
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return jsonArray;
    }

    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
