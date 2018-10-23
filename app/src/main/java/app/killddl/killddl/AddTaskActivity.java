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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.List;

public class AddTaskActivity extends AppCompatActivity {
    User user;
    private EditText mTaskName;
    private EditText mDescription;
    private RadioGroup mColor;
    private Spinner mFrequency;
    private TextView mDisplayDate;
    private TextView mDisplayTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    Boolean dateSet = false;
    Boolean timeSet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        Intent intent = getIntent();
        user = (User)intent.getExtras().get("User");
        mDisplayDate = (TextView) findViewById(R.id.addtask_date);
        mDisplayTime = (TextView) findViewById(R.id.addtask_time);
        mTaskName = (EditText) findViewById(R.id.addtask_taskname);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        AddTaskActivity.this,
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
                        AddTaskActivity.this,
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
    }

    public void close(View v){
        Intent calendar = new Intent(getApplicationContext(),CalendarActivity.class);
        startActivity(calendar);
    }

    public void AddTask(View v){
        List<Object> tasks = user.getTaskList();
        String taskName = mTaskName.getText().toString();
        String description = mDescription.getText().toString();


    }
}
