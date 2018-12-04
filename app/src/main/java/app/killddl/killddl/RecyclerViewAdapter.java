package app.killddl.killddl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.ALARM_SERVICE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements  GestureHelper.ActionCompletionContract {
    private static final String TAG = "RecyclerViewAdapter";

    private List<Tasks> mTasks;
    private Context mContext;
    private ItemTouchHelper touchHelper;
    private String menustate;

    public RecyclerViewAdapter(Context mContext, List<Tasks> mTasks, String menustate) {
        this.mTasks = mTasks;
        this.mContext = mContext;
        this.menustate = menustate;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Tasks task = mTasks.get(holder.getAdapterPosition());
        holder.taskName.setText(task.getName());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(task.getDeadline().toDate());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String dateString = month + "/" + date + "/" + year;
        String timeString = hour + ":" + minute;
        String dateAndTime = "Due on " + dateString + " " + timeString;
        holder.taskDateTime.setText(dateAndTime);


        switch (task.getColor()){
            case Color.RED:
                holder.colorDot.setImageResource(R.drawable.button_red);
                break;
            case Color.BLUE:
                holder.colorDot.setImageResource(R.drawable.button_blue);
                break;
            case Color.BLACK:
                holder.colorDot.setImageResource(R.drawable.button_purple);
                break;
            case Color.YELLOW:
                holder.colorDot.setImageResource(R.drawable.button_yellow);
                break;
            case Color.GREEN:
                holder.colorDot.setImageResource(R.drawable.button_green);
                break;
        }

//        holder..setOnTouchListener(new View.OnTouchListener() {
        holder.reorderer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    touchHelper.startDrag(holder);
                }
                return false;
            }
        });

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, "onClick: clicked on " + mTasks.get(holder.getAdapterPosition()).getName());
//                Toast.makeText(mContext, mTasks.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(mContext, EditTaskActivity.class);
                intent.putExtra("taskId", mTasks.get(holder.getAdapterPosition()).getId());
                intent.putExtra("menuState", menustate);
                intent.putExtra("page","menuPage");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        TextView taskDateTime;
        ImageView colorDot;
        ImageView reorderer;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.task_name);
            taskDateTime = itemView.findViewById(R.id.task_date_time);
            reorderer = itemView.findViewById(R.id.task_reorderer);
            colorDot = itemView.findViewById(R.id.color_dot);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    @Override
    public void onViewMoved(int oldPosition, int newPosition) {
        Tasks targetTask = mTasks.get(oldPosition);
        Tasks task = new Tasks(targetTask);
        mTasks.remove(oldPosition);
        mTasks.add(newPosition, task);
        notifyItemMoved(oldPosition, newPosition);
    }

    @Override
    public void onViewSwiped(int position, int direction) {
        if (mTasks.size() == 0) {
            System.err.println("size == 0");
        }
        else {
            if (direction == ItemTouchHelper.LEFT) {
                Tasks toDelete = mTasks.get(position);
                cancelNotification(false, toDelete.getName(), toDelete.getFrequency(), toDelete.getId());
                MainActivity.getDatabase().deleteTask(toDelete.getId());
                mTasks.remove(position);
                notifyItemRemoved(position);
            }
            else if (direction == ItemTouchHelper.RIGHT) {
                Tasks toFinish = mTasks.get(position);
                cancelNotification(false, toFinish.getName(), toFinish.getFrequency(), toFinish.getId());
                MainActivity.getDatabase().removeTask(toFinish.getId());
                mTasks.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    public void setTouchHelper(ItemTouchHelper touchHelper) {
        this.touchHelper = touchHelper;
    }

    public void cancelNotification(boolean isRecurring, String taskName, int frequency, int taskId) {
        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        intent.putExtra("taskName", taskName);
        intent.putExtra("frequency", frequency);
        intent.putExtra("taskId", taskId);

        //        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, new Random().nextInt(2048), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), taskId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            if (isRecurring) {
                //                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent); // todo this is a expedient solution
                alarmManager.cancel(pendingIntent);
//                Toast.makeText(this, taskName + " has been marked as finished", Toast.LENGTH_LONG).show();
            }
            else {
                //                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                alarmManager.cancel(pendingIntent);
//                Toast.makeText(this, taskName + " has been marked as finished", Toast.LENGTH_LONG).show();
            }
        }
        else {
//            Toast.makeText(this, "cancel notification failed", Toast.LENGTH_LONG).show();
        }
    }
}
