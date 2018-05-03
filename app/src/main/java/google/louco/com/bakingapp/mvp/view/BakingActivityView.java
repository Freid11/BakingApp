package google.louco.com.bakingapp.mvp.view;

import android.app.Fragment;

import com.arellomobile.mvp.MvpView;

public interface BakingActivityView extends MvpView {
    void showFragment(Fragment fragment);
}
