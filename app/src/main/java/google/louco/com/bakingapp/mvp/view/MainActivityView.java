package google.louco.com.bakingapp.mvp.view;

import android.app.Activity;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import google.louco.com.bakingapp.JsonObj.Recipes;

public interface MainActivityView extends MvpView{
    void showListRecipes(List<Recipes> recipes);
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void NewActivity(Activity activity, Recipes recipes);
}
