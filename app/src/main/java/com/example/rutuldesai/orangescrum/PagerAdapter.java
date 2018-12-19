package com.example.rutuldesai.orangescrum;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numberOfTabs;
    public PagerAdapter(FragmentManager fm,int numberOfTabs) {
        super(fm);
        this.numberOfTabs=numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                ProjectSummaryFragment ProjectSummary = new ProjectSummaryFragment();
                return ProjectSummary;
            case 1:
                RiskFragment RiskFragment = new RiskFragment();
                return RiskFragment;
            case 2:
                MilestoneFragment MilestoneFragment = new MilestoneFragment();
                return MilestoneFragment;
            case 3:
                ActivitiesFragment ActivitiesFragment = new ActivitiesFragment();
                return ActivitiesFragment;
            case 4:
                ProductivityFragment ProductivityFragment = new ProductivityFragment();
                return ProductivityFragment;
            case 5:
                CommunicationFragment CommunicationFragment = new CommunicationFragment();
                return CommunicationFragment;
            case 6:
                TestEnvironmentFragment TestEnvironmentFragment = new TestEnvironmentFragment();
                return TestEnvironmentFragment;
            default:
                ProjectSummary = new ProjectSummaryFragment();
                return ProjectSummary;
        }
        }
    @Override
    public int getCount() {
        return numberOfTabs;
    }
}

