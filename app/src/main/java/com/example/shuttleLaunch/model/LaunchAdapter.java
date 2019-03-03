package com.example.shuttleLaunch.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shuttleLaunch.R;

import java.util.ArrayList;
import java.util.List;

public class LaunchAdapter extends RecyclerView.Adapter<LaunchAdapter.ItemViewHolder> {

    private final List<Launch> data;

    public LaunchAdapter(){
        this.data = new ArrayList<>();
    }
    public void setData (List<Launch> newData){
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shuttle_pass, viewGroup, false);
        return new ItemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
            Launch launch = data.get(position);
        itemViewHolder.tvLaunches.setText(launch.getName());
        itemViewHolder.tvTotal.setText(launch.getLocation().toString());
        itemViewHolder.tvOffset.setText(launch.getRocket().toString());
        itemViewHolder.tvCount.setText(launch.getMissions().toString());
            //itemViewHolder.tvLauches.setText(launchResponse.getLaunches().toString());
            //itemViewHolder.tvTotal.setText(launchResponse.getTotal());
            //itemViewHolder.tvOffset.setText(launchResponse.getOffset());
            //itemViewHolder.tvCount.setText(launchResponse.getCount());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvLaunches;
        TextView tvTotal;
        TextView tvOffset;
        TextView tvCount;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLaunches = itemView.findViewById(R.id.tvLaunches);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvOffset = itemView.findViewById(R.id.tvOffset);
            tvCount = itemView.findViewById(R.id.tvCount);

        }
    }
}
