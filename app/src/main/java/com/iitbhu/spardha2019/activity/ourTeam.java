package com.iitbhu.spardha2019.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.iitbhu.spardha2019.R;
import com.iitbhu.spardha2019.activity.OurTeamCard.DataContacts;
import com.iitbhu.spardha2019.activity.OurTeamCard.JSONParseteam;
import com.iitbhu.spardha2019.activity.OurTeamCard.adapter;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iitbhu.spardha2019.activity.OurTeamCard.adapter1;

import java.util.List;

public class ourTeam extends AppCompatActivity {
    public static final String JSON_URL = "https://spardha-17.firebaseio.com/conveners/.json?shallow=true'";
    public static final String JSON_URL1 = "https://spardha-17.firebaseio.com/team/";
    List<DataContacts> mDataset,mDataset1,mDataset2;
    SharedPreferences sharedpreferences;
    private RecyclerView mRecyclerView,mRecyclerView1,mRecyclerView2;
    private RecyclerView.Adapter mAdapter,mAdapter1,mAdapter2;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_team);
        mRecyclerView1 = (RecyclerView) findViewById(R.id.recycler_view_team_cat);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.recycler_view_team_con);

        GridLayoutManager gridlayoutManager = new GridLayoutManager(getBaseContext(), 1);
        GridLayoutManager gridlayoutManager1 = new GridLayoutManager(getBaseContext(), 1);
        GridLayoutManager gridlayoutManager2 = new GridLayoutManager(getBaseContext(), 1);
        mRecyclerView.setLayoutManager(gridlayoutManager);
        mRecyclerView1.setLayoutManager(gridlayoutManager1);
        mRecyclerView2.setLayoutManager(gridlayoutManager2);
        sharedpreferences = getSharedPreferences("dataourTeam", Context.MODE_PRIVATE);
        sendRequest2();
        sendRequest1();

        //sendRequest();


    }

    public void sendRequest2(){
        //final ProgressDialog pDialog = new ProgressDialog(this);
        //pDialog.setMessage("Loading...");
        //pDialog.show();

        //Log.e("mymsg", "sendRequest: "+JSON_URL1+str+".json?shallow=true'" );
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Teamactivity","workion"+response);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("response", response);
                        editor.commit();
                        JSONParseteam pj = new JSONParseteam();
                        pj.parseJSONteam(response);
                        Log.e("DATA_TEAM", "o"+response );
                        mDataset2 = pj.getData();

                        mAdapter2 = new adapter(mDataset2);
                        mRecyclerView2.setAdapter(mAdapter2);
                        //pDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Teamactivity","not workion");

                        Toast.makeText(ourTeam.this,"No Internet connection",Toast.LENGTH_LONG).show();
                        String response = sharedpreferences.getString("response", null);
                        if (response != null) {
                            JSONParseteam pj = new JSONParseteam();
                            pj.parseJSONteam(response);
                            mDataset2 = pj.getData();
                            mAdapter2 = new adapter(mDataset2);
                            mRecyclerView2.setAdapter(mAdapter2);
                        }
                        //pDialog.dismiss();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(ourTeam.this);
        requestQueue.add(stringRequest);

    }


    public void sendRequest(String str){
        //final ProgressDialog pDialog = new ProgressDialog(this);
        //pDialog.setMessage("Loading...");
        //pDialog.show();

        Log.e("mymsg", "sendRequest: "+JSON_URL1+str+".json?shallow=true'" );
        StringRequest stringRequest = new StringRequest(JSON_URL1+str+".json?shallow=true'",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Teamactivity","workion"+response);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("response", response);
                        editor.commit();
                        JSONParseteam pj = new JSONParseteam();
                        pj.parseJSONteam(response);
                        Log.e("DATA_TEAM", "o"+response );
                        mDataset = pj.getData();

                        mAdapter = new adapter(mDataset);
                        mRecyclerView1.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mRecyclerView.setAdapter(mAdapter);
                        //pDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Teamactivity","not workion");

                        Toast.makeText(ourTeam.this,"No Internet connection",Toast.LENGTH_LONG).show();
                        String response = sharedpreferences.getString("response", null);
                        if (response != null) {
                            JSONParseteam pj = new JSONParseteam();
                            pj.parseJSONteam(response);
                            mDataset = pj.getData();
                            mAdapter = new adapter(mDataset);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        //pDialog.dismiss();

                    }
                });

         RequestQueue requestQueue = Volley.newRequestQueue(ourTeam.this);
         requestQueue.add(stringRequest);

    }


    private void sendRequest1(){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Teamactivity","workion"+response);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("response", response);
                        editor.commit();
                        JSONParseteam pj = new JSONParseteam();
                        response = "[{\"contact\":9,\"name\":\"\",\"post\":\"GAMES AND SPORTS COUNCIL\",\"url\":\"\"},{\"contact\":9,\"name\":\"\",\"post\":\"PUBLICITY TEAM\",\"url\":\"\"},{\"contact\":9,\"name\":\"\",\"post\":\"PUBLIC RELATIONS TEAM\",\"url\":\"\"},{\"contact\":9,\"name\":\"\",\"post\":\"MARKETING TEAM\",\"url\":\"\"},{\"contact\":9,\"name\":\"\",\"post\":\"HOSPITALITY TEAM\",\"url\":\"\"},{\"contact\":9,\"name\":\"\",\"post\":\"EVENTS AND OPERATIONS TEAM\",\"url\":\"\"},{\"contact\":9,\"name\":\"\",\"post\":\"CONTENT WRITING TEAM\",\"url\":\"\"},{\"contact\":9,\"name\":\"\",\"post\":\"TECHNICAL TEAM\",\"url\":\"\"},{\"contact\":9,\"name\":\"\",\"post\":\"CREATIVE DESIGN TEAM\",\"url\":\"\"},{\"contact\":9,\"name\":\"\",\"post\":\"INFORMALS TEAM\",\"url\":\"\"}]";
                        pj.parseJSONteam(response);
                        mDataset1 = pj.getData();
                        mAdapter1 = new adapter1(mDataset1,ourTeam.this);
                        mRecyclerView1.setAdapter(mAdapter1);
                        pDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Teamactivity","not workion");

                        Toast.makeText(ourTeam.this,"No Internet connection",Toast.LENGTH_LONG).show();
                        String response = sharedpreferences.getString("response", null);
                        if (response != null) {
                            JSONParseteam pj = new JSONParseteam();
                            pj.parseJSONteam(response);
                            mDataset1 = pj.getData();
                            mAdapter1 = new adapter1(mDataset1,ourTeam.this);
                            mRecyclerView1.setAdapter(mAdapter1);
                        }
                        pDialog.dismiss();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
