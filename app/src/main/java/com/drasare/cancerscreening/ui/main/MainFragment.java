package com.drasare.cancerscreening.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.drasare.cancerscreening.R;
import com.drasare.cancerscreening.ui.guides.Guides;
import com.drasare.cancerscreening.ui.guides.GuidesRecyclerAdapter;
import com.shasin.notificationbanner.Banner;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements View.OnClickListener {

    private static final int NUM_PAGES = 8;

    View rootview;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        CardView one = view.findViewById(R.id.one);
        one.setOnClickListener(this);
        CardView two = view.findViewById(R.id.two);
        two.setOnClickListener(this);
        CardView three = view.findViewById(R.id.three);
        three.setOnClickListener(this);
        CardView four = view.findViewById(R.id.four);
        four.setOnClickListener(this);
        CardView five = view.findViewById(R.id.five);
        five.setOnClickListener(this);
        CardView six = view.findViewById(R.id.six);
        six.setOnClickListener(this);
        CardView seven = view.findViewById(R.id.seven);
        seven.setOnClickListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        rootview = root;
        return root;
    }

    @Override
    public void onClick(View v) {
        final NavController navController = Navigation.findNavController(v);
        switch (v.getId()){
            case R.id.two:
                navController.navigate(R.id.requestKitFragment);
                break;
            case R.id.three:
                navController.navigate(R.id.pickupFragment);
                break;
            case R.id.four:
                navController.navigate(R.id.provideFragment);
                break;
            case R.id.five:
                openResult(getActivity());
                break;
            case R.id.six:
                openLink(getResources().getString(R.string.survey_url));
                break;
            case R.id.seven:
                navController.navigate(R.id.qnAFragment);
                break;
            default:
                navController.navigate(R.id.guidesFragment);
                break;
        }
    }

    public void openResult(Activity activity){
        //if(){
            Banner.make(rootview,activity,Banner.INFO,activity.getResources().getString(R.string.result_title),Banner.TOP).show();
        //}
        //else{
        //  navController.navigate(R.id.resultFragment);
        //}

    }

    public void openLink(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

}
