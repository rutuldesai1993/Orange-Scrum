package com.example.rutuldesai.orangescrum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

public class MyDashBoardFragment extends Fragment {
    Button btnViewProjects;
    private static final String PROJECTLIST_URL = "http://203.88.143.51:880/Orangescrum/api/v2.0/getProjectsList";
    private RecyclerView projectdashboardList;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    MySharedPreference preference;
    private RecyclerView.Adapter adapter;
    public static MyDashBoardFragment newInstance() {
        MyDashBoardFragment fragment = new MyDashBoardFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_dashboard, container, false);
        btnViewProjects=(Button) v.findViewById(R.id.btnViewProject);
        btnViewProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProjectListActivity.class);
                startActivity(intent);
            }
        });
        projectdashboardList = (RecyclerView)v.findViewById(R.id.projectDashBoardListView);
        preference = MySharedPreference.getInstance(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration = new DividerItemDecoration(projectdashboardList.getContext(), linearLayoutManager.getOrientation());
        getData();
        projectdashboardList.setHasFixedSize(true);
        projectdashboardList.setLayoutManager(linearLayoutManager);
        projectdashboardList.addItemDecoration(dividerItemDecoration);
        return  v;

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

                    adapter = new ProjectAdapter(getActivity().getApplicationContext(), projectList);
                    projectdashboardList.setAdapter(adapter);

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

        Volley.newRequestQueue(getActivity()).add(request);
    }
}
