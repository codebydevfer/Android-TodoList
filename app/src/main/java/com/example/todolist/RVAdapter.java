package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{
    ArrayList<String> arrayRV = new ArrayList<>();
    ArrayList<String> arrayListNames = new ArrayList<>();
    Context context;

    public RVAdapter(ArrayList<String> arrayRV, ArrayList<String> arrayListNames, Context context){ // added this
        this.arrayRV = arrayRV;
        this.arrayListNames = arrayListNames;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvRV.setText(arrayRV.get(position));
        holder.tvRVName.setText(arrayListNames.get(position));

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosicion = holder.getAbsoluteAdapterPosition();

                arrayRV.remove(currentPosicion);
                arrayListNames.remove(currentPosicion);

                notifyItemRemoved(currentPosicion);
                notifyItemRangeChanged(currentPosicion, arrayListNames.size());

                //Save to JSON
                if (context instanceof MainActivity){
                    ((MainActivity) context).saveToDataJson();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRV, tvRVName;
        Button deleteBtn;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvRV = itemView.findViewById(R.id.tv_rv);
            tvRVName = itemView.findViewById(R.id.tv_rv_name);
            deleteBtn = itemView.findViewById(R.id.delete_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), arrayListNames.get(getAbsoluteAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
