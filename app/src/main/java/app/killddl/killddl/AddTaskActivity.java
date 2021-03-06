package app.killddl.killddl;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
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
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Date;
import com.google.firebase.Timestamp;

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
    int datePickerId;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int taskId;
    private String menustate;
    private String toPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        Intent intent = getIntent();
        if (intent.hasExtra("menuState")) {
            menustate = intent.getStringExtra("menuState");
        }
        else {
//            menustate = "daily";
            System.err.println("!!! No extra in menu");
        }

        //to which page
        if (intent.hasExtra("page")) {
            toPage = intent.getStringExtra("page");
        }
        else {
            toPage = "calendarPage";
        }

        user = MainActivity.getDatabase().getUser();
        mDisplayDate = findViewById(R.id.addtask_date);
        mDisplayTime = findViewById(R.id.addtask_time);
        mDescription = findViewById(R.id.addtask_description);
        mColor = findViewById(R.id.addtask_color_group);
        mTaskName = findViewById(R.id.addtask_taskname);
        mFrequency = findViewById(R.id.addtask_frequency);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        AddTaskActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        mYear, mMonth, mDay);
                datePickerId = getTaskId();

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
                        AddTaskActivity.this,
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
    }

    public void close(View v) {
        if(toPage.equals("calendarPage")){
            Intent newIntent = new Intent(getApplicationContext(), CalendarActivity.class);
            startActivity(newIntent);
        }else if(toPage.equals("menuPage")){
            Intent newIntent = new Intent(getApplicationContext(), MenuActivity.class);
            newIntent.putExtra("menuState", menustate);
            startActivity(newIntent);
        }
    }

    public void AddTask(View v) {
        if (mTaskName == null || mDescription == null || !dateSet || !timeSet || findViewById(mColor.getCheckedRadioButtonId()) == null) {
            TextView err = findViewById(R.id.addtask_errormsg);
            err.setText("Some content are empty!");
            return;
        }
        int frequency = mFrequency.getSelectedItemPosition();
        String taskName = mTaskName.getText().toString();
        String description = mDescription.getText().toString();
        int color = mColor.getCheckedRadioButtonId();
        if (taskName == "" || description == "" || !dateSet || !timeSet || findViewById(mColor.getCheckedRadioButtonId()) == null) {
            TextView err = findViewById(R.id.addtask_errormsg);
            err.setText("Some content are empty!");
            return;
        }

        switch (color) {
            case R.id.addtask_red:
                System.out.println("CHOOSE COLOR RED " );
                color = Color.RED;
                break;
            case R.id.addtask_blue:
                System.out.println("CHOOSE COLOR blue " );
                color = Color.BLUE;
                break;
            case R.id.addtask_purple:
                System.out.println("CHOOSE COLOR black " );
                color = Color.BLACK;
                break;
            case R.id.addtask_yellow:
                System.out.println("CHOOSE COLOR yellow " );
                color = Color.YELLOW;
                break;
            case R.id.addtask_green:
                System.out.println("CHOOSE COLOR green " );
                color = Color.GREEN;
                break;
        }

        //add task in Database
        Date date = new Date(year-1900, month, day, hour, minute);
        com.google.firebase.Timestamp timestamp = new com.google.firebase.Timestamp(date);
        if (timestamp.compareTo(Timestamp.now()) <= 0) {
            TextView err = findViewById(R.id.addtask_errormsg);
            err.setText("Cannot Create task before time!");
            return;
        }
        taskId = MainActivity.getDatabase().getUser().getTaskCount();
        MainActivity.getDatabase().getUser().setTaskCount(taskId+1);
        MainActivity.getDatabase().updateUser(user);
        Tasks task = new Tasks(taskId, timestamp);
        task.EditColor(color);
        task.EditDescription(description);
        task.EditName(taskName);
        task.date = (month + 1) + "/" + day + "/" + year;
        task.time = (hour) + ":" + minute;
        task.EditFrequency(frequency);
        MainActivity.getDatabase().addTask(task);

        // seed a notification
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, 0);

        setNotification(calendar, false, taskName, -1);

        if(toPage.equals("calendarPage")){
            Intent newIntent = new Intent(getApplicationContext(), CalendarActivity.class);
            startActivity(newIntent);
        }else if(toPage.equals("menuPage")){
            Intent newIntent = new Intent(getApplicationContext(), MenuActivity.class);
            newIntent.putExtra("menuState", menustate);
            startActivity(newIntent);
        }
        /*
        Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
        menu.putExtra("menuState", menustate);
        startActivity(menu);
        */
    }

    public void setNotification(Calendar calendar, boolean isRecurring, String taskName, int frequency) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        intent.putExtra("taskName", taskName);
        intent.putExtra("frequency", frequency);
        intent.putExtra("taskId", taskId);

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
            Toast.makeText(this, "set notification failed", Toast.LENGTH_LONG).show();
        }
    }

}

