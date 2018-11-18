package app.killddl.killddl;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import java.util.Random;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String channelId = "due-now";

        String taskName = intent.getStringExtra("taskName");
        int frequency = intent.getIntExtra("frequency", -1);
        int taskId = intent.getIntExtra("taskId", -1);

        Intent showDueIntent = new Intent(context, EditTaskActivity.class);
        showDueIntent.putExtra("taskId", taskId);
        showDueIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, new Random().nextInt(2048), showDueIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(taskName + " is due now!")
                .setContentText("Tap to open this task.")
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(new Random().nextInt(2048), builder.build());


        //frequency
        if (frequency == -1) {
            // todo destroy this task using task id

        }
        else {
            switch (frequency) {
                case 1: // daily

                    break;
                case 2: // weekly

                    break;
                case 3: // monthly


                    break;
                default:
                    Toast.makeText(context, "frequency notification failed", Toast.LENGTH_SHORT).show();
                    break;

            }
        }

    }
}
