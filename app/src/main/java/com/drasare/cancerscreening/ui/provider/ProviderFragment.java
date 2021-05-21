package com.drasare.cancerscreening.ui.provider;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.drasare.cancerscreening.R;

import java.util.ArrayList;
import java.util.List;

public class ProviderFragment extends Fragment implements ProviderRecyclerAdapter.ClickListener {

    private RecyclerView recyclerView;
    private ProviderRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SearchView searchView;

    List<Provider> myDataset;
    public ProviderFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDataset = new ArrayList<>();
        myDataset.add(new Provider("Baylor Clininc","2546780917","Waco, Texas", "700 S 7th street"));
        myDataset.add(new Provider("Baylor Scott and White","657543323","Waco, Texas", "900 S 7th street"));
        myDataset.add(new Provider("Korle Bu","75767611","Taifa", "Accra Ghana"));
        myDataset.add(new Provider("Military Hospital","345435435","Dome, Texas", "Kumasi"));
        myDataset.add(new Provider("Komfo Anokye","43543545","Kumasi, Texas", "700 S 7th street"));
        myDataset.add(new Provider("ST Johns","65645656","Waco, Texas", "900 S 7th street"));
        myDataset.add(new Provider("Tech Hospital","324324324","Waco, Texas", "700 S 7th street"));
        myDataset.add(new Provider("Legon Hospital","4646464","Waco, Texas", "900 S 7th street"));
        myDataset.add(new Provider("Rigde Hospital","1232423","Waco, Texas", "700 S 7th street"));
        myDataset.add(new Provider("Holy Trinity","555553434","Waco, Texas", "900 S 7th street"));
        myDataset.add(new Provider("Adabraka Polyclinic","111324343","Waco, Texas", "700 S 7th street"));
        myDataset.add(new Provider("Atomic Hospital","65656565","Waco, Texas", "900 S 7th street"));myDataset.add(new Provider("Baylor Clininc","2546780917","Waco, Texas", "700 S 7th street"));
        myDataset.add(new Provider("Estate Hospital","222454545","Waco, Texas", "900 S 7th street"));
        myDataset.add(new Provider("Emergeny room Bi","4444444","Waco, Texas", "700 S 7th street"));
        myDataset.add(new Provider("Cizanao","33339879","Waco, Texas", "900 S 7th street"));

        recyclerView =  view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ProviderRecyclerAdapter(view.getContext(), myDataset);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_provider, container, false);
    }

    @Override
    public void onItemClick(int position, View v) {

    }

    @Override
    public void onItemLongClick(int position, View v) {

    }
}
