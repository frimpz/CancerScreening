package com.drasare.cancerscreening.ui.main;

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

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder> {
    private List<Main> mDataset;
    private static  ClickListener  clickListener;
    private Activity activity;

   public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

       public TextView title;
       public ImageView img;

        public MyViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
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

    public MainRecyclerAdapter(Activity myActivity, List<Main> myDataset) {
        mDataset = myDataset;
        activity = myActivity;
    }


    @Override
    public MainRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(mDataset.get(position).getTitle());
        holder.img.setImageDrawable(ContextCompat.getDrawable(this.activity, mDataset.get(position).getImg()));
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        MainRecyclerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}