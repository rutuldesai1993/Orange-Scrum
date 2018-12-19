package com.example.rutuldesai.orangescrum;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toolbar;

public class ProjectDetailActivity extends AppCompatActivity {

    TextView ProjectDetailActionBarTitle;
    FrameLayout simpleFrameLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout_project_details);
        View view =getSupportActionBar().getCustomView();
        ImageButton filterButton = (ImageButton)view.findViewById(R.id.action_bar_filter);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ProjectDetailActionBarTitle=(TextView)findViewById(R.id.projectdetailactionbarTv);
        ProjectDetailActionBarTitle.setText("Project Detail");
        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectDetailActivity.this,ProjectListActivity.class);
                startActivity(intent);
            }
        });
        tabLayout=(TabLayout)findViewById(R.id.tabs);
        viewPager=(ViewPager)findViewById(R.id.viewPage);
        TabLayout.Tab ProjectSummary = tabLayout.newTab();
        ProjectSummary.setText("Project Summary");
        tabLayout.addTab(ProjectSummary);
        TabLayout.Tab Risk = tabLayout.newTab();
        Risk.setText("Risk");
        tabLayout.addTab(Risk);
        TabLayout.Tab Milestone = tabLayout.newTab();
        Milestone.setText("Milestone");
        tabLayout.addTab(Milestone);
        TabLayout.Tab Activities = tabLayout.newTab();
        Activities.setText("Activities");
        tabLayout.addTab(Activities);
        TabLayout.Tab Productivity = tabLayout.newTab();
        Productivity.setText("Productivity");
        tabLayout.addTab(Productivity);
        TabLayout.Tab Communication = tabLayout.newTab();
        Communication.setText("Communication");
        tabLayout.addTab(Communication);
        TabLayout.Tab TestEnvironment = tabLayout.newTab();
        TestEnvironment.setText("Test Environment");
        tabLayout.addTab(TestEnvironment);
        PagerAdapter pagerAdapter=new com.example.rutuldesai.orangescrum.PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        for(int i=0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(35, 0, 15, 0);
            tab.requestLayout();
        }
    }
}
