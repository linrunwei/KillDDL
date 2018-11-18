package app.killddl.killddl;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditTaskActivity extends AppCompatActivity {
    
    User user;
    List<Tasks> tasksList;
    private EditText mTaskName;
    private EditText mDescription;
    private RadioGroup mColor;
    private TextView mDisplayDate;
    private TextView mDisplayTime;
    private Spinner mFrequency;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    int taskId;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    Boolean dateSet = false;
    Boolean timeSet = false;
    Tasks targetTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Display all the value
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittask);
        Intent intent = getIntent();
//        taskId = intent.getIntExtra("edit_taskId",0);
        taskId = intent.getIntExtra("taskId",0);
        tasksList = MainActivity.getDatabase().getTaskList();
        Tasks targetTask = tasksList.get(taskId);
        user = MainActivity.getDatabase().getUser();




        mTaskName = (EditText) findViewById(R.id.edittask_taskname);
        mDescription = (EditText) findViewById(R.id.edittask_description);
        mColor = (RadioGroup) findViewById(R.id.edittask_color_group);
        mDisplayDate = (TextView) findViewById(R.id.edittask_date);
        mDisplayTime = (TextView) findViewById(R.id.edittask_time);
        mFrequency = (Spinner) findViewById(R.id.edittask_frequency);

        mTaskName.setText(targetTask.getName(),TextView.BufferType.EDITABLE);
        mDescription.setText(targetTask.getDescription(),TextView.BufferType.EDITABLE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targetTask.getDeadline().toDate());
        int mmyear = calendar.get(Calendar.YEAR);
        int mmmonth = calendar.get(Calendar.MONTH)+1;
        int mmdate = calendar.get(Calendar.DAY_OF_MONTH);
        int mmhour = calendar.get(Calendar.HOUR_OF_DAY);
        int mmminute = calendar.get(Calendar.MINUTE);
        String dateString = mmmonth + "/" + mmdate + "/" + mmyear;
        String timeString = mmhour + ":" + mmminute;
        mDisplayDate.setText(dateString);
        mDisplayTime.setText(timeString);
        mFrequency.setSelection(targetTask.frequency);
        switch (targetTask.getColor()){
            case Color.RED:
                ((RadioButton)mColor.getChildAt(0)).setChecked(true);
                break;
            case Color.BLUE:
                ((RadioButton)mColor.getChildAt(1)).setChecked(true);
                break;
            case Color.BLACK:
                ((RadioButton)mColor.getChildAt(2)).setChecked(true);
                break;
            case Color.YELLOW:
                ((RadioButton)mColor.getChildAt(3)).setChecked(true);
                break;
            case Color.GREEN:
                ((RadioButton)mColor.getChildAt(4)).setChecked(true);
                break;
        }

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        EditTaskActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        mYear, mMonth, mDay);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int myear, int mmonth, int mday) {
                year = myear;
                month = mmonth;
                day = mday;
                mmonth = mmonth + 1;

                String date = mmonth + "/" + day + "/" + year;
                SpannableString content = new SpannableString(date);
                content.setSpan(new UnderlineSpan(), 0, date.length(), 0);
                mDisplayDate.setText(content);
                dateSet = true;
            }
        };

        mDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int mHour = calendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(
                        EditTaskActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener,
                        mHour, mMinute, true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hour = i;
                minute = i1;
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
        for (int i = 0; i < mColor.getChildCount(); i++){
            RadioButton r = (RadioButton) mColor.getChildAt(i);
            r.setEnabled(false);
        }



    }


    public void Edit(View v){
        mTaskName.setEnabled(true);
        mDescription.setEnabled(true);
        mColor.setClickable(true);
        mDisplayDate.setClickable(true);
        mDisplayTime.setClickable(true);
        mFrequency.setEnabled(true);
        for (int i = 0; i < mColor.getChildCount(); i++){
            RadioButton r = (RadioButton) mColor.getChildAt(i);
            r.setEnabled(true);
        }

        Button mEdit = (Button) findViewById(R.id.edittask_editBtn);
        mEdit.setText("Save");
        mEdit.setOnClickListener(new View.OnClickListener() {
            //save
            @Override
            public void onClick(View view) {
                if (mTaskName == null || mDescription == null || findViewById(mColor.getCheckedRadioButtonId()) == null || !timeSet || !dateSet) {
                    TextView err = findViewById(R.id.edittask_errormsg);
                    err.setText("Some content are empty!");
                    return;
                }
                int frequency = mFrequency.getSelectedItemPosition();
                String taskName = mTaskName.getText().toString();
                String description = mDescription.getText().toString();
                int color = mColor.getCheckedRadioButtonId();

                switch (color) {
                    case R.id.edittask_red:
                        System.out.println("CHOOSE COLOR RED " );
                        color = Color.RED;
                        break;
                    case R.id.edittask_blue:
                        System.out.println("CHOOSE COLOR blue " );
                        color = Color.BLUE;
                        break;
                    case R.id.edittask_purple:
                        System.out.println("CHOOSE COLOR black " );
                        color = Color.BLACK;
                        break;
                    case R.id.edittask_yellow:
                        System.out.println("CHOOSE COLOR yellow " );
                        color = Color.YELLOW;
                        break;
                    case R.id.edittask_green:
                        System.out.println("CHOOSE COLOR green " );
                        color = Color.GREEN;
                        break;
                }

                //add task in Database
                Date date;
                com.google.firebase.Timestamp timestamp;
                date = new Date(year-1900, month, day, hour, minute);
                timestamp = new com.google.firebase.Timestamp(date);
                if (timestamp.compareTo(com.google.firebase.Timestamp.now()) <= 0) {
                    TextView err = findViewById(R.id.addtask_errormsg);
                    err.setText("Cannot Create task before time!");
                    return;
                }
                Tasks task = new Tasks(taskId, timestamp);
                task.EditColor(color);
                task.EditDescription(description);
                task.EditName(taskName);
                if(dateSet)
                    task.date = (month + 1) + "/" + day + "/" + year;
                else
                    task.date = targetTask.date;
                if(timeSet)
                    task.time = (hour) + ":" + minute;
                else
                    task.time = targetTask.time;
                task.EditFrequency(frequency);
                MainActivity.getDatabase().EditTask(taskId,task);

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day, hour, minute, 0);
                updateNotification(calendar, false, mTaskName.getText().toString(), -1);

                Intent Calendar = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(Calendar);
            }
        });
        }


    public void Finish(View v){
        MainActivity.getDatabase().removeTask(taskId);
        System.out.println("Task Name: " + tasksList.get(taskId).getName() + " Finished task id: " + taskId);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, 0);
        cancelNotification(false, mTaskName.getText().toString(), -1);

        Intent calendarView = new Intent(getApplicationContext(),CalendarActivity.class);
        startActivity(calendarView);
    }

    public void Delete(View v) {
        Finish(v);
    }

    public int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

    public void close(View v){
        Intent calendar = new Intent(getApplicationContext(),CalendarActivity.class);
        startActivity(calendar);
    }

    public void updateNotification(Calendar calendar, boolean isRecurring, String taskName, int frequency) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        intent.putExtra("taskName", taskName);
        intent.putExtra("frequency", frequency);
        intent.putExtra("taskId", taskId);

        //        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, new Random().nextInt(2048), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), taskId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            if (isRecurring) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent); // todo this is a expedient solution
                Toast.makeText(this,
                        "You will be notified at "
                                + month + "/"
                                + date + "/"
                                + year + " "
                                + hour + ":"
                                + minute
                        , Toast.LENGTH_LONG).show();

            }
            else {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                Toast.makeText(this,
                        "You will be notified at "
                                + month + "/"
                                + date + "/"
                                + year + " "
                                + hour + ":"
                                + minute
                        , Toast.LENGTH_LONG).show();
            }

        }
        else {
            Toast.makeText(this, "update notification failed", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelNotification(boolean isRecurring, String taskName, int frequency) {
        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        intent.putExtra("taskName", taskName);
        intent.putExtra("frequency", frequency);
        intent.putExtra("taskId", taskId);

        //        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, new Random().nextInt(2048), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), taskId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            if (isRecurring) {
                //                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent); // todo this is a expedient solution
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, taskName + " has been marked as done", Toast.LENGTH_LONG).show();
            }
            else {
                //                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, taskName + " has been marked as done", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "cancel notification failed", Toast.LENGTH_LONG).show();
        }
    }

}
