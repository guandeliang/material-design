package com.jacob.book.material.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.book.material.R;
import com.jacob.book.material.example.model.Thrones;

import java.util.ArrayList;
import java.util.List;

public class GrammyLibraryLiveAdapterBak2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static interface OnItemClickListener{
        public void onItemClick(View view, Thrones thrones, int position);
    }


    private List<Thrones> items;
    private Context context;
    private OnItemClickListener listener;

    public GrammyLibraryLiveAdapterBak2(Context context){
        this.context = context;
        this.items = new ArrayList<>();
    }

    public void setItems(List<Thrones> items){
        if(items != null){
            this.items = items;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.grammy_library_live_holder, parent, false);
        RecyclerView.ViewHolder holder = new LiveViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Thrones thrones = this.items.get(position);
        LiveViewHolder liveViewHolder = (LiveViewHolder)holder;
        liveViewHolder.bind(thrones, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class LiveViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private ImageView imageView;
        private TextView titleTextView;
        private TextView summaryTextView;

        public LiveViewHolder(View view){
            super(view);
            this.view = view;
            this.imageView = view.findViewById(R.id.image_view);
            this.titleTextView = view.findViewById(R.id.title_text_view);
            this.summaryTextView = view.findViewById(R.id.summary_text_view);
        }

        private void bind(Thrones item, int position) {
            int imageResId = context.getResources().getIdentifier(item.getFileName(), "drawable", context.getPackageName());
            imageView.setImageResource(imageResId);
            titleTextView.setText(item.getTitle());
            summaryTextView.setText(item.getSummary());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener == null){
                        return;
                    }
                    listener.onItemClick(view, item, position);
                }
            });
        }
    }


}
