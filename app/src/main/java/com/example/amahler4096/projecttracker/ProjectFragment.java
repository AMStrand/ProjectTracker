package com.example.amahler4096.projecttracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by amahler4096 on 10/26/2016.
 */
public class ProjectFragment extends Fragment {

    // Create variable for the calendar button:
    private Button mViewCalendarButton;

    private static final String TAG = "testing";

    private static final String ARG_PROJECT_ID = "projectID";
    private static final String DIALOG_DATE = "date";

    private static final int REQUEST_START_DATE = 0;
    private static final int REQUEST_END_DATE = 1;

    private Project mProject;
    private EditText mNameEditText;
    private EditText mCategoryEditText;
    private Button mStartDateButton;
    private Button mEndDateButton;
    private EditText mDecriptionEditText;
    private EditText mNotesEditText;

    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

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

        mNameEditText = (EditText)view.findViewById(R.id.name_edit_text);
        mNameEditText.setText(mProject.getProjectTitle());
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProject.setProjectTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCategoryEditText = (EditText)view.findViewById(R.id.category_edit_text);
        mCategoryEditText.setText(mProject.getProjectCategory());
        mCategoryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProject.setProjectCategory(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mStartDateButton = (Button) view.findViewById(R.id.start_date_button);
        mStartDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newDatePicker(mProject.getProjectStartDate());
                dialog.setTargetFragment(ProjectFragment.this, REQUEST_START_DATE);
                dialog.show(fm, DIALOG_DATE);
            }


        });


//

        mEndDateButton = (Button) view.findViewById(R.id.end_date_button);
        mEndDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newDatePicker(mProject.getProjectEndDate());
                dialog.setTargetFragment(ProjectFragment.this, REQUEST_END_DATE);
                dialog.show(fm, DIALOG_DATE);
            }

        });

        UpdateDate();


        mDecriptionEditText = (EditText)view.findViewById(R.id.description_edit_text);
        mDecriptionEditText.setText(mProject.getProjectDescription().toString());
        mDecriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProject.setProjectDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mNotesEditText = (EditText)view.findViewById(R.id.notes_edit_text);
        mNotesEditText.setText(mProject.getProjectNotes().toString());
        mNotesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProject.setProjectNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;
    }

        // Method to parse the date from the char string passed in:
    private Date parseDate(CharSequence dateCharString) {
            // Set a variable to hold the date:
        Date dateToSet;
            // Enter a try/catch in case of errors:
        try {
                // Set the date to the parsed char string passed in:
            dateToSet = dateFormat.parse(dateCharString.toString());
        } catch (Exception ex) {
                // If error parsing data, set the date to today:
            dateToSet = Calendar.getInstance().getTime();
        }
            // Return the date:
        return dateToSet;
    }

    private void UpdateDate() {
        mStartDateButton. setText(mProject.getProjectStartDate().toString());
        mEndDateButton.setText(mProject.getProjectEndDate().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            Log.e(TAG, "Hey man, this just didn't work.");
            return;
        }

        if(requestCode == REQUEST_START_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mProject.setProjectStartDate(date);
            Log.e(TAG, mProject.getProjectStartDate().toString());
            UpdateDate();
        }

        if(requestCode== REQUEST_END_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mProject.setProjectEndDate(date);
            Log.e(TAG, mProject.getProjectEndDate().toString());
            UpdateDate();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        ProjectList.get(getActivity()).UpdateProject(mProject);
    }
}
