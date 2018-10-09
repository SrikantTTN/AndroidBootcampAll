package com.example.srikant.day5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    Context mContext ;
    ArrayList<Contact> mList;
    Boolean mDeletable;
    recyclerItemClick itemClickCallback;
    ContactAdapter(Context context,ArrayList<Contact> list, Boolean deletable){
        mContext = context;
        mList = list;
        mDeletable = deletable;
        itemClickCallback = (recyclerItemClick) context;
    }

    interface recyclerItemClick{
        void deleteContact(long id, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.person_item,null);
        return new ViewHolder(view,itemClickCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mList.get(position).getName());
        if(mList.get(position).getNumber()!=null || mList.get(position).getNumber().length()>0)
        holder.number.setText(mList.get(position).getNumber());
        else
            holder.number.setVisibility(View.GONE);
        }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView number;
        LinearLayout cl;
        recyclerItemClick callback;
        public ViewHolder(View itemView,recyclerItemClick recyclerItemClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.personName);
            callback = recyclerItemClickListener;
            number = itemView.findViewById(R.id.personNumber);
            cl = itemView.findViewById(R.id.itemContainer);
            if(mDeletable)
                cl.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            long id = mList.get(getAdapterPosition()).getId();
            callback.deleteContact(id,getAdapterPosition());
            mList.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
        }
    }
}
