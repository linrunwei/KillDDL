package app.killddl.killddl;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String channelId = "due-now";
        String taskName = intent.getStringExtra("taskName");
        int frequency = intent.getIntExtra("frequency", -1);
//        Intent repeatingIntent = new Intent(context, CalendarActivity.class);
        Intent showDueIntent = new Intent(context, CalendarActivity.class);
        showDueIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, showDueIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("You have a due NOW!")
                .setContentText(taskName)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(100, builder.build());
        //frequency
        if (frequency != -1) {
            switch (frequency) {
                case 1:

                    break;
                case 2:

                    break;

                case 3:

                    break;
                default:
                    Toast.makeText(context, "frequency notification failed", Toast.LENGTH_SHORT).show();
                    break;

            }
        }

    }
}
