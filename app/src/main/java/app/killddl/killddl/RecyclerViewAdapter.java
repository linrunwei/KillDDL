package app.killddl.killddl;

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

import java.util.List;

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
    public void onViewSwiped(int position) {
        if (mTasks.size() == 0) {
            System.err.println("size == 0");
        }
        else {
            MainActivity.getDatabase().removeTask(mTasks.get(position).getId());
            mTasks.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void setTouchHelper(ItemTouchHelper touchHelper) {
        this.touchHelper = touchHelper;
    }
}
