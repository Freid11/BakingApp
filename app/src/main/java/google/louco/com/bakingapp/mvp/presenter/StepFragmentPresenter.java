package google.louco.com.bakingapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.JsonObj.Step;
import google.louco.com.bakingapp.mvp.view.StepFragmentView;
import google.louco.com.bakingapp.ui.fragment.InfoStepFragment;

@InjectViewState
public class StepFragmentPresenter extends MvpPresenter<StepFragmentView>{

    private Recipes recipes;

    public StepFragmentPresenter() {
        if(recipes != null){
            StartFragment(recipes);
        }
    }

    public void StartFragment(Recipes recipes){
        this.recipes = recipes;
        getViewState().ShowNameBaking(recipes.getName());
        getViewState().ShowIngredient(recipes.getIngredients());
        getViewState().ShowStep(recipes.getSteps());
    }

    public void ClickStep(Step step){
        InfoStepFragment infoStepFragment = new InfoStepFragment();
        infoStepFragment.setStep(step);
        infoStepFragment.setRecipes(recipes);
        getViewState().newFragmentInfo(infoStepFragment);
    }
}
