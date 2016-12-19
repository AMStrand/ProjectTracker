package com.example.amahler4096.projecttracker;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class ProjectActivity extends SingleFragmentActivity {

    private static final String EXTRA_PROJECT_ID = "Again, We need a long unique string to stick the project ID to.";

    public static Intent newIntent(Context packageContext, UUID projectID) {
        Intent intent = new Intent(packageContext, ProjectActivity.class);
        intent.putExtra(EXTRA_PROJECT_ID, projectID);
        return intent;
    }

    protected Fragment createFragment() {
        UUID projectID= (UUID) getIntent().getSerializableExtra(EXTRA_PROJECT_ID);
        return ProjectFragment.newInstance(projectID);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
