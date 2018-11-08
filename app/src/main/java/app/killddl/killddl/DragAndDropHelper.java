package app.killddl.killddl;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_DRAG;

public class DragAndDropHelper extends ItemTouchHelper.Callback {
    public interface ActionCompletionContract {
        void onViewMoved(int oldPosition, int newPosition);
        void onViewSwiped(int position);
    }

    private ActionCompletionContract contract;

    public DragAndDropHelper(ActionCompletionContract contract) {
        this.contract = contract;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        if (viewHolder instanceof SectionHeaderViewHolder) {
//            return 0;
//        }
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
//        return makeMovementFlags(dragFlags, 0);
        return makeFlag(ACTION_STATE_DRAG, dragFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        contract.onViewMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//        contract.onViewSwiped(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }
}
