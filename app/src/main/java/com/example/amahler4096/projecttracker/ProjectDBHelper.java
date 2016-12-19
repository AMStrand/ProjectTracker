package com.example.amahler4096.projecttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Tomato on 12/18/2016.
 */

public class ProjectDBHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Project.db";

        boolean mSeedDatabase;

        Context mContext;


        public ProjectDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mContext = context;
            mSeedDatabase = false;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + ProjectStorage.ProjectEntry.TABLE_NAME + "(" +
            "_id integer primary key autoincrement, " +
                            ProjectStorage.ProjectEntry.UUID + "," +
                            ProjectStorage.ProjectEntry.COLUMN_NAME_TITLE + ", " +
                            ProjectStorage.ProjectEntry.COLUMN_NAME_CATEGORY + ", " +
                            ProjectStorage.ProjectEntry.COLUMN_NAME_START_DATE + ", " +
                            ProjectStorage.ProjectEntry.COLUMN_NAME_END_DATE + ", " +
                            ProjectStorage.ProjectEntry.COLUMN_NAME_DESCRIPTION + ", " +
                            ProjectStorage.ProjectEntry.COLUMN_NAME_NOTES + ")"
            );

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (mSeedDatabase) {

            ContentValues values = new ContentValues();

            String date = new Date().toString();

            values.put(ProjectStorage.ProjectEntry.UUID, UUID.randomUUID().toString());
            values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_TITLE, "Enter a title here");
            values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_CATEGORY, "Enter a category here");
            values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_START_DATE, date );
            values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_END_DATE, date);
            values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_DESCRIPTION, "Enter description here");
            values.put(ProjectStorage.ProjectEntry.COLUMN_NAME_NOTES, "Enter notes here");



            db.insert(ProjectStorage.ProjectEntry.TABLE_NAME, null, values);

        }
    }
}


