package app.killddl.killddl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class GestureHelper extends ItemTouchHelper.Callback {
    public interface ActionCompletionContract {
        void onViewMoved(int oldPosition, int newPosition);
        void onViewSwiped(int position, int direction);
    }

    private ActionCompletionContract contract;
    private Drawable deleteBackground;
    private Drawable finishBackground;
    private Drawable deleteMark;
    private Drawable finishMark;
    private int markMargin;

    public GestureHelper(ActionCompletionContract contract, Context context) {
        this.contract = contract;
        this.deleteBackground = new ColorDrawable(Color.RED);
        this.deleteMark = ContextCompat.getDrawable(context, R.drawable.ic_clear);
        this.deleteMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        this.finishBackground = new ColorDrawable(0xFF09BB26);
        this.finishMark = ContextCompat.getDrawable(context, R.drawable.ic_check);
        this.finishMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        this.markMargin = (int) context.getResources().getDimension(R.dimen.ic_clear_margin);
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        contract.onViewMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        contract.onViewSwiped(viewHolder.getAdapterPosition(), direction);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (dX < 0) { // swipe from right to left, should delete a task
//                float alpha = 1 - (Math.abs(dX) / recyclerView.getWidth());
//                viewHolder.itemView.setAlpha(alpha);
                // draw red background

                deleteBackground.setBounds(viewHolder.itemView.getRight() + (int) dX, viewHolder.itemView.getTop(), viewHolder.itemView.getRight(), viewHolder.itemView.getBottom());
                deleteBackground.draw(c);

                // draw delete mark
                int itemHeight = viewHolder.itemView.getBottom() - viewHolder.itemView.getTop();
                int intrinsicWidth = deleteMark.getIntrinsicWidth();
                int intrinsicHeight = deleteMark.getIntrinsicWidth();

                int xMarkLeft = viewHolder.itemView.getRight() - markMargin - intrinsicWidth;
                int xMarkRight = viewHolder.itemView.getRight() - markMargin;
                int xMarkTop = viewHolder.itemView.getTop() + (itemHeight - intrinsicHeight)/2;
                int xMarkBottom = xMarkTop + intrinsicHeight;
                deleteMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

                deleteMark.draw(c);
            }
            else if (dX > 0) { // swipe from left to right, should mark a task as finished
                finishBackground.setBounds(viewHolder.itemView.getLeft() + (int) dX, viewHolder.itemView.getTop(), viewHolder.itemView.getLeft(), viewHolder.itemView.getBottom());
                finishBackground.draw(c);

                int itemHeight = viewHolder.itemView.getBottom() - viewHolder.itemView.getTop();
                int intrinsicWidth = finishMark.getIntrinsicWidth();
                int intrinsicHeight = finishMark.getIntrinsicWidth();

                int xMarkLeft = viewHolder.itemView.getLeft() + markMargin;
                int xMarkRight = viewHolder.itemView.getLeft() + markMargin + intrinsicWidth;
                int xMarkTop = viewHolder.itemView.getTop() + (itemHeight - intrinsicHeight)/2;
                int xMarkBottom = xMarkTop + intrinsicHeight;
                finishMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

                finishMark.draw(c);
            }

        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
}
