package com.example.srikant.day4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person> list;
    Context mContext;
    PersonAdapter(Context context,ArrayList<Person> people){
        this.mContext = context;
        this.list = people;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.person_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getEmail());
        holder.age.setText(String.valueOf(list.get(position).getAge()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView email;
        TextView age;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.personName);
            email = itemView.findViewById(R.id.personEmail);
            age = itemView.findViewById(R.id.personAge);
        }
    }
}
