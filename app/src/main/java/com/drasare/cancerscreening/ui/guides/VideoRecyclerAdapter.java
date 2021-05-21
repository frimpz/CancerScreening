package com.drasare.cancerscreening.ui.guides;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.drasare.cancerscreening.R;

import java.util.List;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.MyViewHolder> {
    private List<VideoModel> mDataset;

   public static class MyViewHolder extends RecyclerView.ViewHolder{

       public TextView topic;

        public MyViewHolder(View v) {
            super(v);
            topic = v.findViewById(R.id.topic);
        }
   }

    public VideoRecyclerAdapter(List<VideoModel> myDataset) {
        mDataset = myDataset;
    }


    @Override
    public VideoRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.topic.setText(mDataset.get(position).getTopic());
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}