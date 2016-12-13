package com.example.amahler4096.projecttracker;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

/**
 * Created by amahler4096 on 10/24/2016.
 */
public class ProjectList {

    private static ProjectList sProjectList;

    private List<Project> mProjectList;

    private Context mContext;

    public static ProjectList get(Context context) {
        if (sProjectList == null) {
            sProjectList = new ProjectList(context);
        }
        return sProjectList;
    }

    private ProjectList(Context context) {
        mProjectList = new ArrayList<>();

        mContext = context;

        // Load in some projects for testing purposes:
        for (int i = 0; i < 10; i++) {
            Project project = new Project();
            project.setProjectTitle("Project #" + i);

            String category;
            if (i % 2 == 0) {
                category = "School";
            } else {
                category = "Work";
            }

            project.setProjectCategory(category);
            project.setProjectStartDate(new Date());
            project.setProjectEndDate(new Date());
            project.setProjectDescription("Project #" + i + " for " + category);
            project.setProjectNotes("Notes for project here");

            mProjectList.add(project);
        }
    }

    public List<Project> getProjects() {
        return mProjectList;
    }

    public Project getProject(UUID id) {
        for(Project project : mProjectList) {
            if(project.getmProjectIDTag().equals(id)) {
                return project;
            }
        }
        return null;
    }
}
