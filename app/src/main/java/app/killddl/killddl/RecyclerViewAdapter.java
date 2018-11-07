package app.killddl.killddl;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private List<Tasks> mTasks;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, List<Tasks> mTasks) {
        this.mTasks = mTasks;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.taskName.setText(mTasks.get(position).getName());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mTasks.get(position).getDeadline().toDate());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String dateString = month + "/" + date + "/" + year;
        String timeString = hour + ":" + minute;
        String dateAndTime = "Due on " + dateString + " " + timeString;
        holder.taskDateTime.setText(dateAndTime);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + mTasks.get(position).getName());
                Toast.makeText(mContext, mTasks.get(position).getName(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(mContext, EditTaskActivity.class);
                intent.putExtra("taskId", mTasks.get(position).getId());
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
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.task_name);
            taskDateTime = itemView.findViewById(R.id.task_date_time);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
