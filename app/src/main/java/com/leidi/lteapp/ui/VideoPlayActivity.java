package com.leidi.lteapp.ui;

import android.view.View;
import android.widget.VideoView;

import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

public class VideoPlayActivity extends BaseActivity {

    private VideoView vvVideo;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initView() {
        setToolbar("视频播放");
        toolbar.setVisibility(View.GONE);
        System.out.println("======"+getIntent().getStringExtra("data"));
        vvVideo = findViewById(R.id.vv_video);
        vvVideo.setVideoPath(getIntent().getStringExtra("data"));
        vvVideo.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        vvVideo.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vvVideo.stopPlayback();
    }
}
