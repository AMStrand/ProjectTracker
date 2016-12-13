package com.example.amahler4096.projecttracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by Tomato on 12/13/2016.
 */

public class EditProjectFragment extends Fragment {

    private static final String ARG_PROJECT_ID = "projectID";

    public static EditProjectFragment EditInstance(UUID projectID) {

        Bundle args = new Bundle();

        args.putSerializable(ARG_PROJECT_ID, projectID);

        EditProjectFragment fragment = new EditProjectFragment();

        fragment.setArguments(args);

        return fragment;
    }

    public static EditProjectFragment NewInstance() {
        EditProjectFragment fragment = new EditProjectFragment();
        return  fragment;
    }

}
