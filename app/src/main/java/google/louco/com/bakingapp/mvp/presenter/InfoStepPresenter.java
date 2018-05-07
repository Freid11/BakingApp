package google.louco.com.bakingapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Objects;

import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.JsonObj.Step;
import google.louco.com.bakingapp.mvp.view.InfoStepFragmentView;


@InjectViewState
public class InfoStepPresenter extends MvpPresenter<InfoStepFragmentView> {

    private Recipes recipes;
    private Step step;
    private int Position;

    public InfoStepPresenter() {

    }

    public void SaveParam(Recipes recipes, Step step) {
        this.recipes = recipes;
        this.step = step;
        Position = SearchPositionById();

        onShowButtonListener();
        getViewState().showStepInf(step);
        getViewState().showStep(StepCount());
    }

    public void ClickBack() {
        Position--;
        step = recipes.getSteps().get(Position);
        onShowButtonListener();
        getViewState().showStepInf(step);
        getViewState().showStep(StepCount());
    }

    public void ClickNext() {
        Position++;
        step = recipes.getSteps().get(Position);
        onShowButtonListener();
        getViewState().showStepInf(step);
        getViewState().showStep(StepCount());
    }

    private void onShowButtonListener() {
        if (recipes.getSteps().size() - 1 == Position) getViewState().showNext(false);
        else getViewState().showNext(true);

        if (Position == 0) getViewState().showBack(false);
        else getViewState().showBack(true);
    }

    private int SearchPositionById() {
        for (int x = 0; x < recipes.getSteps().size(); x++) {
            if (recipes.getSteps().get(x).equals(step)) {
                return x;
            }
        }
        return 0;
    }

    private String StepCount() {
        return "Page " + String.valueOf(Position + 1) + " / " + recipes.getSteps().size();
    }
}