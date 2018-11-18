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
        void onViewSwiped(int position);
    }

    private ActionCompletionContract contract;
    private Drawable background;
    private Drawable xMark;
    private int xMarkMargin;

    public GestureHelper(ActionCompletionContract contract, Context context) {
        this.contract = contract;
        this.background = new ColorDrawable(Color.RED);
        this.xMark = ContextCompat.getDrawable(context, R.drawable.ic_clear_24dp);
        this.xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        this.xMarkMargin = (int) context.getResources().getDimension(R.dimen.ic_clear_margin);
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        if (viewHolder instanceof SectionHeaderViewHolder) {
//            return 0;
//        }
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT;
//        return makeMovementFlags(dragFlags, 0);
//        return makeFlag(ACTION_STATE_DRAG, dragFlags);
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        contract.onViewMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        contract.onViewSwiped(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            float alpha = 1 - (Math.abs(dX) / recyclerView.getWidth());
//            viewHolder.itemView.setAlpha(alpha);
            // draw red background

            background.setBounds(viewHolder.itemView.getRight() + (int) dX, viewHolder.itemView.getTop(), viewHolder.itemView.getRight(), viewHolder.itemView.getBottom());
            background.draw(c);

            // draw x mark
            int itemHeight = viewHolder.itemView.getBottom() - viewHolder.itemView.getTop();
            int intrinsicWidth = xMark.getIntrinsicWidth();
            int intrinsicHeight = xMark.getIntrinsicWidth();

            int xMarkLeft = viewHolder.itemView.getRight() - xMarkMargin - intrinsicWidth;
            int xMarkRight = viewHolder.itemView.getRight() - xMarkMargin;
            int xMarkTop = viewHolder.itemView.getTop() + (itemHeight - intrinsicHeight)/2;
            int xMarkBottom = xMarkTop + intrinsicHeight;
            xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

            xMark.draw(c);

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
