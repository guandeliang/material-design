package com.jacob.book.material.main.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.book.material.R;
import com.jacob.book.material.main.model.ExampleGroup;
import com.jacob.book.material.main.model.ExampleItem;

import java.util.List;

public class MainExampleNodeAdapter extends BaseNodeAdapter {
    public interface OnItemClickListener{
        public void onItemClick(ExampleItem item);
    }

    private static int ITEM_TYPE_UNKNOW_NODE = -1;
    private static int ITEM_TYPE_ROOT_NODE = 0;
    private static int ITEM_TYPE_ITEM_NODE = 1;

    private OnItemClickListener onItemClickListener;

    public MainExampleNodeAdapter(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
        addFullSpanNodeProvider(new RootNodeProvider());
        addItemProvider(new ItemNodeProvider());
    }


    @Override
    protected int getItemType(List<? extends BaseNode> data, int position) {
        BaseNode node = data.get(position);
        if (node instanceof ExampleGroup) {
            return ITEM_TYPE_ROOT_NODE;
        }else if (node instanceof ExampleItem) {
            return ITEM_TYPE_ITEM_NODE;
        }else{
            return ITEM_TYPE_UNKNOW_NODE;
        }
    }

    private class RootNodeProvider extends BaseNodeProvider {
        @Override
        public int getItemViewType() {
            return ITEM_TYPE_ROOT_NODE;
        }

        @Override
        public int getLayoutId() {
            return R.layout.main_example_group_holder;
        }

        @Override
        public void convert(BaseViewHolder holder, BaseNode node) {
            ExampleGroup group = (ExampleGroup)node;
            String title = group.getTitle();
            int iconResId = context.getResources().getIdentifier(group.getIconName(), "drawable", context.getPackageName());
            holder.setText(R.id.title_text_view, title);
            holder.setImageResource(R.id.icon_image_view, iconResId);
            if(group.isExpanded()){
                holder.setImageResource(R.id.arrow_image_view, R.drawable.icon_arrow_drop_down);
            }else{
                holder.setImageResource(R.id.arrow_image_view, R.drawable.icon_arrow_drop_up);
            }

        }

        @Override
        public void onClick(BaseViewHolder holder, View view, BaseNode data, int position) {
            getAdapter().expandOrCollapse(position);
        }
    }

    private class ItemNodeProvider extends BaseNodeProvider {
        @Override
        public int getItemViewType() {
            return ITEM_TYPE_ITEM_NODE;
        }

        @Override
        public int getLayoutId() {
            return R.layout.main_example_item_holder;
        }

        @Override
        public void convert(BaseViewHolder holder, BaseNode node) {
            ExampleItem item = (ExampleItem)node;
            String title = item.getTitle();
            holder.setText(R.id.title_text_view, title);
        }

        @Override
        public void onClick(BaseViewHolder helper, View view, BaseNode node, int position) {
            ExampleItem item = (ExampleItem)node;
            onItemClickListener.onItemClick(item);
        }
    }
}
