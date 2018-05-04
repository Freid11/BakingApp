package google.louco.com.bakingapp.mvp.presenter;

import android.app.Fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.mvp.view.BakingActivityView;
import google.louco.com.bakingapp.ui.fragment.StepFragment;

@InjectViewState
public class BakingPresenterActivity extends MvpPresenter<BakingActivityView>{

    public BakingPresenterActivity() {
    }

    public void startActivity(Recipes recipes){
        StepFragment stepFragment = new StepFragment();
        stepFragment.setRecipes(recipes);
        getViewState().showFragment(stepFragment);
    }

    public void ActionActivity(Fragment fragment){
        getViewState().showFragment(fragment);
    }
}
