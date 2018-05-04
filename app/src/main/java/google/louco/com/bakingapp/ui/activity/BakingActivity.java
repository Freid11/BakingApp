package google.louco.com.bakingapp.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.R;
import google.louco.com.bakingapp.mvp.model.ActionActivity;
import google.louco.com.bakingapp.mvp.presenter.BakingPresenterActivity;
import google.louco.com.bakingapp.mvp.view.BakingActivityView;

public class BakingActivity extends MvpAppCompatActivity implements ActionActivity, BakingActivityView {

    @InjectPresenter
    BakingPresenterActivity presenterActivity;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baking_activity);
        ButterKnife.bind(this);

        if(savedInstanceState == null) {
            Intent intent = this.getIntent();
            Bundle bundleRecipes = intent.getExtras();
            if (bundleRecipes != null ) {
                presenterActivity.startActivity(Recipes.FromJson(bundleRecipes.getString(Recipes.KEY_SERIALIZABLE)));
            }
        }

        fragmentManager = getFragmentManager();
    }

    @Override
    public void showFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fl_baking, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAction(Fragment fragment) {
        presenterActivity.ActionActivity(fragment);
    }

}
