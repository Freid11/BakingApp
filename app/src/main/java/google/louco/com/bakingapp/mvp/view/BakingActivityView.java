package google.louco.com.bakingapp.mvp.view;

import android.app.Fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface BakingActivityView extends MvpView {
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showFragment(Fragment fragment);
}
