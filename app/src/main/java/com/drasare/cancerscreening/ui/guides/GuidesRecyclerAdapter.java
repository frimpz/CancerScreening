package com.drasare.cancerscreening.ui.guides;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.drasare.cancerscreening.R;

import java.util.List;

public class GuidesRecyclerAdapter extends RecyclerView.Adapter<GuidesRecyclerAdapter.MyViewHolder> {
    private List<Guides> mDataset;
    private static  ClickListener  clickListener;
    private Activity activity;

   public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

       public TextView topic;
       public TextView date;
       public ImageView img;

        public MyViewHolder(View v) {
            super(v);
            topic = v.findViewById(R.id.topic);
            date = v.findViewById(R.id.date);
            img = v.findViewById(R.id.thumbnail);
            v.setOnClickListener(this);
        }

       @Override
       public void onClick(View v) {
           clickListener.onItemClick(getAdapterPosition(), v);
       }

       @Override
       public boolean onLongClick(View v) {
           clickListener.onItemLongClick(getAdapterPosition(), v);
           return false;
       }
   }

    public GuidesRecyclerAdapter(Activity myActivity, List<Guides> myDataset) {
        mDataset = myDataset;
        activity = myActivity;
    }


    @Override
    public GuidesRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.guides_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.topic.setText(mDataset.get(position).getTopic());
        holder.date.setText(mDataset.get(position).getDate());
        holder.img.setImageDrawable(ContextCompat.getDrawable(this.activity, mDataset.get(position).getImg()));
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        GuidesRecyclerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}