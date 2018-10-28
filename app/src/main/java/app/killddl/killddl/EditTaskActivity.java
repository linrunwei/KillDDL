package app.killddl.killddl;

import android.support.v7.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {
    /*
    User user;
    ArrayList<Tasks> tasksList;
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
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    Boolean dateSet = false;
    Boolean timeSet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Display all the value
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittask);
        Intent intent = getIntent();
        int taskId = intent.getIntExtra("edit_taskId",0);
        Tasks targetTask = tasksList.get(taskId);
        user = MainActivity.getUser();



        mTaskName = (EditText) findViewById(R.id.edittask_taskname);
        mDescription = (EditText) findViewById(R.id.edittask_description);
        mColor = (RadioGroup) findViewById(R.id.edittask_color_group);
        mDisplayDate = (TextView) findViewById(R.id.edittask_date);
        mDisplayTime = (TextView) findViewById(R.id.edittask_time);
        mFrequency = (Spinner) findViewById(R.id.edittask_frequency);

        mTaskName.setText(targetTask.getName(),TextView.BufferType.EDITABLE);
        mDescription.setText(targetTask.getDescription(),TextView.BufferType.EDITABLE);
        mDisplayDate.setText(targetTask.date);
        mDisplayTime.setText(targetTask.time);
        mFrequency.setSelection(getIndex(mFrequency, "None"));


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                if(mTaskName == null || mDescription == null || !dateSet || !timeSet || mColor == null)
                {
                    TextView err = findViewById(R.id.addtask_errormsg);
                    err.setText("Some content are empty!");
                    return;
                }
                List<Object> tasksList = user.getTaskList();
                String taskName = mTaskName.getText().toString();
                String description = mDescription.getText().toString();
                String color = findViewById(mColor.getCheckedRadioButtonId()).getTag().toString();
                int mColor = Color.BLACK;
                switch (color){
                    case "red":
                        mColor = Color.RED;
                        break;
                    case "blue":
                        mColor = Color.BLUE;
                        break;
                    case "purple":
                        mColor = Color.BLACK;
                        break;
                    case "yellow":
                        mColor = Color.YELLOW;
                        break;
                    case "green":
                        mColor = Color.GREEN;
                        break;
                }
                java.util.Date date = new java.util.Date(year, month, day, hour, minute);
                com.google.firebase.Timestamp timestamp = new com.google.firebase.Timestamp(date);
                Tasks task = new Tasks(user.getTaskList().size(),timestamp);
                task.EditColor(mColor);
                task.EditDescription(description);
                task.EditName(taskName);
                task.date = (month+1) + "/" + day + "/" + year;
                task.time = (hour) + ":" + minute;
                user.getTaskList().add(task);
                int frequency = -1;

                //add task in Database
                Db database = MainActivity.getDatabase();
                database.EditTask(task);

                //quit
                Intent Calendar = new Intent(getApplicationContext(),CalendarActivity.class);
                startActivity(Calendar);
            }
        });
        }


    void Finish(View v, int taskId){
        tasksList.get(taskId).EditIsFinished(true);
        Intent calendar = new Intent(getApplicationContext(),CalendarActivity.class);
        startActivity(calendar);
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
    */
}
