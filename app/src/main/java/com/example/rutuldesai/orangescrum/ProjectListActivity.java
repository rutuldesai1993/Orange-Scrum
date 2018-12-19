package com.example.rutuldesai.orangescrum;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProjectListActivity extends AppCompatActivity {
    private static final String PROJECTLIST_URL = "http://203.88.143.51:880/Orangescrum/api/v2.0/getProjectsList";
    private RecyclerView pList;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    MySharedPreference preference;
    SearchView searchView;
    private TextView ProjectListActionBarTitle;
    private RecyclerView.Adapter adapter;
    public static final String PREFS_NAME = "AUTH_PREFS";
    public static final String PREFS_KEY = "AUTH_PREFS_String";
    Context context;
    SharedPreferences sp;
    String AUTH_TOKEN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_list_layout);
        pList = findViewById(R.id.projectListView);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout_project_list);
        View view =getSupportActionBar().getCustomView();
        ProjectListActionBarTitle=(TextView)findViewById(R.id.projectlistactionbarTv);
        ProjectListActionBarTitle.setText("My Projects");
        ImageButton filterButton = (ImageButton)view.findViewById(R.id.action_bar_filter);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        preference = MySharedPreference.getInstance(this);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(pList.getContext(), linearLayoutManager.getOrientation());
        getData();

        pList.setHasFixedSize(true);
        pList.setLayoutManager(linearLayoutManager);
        pList.addItemDecoration(dividerItemDecoration);



    }

    private void getData() {


            StringRequest request = new StringRequest(Request.Method.POST,
                    PROJECTLIST_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    List<ProjectModel> projectList = new ArrayList<>();

                    System.out.println(response);

                    try {
                        JSONObject object = new JSONObject(response);
                        JSONObject results = object.getJSONObject("results");
                        JSONArray projects = results.getJSONArray("projects");

                        for(int i=0;i<projects.length();i++)
                        {
                            JSONObject obj = projects.getJSONObject(i);
                            String id = obj.getString("id");
                            String name = obj.getString("name");
                            projectList.add(new ProjectModel(id,name));

                        }

                        for(ProjectModel m : projectList)
                        {
                            System.out.println(m);
                        }

                        adapter = new ProjectAdapter(getApplicationContext(), projectList);
                        pList.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {

                @Override
                public byte[] getBody() throws AuthFailureError {

                    String Auth_Token = preference.getAuthToken();
                    System.out.println(Auth_Token);
                    StringBuilder builder = new StringBuilder();
                    builder.append("{\"auth_token\":\"");
                    builder.append(Auth_Token+ "\"}");

                    String AUTHTOKEN_JSON = builder.toString();

                    System.out.println(AUTHTOKEN_JSON);

                    return AUTHTOKEN_JSON.getBytes();

                }
            };

            Volley.newRequestQueue(ProjectListActivity.this).add(request);
        }
    }