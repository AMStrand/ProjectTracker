package com.example.amahler4096.projecttracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class ProjectPagerActivity extends AppCompatActivity {
        // Variable to hold the extra string to id the project id value:
    private static String EXTRA_PROJECT_ID = "edu.kvcc.cis298.ProjectTracker.project_id";

    private ViewPager mViewPager;
    private List<Project> mProjects;

    public static Intent newIntent(Context packageContext, UUID projectId) {
            // Create a new intent using the ProjectPagerActivity class:
        Intent intent = new Intent(packageContext, ProjectPagerActivity.class);
            // Add the id as an extra:
        intent.putExtra(EXTRA_PROJECT_ID, projectId);
            // Return the intent:
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            // Set the view to the project pager activity view:
        setContentView(R.layout.activity_project_pager);
            // Get the project id:
        final UUID projectId = (UUID) getIntent().getSerializableExtra(EXTRA_PROJECT_ID);
            // Wire up the view pager:
        mViewPager = (ViewPager) findViewById(R.id.activity_project_pager_view_pager);
            // Get a local instance of the project list:
        mProjects = ProjectList.get(this).getProjects();
            // Get a local instance of the fragment manager:
        FragmentManager fragmentManager = getSupportFragmentManager();
            // Set the adapter:
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {

                Project project = mProjects.get(position);
                return ProjectFragment.newInstance(project.getmProjectIDTag());
            }

            @Override
            public int getCount() {
                return mProjects.size();
            }
        });

            // Loop through the project items to find the match by id:
        for (int i = 0; i < mProjects.size(); i++) {
                // If the ids match:
            if (mProjects.get(i).getmProjectIDTag().equals(projectId)) {
                    // Set the current item to the current project and break out of the loop:
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
