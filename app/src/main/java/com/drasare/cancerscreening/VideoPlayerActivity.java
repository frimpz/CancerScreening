package com.drasare.cancerscreening;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.drasare.cancerscreening.ui.guides.VideoModel;
import com.drasare.cancerscreening.ui.guides.VideoRecyclerAdapter;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayerActivity extends AppCompatActivity {

    private String URL = "";

    private PlayerView playerView;
    private SimpleExoPlayer player;
    private Context context;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;


    ImageView fullscreenButton;
    boolean fullscreen = false;
    CardView videoPlayer;
    ConstraintLayout infoArea;

    private RecyclerView recyclerView;
    private VideoRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<VideoModel> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.fragment_guides_player);
        setContentView(R.layout.tyt);

        myDataset = new ArrayList<>();
        myDataset.add(new VideoModel("Cervical Cancer Prevention"));
        myDataset.add(new VideoModel("HPV Vaccination"));
        myDataset.add(new VideoModel("Screening"));

        recyclerView =  findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new VideoRecyclerAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

        playerView = findViewById(R.id.video_view);
        context = this;
        fullscreenButton = findViewById(R.id.exo_fullscreen_icon);
        videoPlayer =  findViewById(R.id.card_view);
        infoArea = findViewById(R.id.info_view);

        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fullscreen) {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(VideoPlayerActivity.this, R.drawable.ic_fullscreen_open));

                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

                    if(getSupportActionBar() != null){
                        getSupportActionBar().show();
                    }

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = (int) ( 200 * getApplicationContext().getResources().getDisplayMetrics().density);
                    playerView.setLayoutParams(params);

                    fullscreen = false;
                }else{
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(VideoPlayerActivity.this, R.drawable.ic_fullscreen_close));

                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                    if(getSupportActionBar() != null){
                        getSupportActionBar().hide();
                    }

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = params.MATCH_PARENT;
                    playerView.setLayoutParams(params);

                    fullscreen = true;
                }
            }
        });

        URL = getIntent().getStringExtra("url");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // orient();
    }

//    public void setFullScreen(){
//        infoArea.setVisibility(View.GONE);
//        fullScreen.setVisibility(View.GONE);
//        miniScreen.setVisibility(View.VISIBLE);
//    }
//
//    public void setMiniScreen(){
//        miniScreen.setVisibility(View.GONE);
//        infoArea.setVisibility(View.VISIBLE);
//        fullScreen.setVisibility(View.VISIBLE);
//    }

//    public void orient(){
//        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
//            setFullScreen();
//        }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            setMiniScreen();
//        }
//    }

    private void initializePlayer() {
        player = new SimpleExoPlayer.Builder(context).build();
        playerView.setPlayer(player);
        Uri uri = Uri.parse(URL);
        MediaSource mediaSource = buildMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, false, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    //@SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        ;
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(context, "com.drasare.cancerscreening.exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }
}
