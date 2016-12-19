package com.example.amahler4096.projecttracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.text.ParseException;

/**
 * Created by amahler4096 on 10/26/2016.
 */
public class ProjectListActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProjectList projectList = ProjectList.get(getBaseContext());
    }

    @Override
    protected Fragment createFragment() {
        return new ProjectListFragment();
    }
}
