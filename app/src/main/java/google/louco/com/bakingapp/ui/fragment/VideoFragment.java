package google.louco.com.bakingapp.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.bakingapp.R;

public class VideoFragment extends Fragment {

    @BindView(R.id.pl_video)
    PlayerView pvVideo;

    private String URLText = "";
    private SimpleExoPlayer player;
    private String KEY_SAVE_POSITION = "playbackPosition";
    private String KEY_SAVE_CURRENT = "currentWindow";
    private String KEY_SAVE_PLAY = "PLAY";
    private boolean isFull = false;
    private long playbackPosition = 0;
    private long currentWindow = 0;
    private boolean playWhenReady = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(container!=null) {
            inflater = LayoutInflater.from(container.getContext());
            View view = inflater.inflate(R.layout.video_fragment, container, false);
            ButterKnife.bind(this, view);
            return view;
        }
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState!=null){
            playbackPosition = savedInstanceState.getLong(KEY_SAVE_POSITION);
            currentWindow = savedInstanceState.getLong(KEY_SAVE_CURRENT);
            playWhenReady = savedInstanceState.getBoolean(KEY_SAVE_PLAY);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23 && !URLText.isEmpty()) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isFull) {
            hideSystemUi();
        }
        if ((Util.SDK_INT <= 23 || player == null) && !URLText.isEmpty()) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        pvVideo.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        pvVideo.setPlayer(player);
        MediaSource source = buildMediaSource(Uri.parse(URLText));
        player.prepare(source);
        player.seekTo(playbackPosition);
        player.setPlayWhenReady(playWhenReady);
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(KEY_SAVE_POSITION, playbackPosition);
        outState.putLong(KEY_SAVE_CURRENT, currentWindow);
        outState.putBoolean(KEY_SAVE_PLAY, playWhenReady);
        super.onSaveInstanceState(outState);
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public void setURL(String URL) {
        this.URLText = URL;
    }

    public long getPlaybackPosition() {
        return playbackPosition;
    }

    public void setPlaybackPosition(long playbackPosition) {
        this.playbackPosition = playbackPosition;
    }

    public long getCurrentWindow() {
        return currentWindow;
    }

    public void setCurrentWindow(long currentWindow) {
        this.currentWindow = currentWindow;
    }

    public boolean isPlayWhenReady() {
        return playWhenReady;
    }

    public void setPlayWhenReady(boolean playWhenReady) {
        this.playWhenReady = playWhenReady;
    }
}
