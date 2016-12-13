package com.example.amahler4096.projecttracker;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class EditProjectActivity extends SingleFragmentActivity {

    private static final String EXTRA_PROJECT_UUID = "this is the uuid for the specific project we want to edit";

    public static Intent newProjectIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, EditProjectActivity.class);
        return intent;
    }

    public static Intent EditIntent(Context packageContext, UUID projectID) {
        Intent intent = new Intent(packageContext, EditProjectActivity.class);
        intent.putExtra(EXTRA_PROJECT_UUID, projectID);
        return intent;

    }

    @Override
    protected Fragment createFragment() {
        if(getIntent().getSerializableExtra(EXTRA_PROJECT_UUID) != null)
        {
            UUID projectID = (UUID) getIntent().getSerializableExtra(EXTRA_PROJECT_UUID);
            return EditProjectFragment.EditInstance(projectID);
        }
        else
        {
            return EditProjectFragment.NewInstance();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);



    }
}
