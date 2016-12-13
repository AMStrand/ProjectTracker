package com.example.amahler4096.projecttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by amahler4096 on 10/26/2016.
 */
public class ProjectListFragment extends Fragment {

    private Button mNewProjectButton;

    private Button mViewCalendarButton;

    private RecyclerView mProjectListRecycler;

    private ProjectAdapter mProjectAdapter;


    //This Creates the view for the Project List activity.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);

        mProjectListRecycler = (RecyclerView) view.findViewById();

        mProjectListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        mNewProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EditProjectActivity.newProjectIntent(getActivity());
                startActivity(intent);
            }
        });


        updateUI();

        return view;
    }


    //Updates the UI on resume.
    @Override
    public void onResume(){
        super.onResume();

        updateUI();
    }

    //Holds a single project and it's information for display. Since I don't know what we want to display in the main list of projects, I haven't
    //placed any text views or the like yet. -Jordan
    private class ProjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Project mProject;

        //Constructor for the view holder. Will eventually hold information for the text views.
        public ProjectHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

        }

        //This binds the project information to the view holder. Again, will eventually connect to the text views.
        public void bindProject(Project project)
        {
            mProject = project;
        }

        public void onClick (View v) {
            Intent intent = ProjectActivity.newIntent(getActivity(), mProject.getmProjectIDTag());
            startActivity(intent);
        }

    }


    //The project adapater is a mid step between the list of projects and the individual Project holders.
    private class ProjectAdapter extends RecyclerView.Adapter<ProjectHolder> {
        private List<Project> mProjects;

        public ProjectAdapter(List<Project> projects)
        {
            mProjects= projects;
        }

        //Gets a view so that we can create a new ProjectHolder.
        @Override
        public ProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= LayoutInflater.from(getActivity());

            View view = layoutInflater.inflate();

            return new ProjectHolder(view);
        }

        @Override
        public void onBindViewHolder(ProjectHolder holder, int position){

            Project project = mProjects.get(position);

            holder.bindProject(project);
        }

        @Override
        public int getItemCount() {
            return mProjects.size();
        }

    }

    private void updateUI() {
        ProjectList projectList = ProjectList.get(getActivity());

        List<Project> projects = projectList.getProjects();

        if(mProjectAdapter == null)
        {
            mProjectAdapter = new ProjectAdapter(projects);

            mProjectListRecycler.setAdapter(mProjectAdapter);
        }
        else
        {
            mProjectAdapter.notifyDataSetChanged();
        }
    }


}
