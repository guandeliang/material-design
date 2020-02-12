package com.jacob.book.material.example.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.ExampleImage;
import com.jacob.book.temp.TempConstant;

import java.util.ArrayList;
import java.util.List;

public class ExampleTrackerImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int ITEM_TYPE_HEADER = -1;
    private static int ITEM_TYPE_ITEM = 0;
    private static int ITEM_TYPE_FOOTER = 1;

    private List<ExampleImage> items;
    private Context context;
    private SelectionTracker<Long> tracker;

    public ExampleTrackerImageAdapter(Context context){
        this.context = context;
        this.items = new ArrayList<>();
    }

    public void setItems(List<ExampleImage> items){
        if(items != null){
            this.items = items;
        }
    }

    public void setTracker(SelectionTracker<Long> tracker){
        this.tracker = tracker;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return ITEM_TYPE_HEADER;
        }else if(position == items.size() + 1){
            return ITEM_TYPE_FOOTER;
        }else{
            return ITEM_TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return items.size() + 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        if(viewType == ITEM_TYPE_HEADER){
            View view = inflater.inflate(R.layout.example_image_header, parent, false);
            holder = new HeaderViewHolder(view);
        }else if(viewType == ITEM_TYPE_FOOTER){
            View view = inflater.inflate(R.layout.example_image_footer, parent, false);
            holder = new FooterViewHolder(view);
        }else{
            View view = inflater.inflate(R.layout.example_image_selection_holder, parent, false);
            holder = new ItemViewHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        if(holder instanceof ItemViewHolder){
            layoutParams.setFullSpan(false);
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            ExampleImage item = items.get(position - 1);
            itemViewHolder.bind(item, position);
        }else{
            layoutParams.setFullSpan(true);
            if(holder instanceof HeaderViewHolder){
                HeaderViewHolder headerViewHolder = (HeaderViewHolder)holder;
                headerViewHolder.bind(getItemCount() - 2);
            }
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public HeaderViewHolder(View view){
            super(view);
            this.textView = view.findViewById(R.id.text_view);
        }

        private void bind(int itemCount) {
            textView.setText("共" + itemCount + "张图片");
        }

    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{
        public FooterViewHolder(View view){
            super(view);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private View maskView;
        private ImageView unCheckImageView;
        private ImageView checkImageView;

        private final ItemDetails details;

        public ItemViewHolder(View view){
            super(view);
            this.imageView = view.findViewById(R.id.image_view);
            this.maskView = view.findViewById(R.id.mask_view);
            this.unCheckImageView = view.findViewById(R.id.unchecked_image_view);
            this.checkImageView = view.findViewById(R.id.checked_image_view);
            details = new ItemDetails();
        }

        private void bind(ExampleImage item, int position) {
            details.position = position;
            String fileName = item.getFileName();
            int iconResId = context.getResources().getIdentifier(fileName, "drawable", context.getPackageName());
            imageView.setImageResource(iconResId);

            if (tracker != null) {
                bindSelectedState();
            }
        }

        private void bindSelectedState() {
            if(tracker.isSelected(details.getSelectionKey())){
                this.maskView.setVisibility(View.VISIBLE);
                this.unCheckImageView.setVisibility(View.INVISIBLE);
                this.checkImageView.setVisibility(View.VISIBLE);
            }else{
                this.maskView.setVisibility(View.INVISIBLE);
                this.unCheckImageView.setVisibility(View.VISIBLE);
                this.checkImageView.setVisibility(View.INVISIBLE);
            }
        }

        ItemDetailsLookup.ItemDetails<Long> getItemDetails() {
            return details;
        }

    }

    public static class SelectionPredicate extends SelectionTracker.SelectionPredicate<Long> {
        private ExampleTrackerImageAdapter adapter;
        public SelectionPredicate(ExampleTrackerImageAdapter adapter){
            this.adapter = adapter;
        }

        @Override
        public boolean canSetStateForKey(@NonNull Long key, boolean nextState) {
            if(key == 0 || key == adapter.getItemCount()){
                return false;
            }else{
                return true;
            }
        }

        @Override
        public boolean canSetStateAtPosition(int position, boolean nextState) {
            if(position == 0 || position == adapter.getItemCount()){
                return false;
            }else{
                return true;
            }
        }

        @Override
        public boolean canSelectMultiple() {
            return true;
        }
    }

    public static class DetailsLookup extends ItemDetailsLookup<Long> {

        private final RecyclerView recyclerView;

        public DetailsLookup(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        @Nullable
        @Override
        public ItemDetails<Long> getItemDetails(@NonNull MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                if (viewHolder instanceof ItemViewHolder) {
                    return ((ItemViewHolder) viewHolder).getItemDetails();
                }
            }
            return null;
        }
    }

    public static class KeyProvider extends ItemKeyProvider<Long> {

        public KeyProvider(ExampleTrackerImageAdapter adapter) {
            super(ItemKeyProvider.SCOPE_MAPPED);
        }

        @Nullable
        @Override
        public Long getKey(int position) {
            return (long) position;
        }

        @Override
        public int getPosition(@NonNull Long key) {
            long value = key;
            return (int) value;
        }
    }

    public static class ItemDetails extends ItemDetailsLookup.ItemDetails<Long> {
        private long position;
        public ItemDetails() {
        }

        @Override
        public int getPosition() {
            return (int) position;
        }

        @Nullable
        @Override
        public Long getSelectionKey() {
            return position;
        }

        @Override
        public boolean inSelectionHotspot(@NonNull MotionEvent e) {
            return false;
        }

        @Override
        public boolean inDragRegion(@NonNull MotionEvent e) {
            return true;
        }
    }


}
