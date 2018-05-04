package google.louco.com.bakingapp.mvp.view;

import android.app.Fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import google.louco.com.bakingapp.JsonObj.Step;

public interface StepFragmentView extends MvpView {
    void ShowNameBaking(String name);
    void ShowIngredient(String ingredient);
    void ShowStep(List<Step> steps);
    void onClickStep(Step step);
    @StateStrategyType(value = SkipStrategy.class)
    void newFragmentInfo(Fragment fragment);
}
