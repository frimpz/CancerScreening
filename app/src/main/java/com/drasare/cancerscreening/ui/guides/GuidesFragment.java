package com.drasare.cancerscreening.ui.guides;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drasare.cancerscreening.MainActivity;
import com.drasare.cancerscreening.R;
import com.drasare.cancerscreening.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;


public class GuidesFragment extends Fragment implements GuidesRecyclerAdapter.ClickListener {

    private RecyclerView recyclerView;
    private GuidesRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Guides> myDataset;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDataset = new ArrayList<>();
        myDataset.add(new Guides("Reprioritizing","Current",getString(R.string.media_url_mp4_2), R.drawable.prio));
        myDataset.add(new Guides("Reforming","1 Days Ago",getString(R.string.media_url_mp4_1), R.drawable.frame));
        myDataset.add(new Guides("Reframing","4 Days Ago",getString(R.string.media_url_mp4_2), R.drawable.two));

        DividerItemDecoration verticalDivider = new DividerItemDecoration(view.getContext(),
                DividerItemDecoration.VERTICAL);

        recyclerView =  view.findViewById(R.id.my_recycler_view);
        recyclerView.addItemDecoration(verticalDivider);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new GuidesRecyclerAdapter(getActivity(), myDataset);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_guides, container, false);

        return root;
    }

    @Override
    public void onItemClick(int position, View v) {
        Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
        intent.putExtra("url", myDataset.get(position).getUrl());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position, View v) {

    }
}
