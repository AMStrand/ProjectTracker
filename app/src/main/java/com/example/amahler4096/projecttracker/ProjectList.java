package com.example.amahler4096.projecttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.ParseException;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private static final String TAG = "tag";

    private static ProjectList sProjectList;

    private SQLiteDatabase mDB;

    private Context mContext;






    public static ProjectList get(Context context) {
        if (sProjectList == null) {
            sProjectList = new ProjectList(context);
        }
        return sProjectList;
    }

    private ProjectList(Context context) {

        mContext = context;

        mDB = new ProjectDBHelper(context).getWritableDatabase();

    }

    public List<Project> getProjects() {

        List<Project> projects = new ArrayList<>();

        ProjectCursorWrapper cursor = queryProjects(null, null);

        try {
            cursor.moveToFirst();
            cursor.moveToNext();

            while(!cursor.isAfterLast()) {

                projects.add(cursor.getProject());

                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }

            return projects;

    }

    public Project getProject(UUID id) {

        ProjectCursorWrapper cursor= queryProjects(ProjectStorage.ProjectEntry.UUID + "= ?", new String[] {id.toString()});

        try {
            if (cursor.getCount() == 0) {
                return new Project();
            }

            cursor.moveToFirst();

            return cursor.getProject();
        }
        finally {
            cursor.close();
        }

    }

    public void addProject(Project project) {
        ContentValues values = getContentValues(project);

        mDB.insert(ProjectStorage.ProjectEntry.TABLE_NAME, null, values);

        Log.e(TAG, "Database Updated.");

    }

    public void UpdateProject(Project project) {
        String uuidString = project.getmProjectIDTag().toString();

        ContentValues values = getContentValues(project);

        mDB.update(ProjectStorage.ProjectEntry.TABLE_NAME, values, ProjectStorage.ProjectEntry.UUID + "= ?", new String[] {uuidString});
    }



    public static ContentValues getContentValues (Project project) {

        ContentValues values = new ContentValues();

        values.put(ProjectStorage.ProjectEntry.UUID, project.getmProjectIDTag().toString());
        values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_TITLE, project.getProjectTitle());
        values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_CATEGORY, project.getProjectCategory());
        values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_START_DATE, project.getProjectStartDate().toString());
        values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_END_DATE, project.getProjectEndDate().toString());
        values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_DESCRIPTION, project.getProjectDescription());
        values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_NOTES, project.getProjectNotes());

        return values;


    }


    private ProjectCursorWrapper queryProjects(String whereClause, String[] whereArgs) {
        Cursor cursor = mDB.query(
                ProjectStorage.ProjectEntry.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ProjectCursorWrapper(cursor);
    }
}
