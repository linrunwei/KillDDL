package app.killddl.killddl;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

public class EditTaskActivity extends AppCompatActivity {
    User user;
    ArrayList<Tasks> tasksList;
    private EditText mTaskName;
    private EditText mDescription;
    private RadioGroup mColor;
    private TextView mDisplayDate;
    private TextView mDisplayTime;
    private Spinner mFrequency;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    Boolean dateSet = false;
    Boolean timeSet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Display all the value
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittask);
        user = MainActivity.getUser();


        mTaskName = (EditText) findViewById(R.id.edittask_taskname);
        mDescription = (EditText) findViewById(R.id.edittask_description);
        mColor = (RadioGroup) findViewById(R.id.edittask_color_group);
        mDisplayDate = (TextView) findViewById(R.id.edittask_date);
        mDisplayTime = (TextView) findViewById(R.id.edittask_time);
        mFrequency = (Spinner) findViewById(R.id.edittask_frequency);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        EditTaskActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                SpannableString content = new SpannableString(date);
                content.setSpan(new UnderlineSpan(), 0, date.length(), 0);
                mDisplayDate.setText(content);
                dateSet = true;
            }
        };

        mDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(
                        EditTaskActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener,
                        hour,minute,true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String date = i + ":" + i1;
                System.out.println(date);
                SpannableString content = new SpannableString(date);
                content.setSpan(new UnderlineSpan(), 0, date.length(), 0);
                mDisplayTime.setText(content);
                timeSet = true;
            }
        };

        //Get all value

        //Display all the value

        //Disable edit functionality

        mTaskName.setEnabled(false);
        mDescription.setEnabled(false);
        mColor.setClickable(false);
        mDisplayDate.setClickable(false);
        mDisplayTime.setClickable(false);
        mFrequency.setEnabled(false);




    }


    public void Edit(View v){

        mTaskName.setEnabled(true);
        mDescription.setEnabled(true);
        mColor.setClickable(true);
        mDisplayDate.setClickable(true);
        mDisplayTime.setClickable(true);
        mFrequency.setEnabled(true);

        Button mEdit = (Button) findViewById(R.id.edittask_edit);
        mEdit.setText("Save");
        mEdit.setOnClickListener(new View.OnClickListener() {
            //save
            @Override
            public void onClick(View view) {
                Intent calendar = new Intent(getApplicationContext(),CalendarActivity.class);
                startActivity(calendar);
            }
        });
        }


    void Finish(View v, int taskId){
        tasksList.get(taskId).EditIsFinished(true);
        Intent calendar = new Intent(getApplicationContext(),CalendarActivity.class);
        startActivity(calendar);
    }
}
