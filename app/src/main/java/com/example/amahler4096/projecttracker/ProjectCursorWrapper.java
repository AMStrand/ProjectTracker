package com.example.amahler4096.projecttracker;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Tomato on 12/18/2016.
 */

public class ProjectCursorWrapper extends CursorWrapper {

    public static final String TAG = "tag";

    public ProjectCursorWrapper (Cursor cursor){
        super(cursor);
    }

    public Project getProject() {

        String projectUUID = getString(getColumnIndex(ProjectStorage.ProjectEntry.UUID));
        String projectTitle = getString(getColumnIndex(ProjectStorage.ProjectEntry.COLUMN_NAME_TITLE));
        String projectCategory = getString((getColumnIndex(ProjectStorage.ProjectEntry.COLUMN_NAME_CATEGORY)));
        long projectStart = getLong(getColumnIndex(ProjectStorage.ProjectEntry.COLUMN_NAME_START_DATE));
        long projectEnd= getLong(getColumnIndex(ProjectStorage.ProjectEntry.COLUMN_NAME_END_DATE));
        String projectDescription = getString(getColumnIndex(ProjectStorage.ProjectEntry.COLUMN_NAME_DESCRIPTION));
        String projectNotes= getString(getColumnIndex(ProjectStorage.ProjectEntry.COLUMN_NAME_NOTES));


        Log.e(TAG, projectUUID);

        Project project = new Project(UUID.fromString(projectUUID));

        project.setProjectTitle(projectTitle);
        project.setProjectCategory(projectCategory);
        project.setProjectStartDate(new Date(projectStart));
        project.setProjectEndDate(new Date(projectEnd));
        project.setProjectDescription(projectDescription);
        project.setProjectNotes(projectNotes);

        return project;

    }

}
