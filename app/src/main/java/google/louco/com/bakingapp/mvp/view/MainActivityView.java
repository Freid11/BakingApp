package google.louco.com.bakingapp.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import google.louco.com.bakingapp.JsonObj.Recipes;

public interface MainActivityView extends MvpView{
    void showListRecipes(List<Recipes> recipes);
}
