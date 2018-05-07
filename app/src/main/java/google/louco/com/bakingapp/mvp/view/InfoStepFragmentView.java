package google.louco.com.bakingapp.mvp.view;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import google.louco.com.bakingapp.JsonObj.Step;

public interface InfoStepFragmentView extends MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showStepInf(Step step);
    void showStep(String text);
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showBack(boolean show);
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showNext(boolean show);
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showTitle(String name);
}
