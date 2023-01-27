package com.example.findyourspotappmoatez;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findyourspotappmoatez.model.Spot;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.myviewholder> {
ArrayList<Spot> dataholder;

    public Myadapter(ArrayList<Spot> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent, false);
        return new myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.title.setText((dataholder.get(position).getTitle()));
        holder.platitude.setText((dataholder.get(position).getPlatitude()));
        holder.plongitude.setText((dataholder.get(position).getPlongitude()));



    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView title , platitude , plongitude;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
             title = (TextView) itemView.findViewById(R.id.title);
            platitude = (TextView) itemView.findViewById(R.id.platitude);
            plongitude= (TextView) itemView.findViewById(R.id.plongitude);

        }
    }

}
