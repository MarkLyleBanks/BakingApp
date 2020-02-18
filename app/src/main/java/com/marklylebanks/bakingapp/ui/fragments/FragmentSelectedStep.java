package com.marklylebanks.bakingapp.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.marklylebanks.bakingapp.R;
import com.marklylebanks.bakingapp.ui.MainActivity;

public class FragmentSelectedStep extends Fragment {

    private int mRecipeId;
    private int mStepId;
    private SimpleExoPlayer mPlayer;
    private SimpleExoPlayerView mPlayerView;
    private Context mContext;

    public FragmentSelectedStep() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selected_step, container, false);

        mPlayerView = rootView.findViewById(R.id.playerView);


        TextView shortDesc = rootView.findViewById(R.id.tv_selected_step_short_description);
        TextView description = rootView.findViewById(R.id.tv_selected_step_description);

        shortDesc.setText(MainActivity.recipeList.get(mRecipeId).getStepsList().get(mStepId).getShortDescription());
        description.setText(MainActivity.recipeList.get(mRecipeId).getStepsList().get(mStepId).getDescription());

        String videoUrlString = MainActivity.recipeList.get(mRecipeId).getStepsList().get(mStepId).getVideoURL();
        String imageUrlString = MainActivity.recipeList.get(mRecipeId).getStepsList().get(mStepId).getThumbnailURL();

        // check if there is a video url if not remove the playerView
        if (videoUrlString == "" || videoUrlString == null){
            mPlayerView.setVisibility(View.GONE);

            //set up the exoplayer and play video
        }else{
            String filetype = videoUrlString.substring(videoUrlString.lastIndexOf(".") + 1);
            Log.i("fileType", "onCreateView: file type is: " + filetype);
            if (filetype.equalsIgnoreCase("mp4")){
                initializePlayer(Uri.parse(videoUrlString));
            }
        }

        return rootView;
    }

    public void setRecipeId(int mRecipeId) {
        this.mRecipeId = mRecipeId;
    }

    public void setStepId(int mStepId) {
        this.mStepId = mStepId;
    }
    public void setContext(Context context) {
       mContext = context;
    }

    private void initializePlayer(Uri mediaUri){
        if(mPlayer == null){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mPlayer);

            //mPlayer.addListener(mContext);

            String userAgent = Util.getUserAgent(mContext, "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri,
                    new DefaultDataSourceFactory(mContext, userAgent),
                    new DefaultExtractorsFactory(), null, null);
            mPlayer.prepare(mediaSource);
            mPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPlayer != null ) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }
}//class end
