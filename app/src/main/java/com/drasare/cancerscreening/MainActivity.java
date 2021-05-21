package com.drasare.cancerscreening;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.drasare.cancerscreening.ui.guides.GuidesFragment;
import com.drasare.cancerscreening.ui.kit.RequestKitFragment;
import com.drasare.cancerscreening.ui.pickup.PickupFragment;
import com.drasare.cancerscreening.ui.provider.ProvideFragment;
import com.drasare.cancerscreening.ui.result.ResultFragment;
import com.drasare.cancerscreening.ui.survey.SurveyFragment;

import params.com.stepview.StatusViewScroller;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int NUM_PAGES = 7;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    StatusViewScroller statusViewScroller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager =  findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());

        statusViewScroller = findViewById(R.id.stat);
        statusViewScroller.getStatusView().setCurrentCount(1);

        Button nxt = findViewById(R.id.next);
        Button pre = findViewById(R.id.prev);
        nxt.setOnClickListener(this);
        pre.setOnClickListener(this);



    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                mPager.setCurrentItem(getNextPossibleItemIndex (1), true);
                setCurrent();
                break;
            case R.id.prev:
               mPager.setCurrentItem(getNextPossibleItemIndex (-1), true);
                setCurrent();
               break;
        }
    }


    private int getNextPossibleItemIndex (int change) {
        int currentIndex = mPager.getCurrentItem();
        int total = mPager.getAdapter().getCount();
        if (currentIndex + change <= 0) {
            return 0;
        }
        else if(currentIndex + change >= total){
            return total;
        }else{
            return currentIndex + change;
        }
    }

    private class SlidePagerAdapter extends FragmentStatePagerAdapter {
        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SurveyFragment();
                case 1:
                    return new GuidesFragment();
                case 2:
                    return new RequestKitFragment();
                case 3:
                    return new PickupFragment();
                case 4:
                    return new ResultFragment();
                case 5:
                    return new ProvideFragment();
                case 6:
                    return new SurveyFragment();
                default:
                    return new SurveyFragment();
                //case R.id.prev:
                //   mPager.setCurrentItem(getNextPossibleItemIndex (-1), true);
            }

        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public void setCurrent(){
        int pos = mPager.getCurrentItem()+1;
        statusViewScroller.scrollToStep(pos - 2 );
        statusViewScroller.getStatusView().setCurrentCount(pos);
    }


}
