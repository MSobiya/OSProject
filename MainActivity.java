package com.example.sobiya.tablelayout;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String MY_JSON ="MY_JSON";

    private static final String JSON_URL = "http://192.168.0.100/Android_OS_Project/mysql_connect_retrieve.php";
    //private static final String JSON_URL = "http://api.learn2crack.com/android/json/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getJSON(JSON_URL);


    }

    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait...",null,true,true);
            }

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loading.dismiss();

                try {
                    JSONArray jArray = new JSONArray(result);
                    for (int i = 0; i < jArray.length() ; i++) {
                        TableLayout tb = (TableLayout) findViewById(R.id.tb);
                        //tb.removeAllViewsInLayout();
                        TableRow tr = new TableRow(MainActivity.this);
                        JSONObject json_data = jArray.getJSONObject(i);
                        Log.i("log_tag", "id: " + json_data.getInt("id") + ", ip: " + json_data.getString("ip") + ", pc_no: " + json_data.getInt("pc_no") + ", status: " + json_data.getString("status") + ", last_update: " + json_data.getString("last_update"));

                        String sid = String.valueOf(json_data.getInt("id"));
                        TextView tv0 = new TextView(MainActivity.this);
                        tv0.setText(sid);
                        tv0.setPadding(20, 0, 0, 0);
                        tr.addView(tv0);

                        String sip = String.valueOf(json_data.getString("ip"));
                        TextView tv1 = new TextView(MainActivity.this);
                        tv1.setText(sip);
                        tv1.setPadding(20, 0, 0, 0);
                        tr.addView(tv1);


                        String spc_no = String.valueOf(json_data.getInt("pc_no"));
                        TextView tv2 = new TextView(MainActivity.this);
                        tv2.setText(spc_no);
                        tv2.setPadding(20, 0, 0, 0);
                        tr.addView(tv2);


                        String sstaus = String.valueOf(json_data.getString("status"));
                        TextView tv3 = new TextView(MainActivity.this);
                        tv3.setText(sstaus);
                        tv3.setPadding(20, 0, 0, 0);
                        tr.addView(tv3);

                        String slast_update = String.valueOf(json_data.getString("last_update"));
                        TextView tv4 = new TextView(MainActivity.this);
                        tv4.setText(slast_update);
                        tv4.setPadding(20, 0, 0, 0);
                        tr.addView(tv4);

                        tb.addView(tr);

                    }


                } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data" + e.toString());
                    Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
                }

            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);
    }
}
