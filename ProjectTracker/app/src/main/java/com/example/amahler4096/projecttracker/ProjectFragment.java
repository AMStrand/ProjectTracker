package com.example.amahler4096.projecttracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.UUID;

/**
 * Created by amahler4096 on 10/26/2016.
 */
public class ProjectFragment extends Fragment {

    private static final String ARG_PROJECT_ID = "projectID";

    private Project mProject;
    private Button mEditProjectButton;



    public static ProjectFragment newInstance(UUID projectID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROJECT_ID, projectID);

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID projectID = (UUID) getArguments().getSerializable(ARG_PROJECT_ID);

        mProject= ProjectList.get(getActivity()).getProject(projectID);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_project, container, false);

        mEditProjectButton.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
              Intent intent = EditProjectActivity.EditIntent(getActivity(),mProject.getmProjectIDTag());
              startActivity(intent);
          }
        });

        return view;
    }


}
