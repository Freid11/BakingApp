package google.louco.com.bakingapp.mvp.view;


import com.arellomobile.mvp.MvpView;

import google.louco.com.bakingapp.JsonObj.Step;

public interface InfoStepFragmentView extends MvpView {
    void showStepInf(Step step);
    void showStep(String text);
    void showBack(boolean show);
    void showNext(boolean show);
}
