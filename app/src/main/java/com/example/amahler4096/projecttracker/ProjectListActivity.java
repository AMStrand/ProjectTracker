package com.example.amahler4096.projecttracker;

import android.support.v4.app.Fragment;

/**
 * Created by amahler4096 on 10/26/2016.
 */
public class ProjectListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ProjectListFragment();
    }
}
