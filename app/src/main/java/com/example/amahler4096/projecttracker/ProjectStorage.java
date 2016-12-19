package com.example.amahler4096.projecttracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Tomato on 12/18/2016.
 */

public final class ProjectStorage {

    private ProjectStorage() {};

    public static class ProjectEntry implements BaseColumns {
        public static final String UUID = "uuid";
        public static final String TABLE_NAME = "projects";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CATEGORY = "subtitle";
        public static final String COLUMN_NAME_START_DATE = "start";
        public static final String COLUMN_NAME_END_DATE = "end";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_NOTES = "notes";
    }


}
