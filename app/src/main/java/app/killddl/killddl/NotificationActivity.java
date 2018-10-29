package app.killddl.killddl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_notification);
    }

//    private long getDuration(){
//        // get today's date
//        Calendar temp = Calendar.getInstance();
//        // get current month
//        int currentMonth = temp.get(Calendar.MONTH);
//        // move month ahead
//        currentMonth++;
//        // check if has not exceeded threshold of december
//        if(currentMonth > Calendar.DECEMBER){
//            // alright, reset month to jan and forward year by 1 e.g from 2013 to 2014
//            currentMonth = Calendar.JANUARY;
//            // Move year ahead as well
//            temp.set(Calendar.YEAR, temp.get(Calendar.YEAR)+1);
//        }
//        // reset calendar to next month
//        temp.set(Calendar.MONTH, currentMonth);
//        // get the maximum possible days in this month
//        int maximumDay = temp.getActualMaximum(Calendar.DAY_OF_MONTH);
//        // set the calendar to maximum day (e.g in case of fEB 28th, or leap 29th)
//        temp.set(Calendar.DAY_OF_MONTH, maximumDay);
//        long futureTime = temp.getTimeInMillis(); // this is time one month ahead
//        return (futureTime); // this is what you set as trigger point time i.e one month after
//    }

//    public void GetNotification(View view, Timestamp timestamp, boolean isRecurring, String frequency, String taskName) {
//    public void GetNotification(View view) {
    public void GetNotification(View view, Calendar calendar, boolean isRecurring) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.clear();
//        boolean isRecurring = false;

//        Date dateAndTime = timestamp.toDate();
//        Date dateAndTime = new ()
//        Calendar calendar = Calendar.getInstance();
//        calendar.clear();
//        calendar.setTime(dateAndTime);
//        int year =  calendar.get(Calendar.DAY_OF_MONTH);
//        int month = calendar.get(Calendar.MONTH);
//        int date = calendar.get(Calendar.DAY_OF_MONTH);
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
        String taskName = "New task";
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = 0;
        calendar.set(year, month, date, hour, minute,second);

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        intent.putExtra("taskName", taskName);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        if (alarmManager != null) {
            if (isRecurring) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent); // todo this is a expedient solution
                Toast.makeText(this,
                        "scheduled recurring notification at "
                                + year + "."
                                + month + "."
                                + date + " "
                                + hour + ":"
                                + minute + ":"
                                + second
                        , Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "isRecurring, not implemented", Toast.LENGTH_SHORT).show();
//                switch (frequency) {
//                    case "daily":
////                        alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//                        break;
//                    case "weekly":
////                        alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingIntent);
//                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//                        break;
//                    case "monthly":
////                        alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), getDuration(), pendingIntent);
//                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//                        break;
//                }
            }
            else {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                Toast.makeText(this,
                        "scheduled one time notification at "
                        + year + "."
                        + month + "."
                        + date + " "
                        + hour + ":"
                        + minute + ":"
                        + second
                , Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "setExactAndAllowWhileIdle failed", Toast.LENGTH_SHORT).show();
        }
    }
}
