package com.example.amahler4096.projecttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by amahler4096 on 10/26/2016.
 */
public class ProjectListFragment extends Fragment {

        // Recycler view related items:
    private RecyclerView mProjectListRecycler;
    private ProjectAdapter mProjectAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            // There is an options menu:
        setHasOptionsMenu(true);
    }

    //This Creates the view for the Project List activity.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // Inflate the view:
        View view = inflater.inflate(R.layout.activity_main, container, false);
            // Wire up the recycler view:
        mProjectListRecycler = (RecyclerView) view.findViewById(R.id.current_project_list);
            // Set the layout manager:
        mProjectListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            // Update the UI:
        updateUI();
            // Return the view:
        return view;
    }


    //Updates the UI on resume.
    @Override
    public void onResume(){
        super.onResume();
            // Update the UI:
        updateUI();
    }

    //Holds a single project and it's information for display. Since I don't know what we want to display in the main list of projects, I haven't
    //placed any text views or the like yet. -Jordan
    private class ProjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            // Local variable for a project:
        private Project mProject;
            // Set up local variables for the widgets:
        private TextView mNameTextView;
        private TextView mCategoryTextView;
        private CheckBox mCompleteCheckBox;

        //Constructor for the view holder. Will eventually hold information for the text views.
        public ProjectHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
                // Wire up the widgets:
            mNameTextView = (TextView) itemView.findViewById(R.id.project_name_text_view);
            mCategoryTextView = (TextView) itemView.findViewById(R.id.project_category_text_view);
            mCompleteCheckBox = (CheckBox) itemView.findViewById(R.id.project_complete_checkbox);
        }

        //This binds the project information to the view holder. Again, will eventually connect to the text views.
        public void bindProject(Project project)
        {
                // Save the project to the local variable:
            mProject = project;
                // Set the text of the text views:
            mNameTextView.setText(mProject.getProjectTitle());
            mCategoryTextView.setText(mProject.getProjectCategory());
                // If the end date of the project is before today, check the complete checkbox:
            mCompleteCheckBox.setChecked(mProject.getProjectEndDate().before(Calendar.getInstance().getTime()));
        }

            // On clicking one, start the ProjectActivity using the id of the chosen project:
        public void onClick (View v) {
            Intent intent = ProjectActivity.newIntent(getActivity(), mProject.getmProjectIDTag());
            startActivity(intent);
        }

    }


    //The project adapter is a mid step between the list of projects and the individual Project holders.
    private class ProjectAdapter extends RecyclerView.Adapter<ProjectHolder> {
             //Create a local variable to hold the list of projects:
        private List<Project> mProjects;

            // Set the local variable equal to the list of projects passed in:
        public ProjectAdapter(List<Project> projects)
        {
            mProjects = projects;
        }

        //Gets a view so that we can create a new ProjectHolder.
        @Override
        public ProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_project, parent, false);
            return new ProjectHolder(view);
        }

            // Binds the project to the view based on position:
        @Override
        public void onBindViewHolder(ProjectHolder holder, int position){
            Project project = mProjects.get(position);
            holder.bindProject(project);
        }

            // Method to get the item count:
        @Override
        public int getItemCount() {
            return mProjects.size();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            // Switch statement to determine which item was selected:
        switch (item.getItemId()) {
                // If the item is the new project item...
            case R.id.menu_item_new_project:
                    // Create a new project:
                Project project = new Project();
                    // Add the project to the project list:
                ProjectList.get(getActivity()).addProject(project);
                    // Get the intent from the project pager activity:
                Intent intent = ProjectPagerActivity.newIntent(getActivity(), project.getmProjectIDTag());
                    // Start the activity:
                startActivity(intent);
                    // Return true as it has succeeded if it got this far:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
            // Inflate the options menu:
        inflater.inflate(R.menu.fragment_project_list, menu);
    }

        // Private method to update the UI:
    private void updateUI() {
            // Create a local instance of the project list singleton:
        ProjectList projectList = ProjectList.get(getActivity());
            // Get the project list:
        List<Project> projects = projectList.getProjects();
            // If the adapter is null...
        if(mProjectAdapter == null)
        {
                // Create a new project adapter with the project list:
            mProjectAdapter = new ProjectAdapter(projects);
                // Set the adapter:
            mProjectListRecycler.setAdapter(mProjectAdapter);
        }
        else
        {
                // Otherwise, notify that the data set has changed:
            mProjectAdapter.notifyDataSetChanged();
        }
    }
}
