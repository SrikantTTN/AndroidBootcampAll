package com.example.srikant.day2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<NewsModel> newsList = new ArrayList<>();
    private Context mContext;
    NewsAdapter(Context context){
        mContext = context;
    }

    public void addNews(ArrayList<NewsModel> list){
        newsList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.constraintlayout_ex2, parent, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.notNeededIcon.setVisibility(View.GONE);
        NewsModel n = newsList.get(position);
        holder.title.setText(n.getTitle());
        holder.description.setText(n.getDescription());
        holder.datePosted.setText(n.getDatePosted());
        Picasso.get().load(n.getMainImageUrl()).fit().centerCrop().into(holder.mainImage);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView datePosted;
        public ImageView mainImage;
        public TextView description;
        public ImageView notNeededIcon;
        public ViewHolder(View itemView) {
            super(itemView);
            notNeededIcon = itemView.findViewById(R.id.profilePic);
            title = itemView.findViewById(R.id.title);
            datePosted = itemView.findViewById(R.id.datePosted);
            description = itemView.findViewById(R.id.description);
            mainImage = itemView.findViewById(R.id.mainImg);
        }
    }
}
