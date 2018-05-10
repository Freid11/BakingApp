package google.louco.com.bakingapp.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.ButterKnife;
import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.R;
import google.louco.com.bakingapp.components.ServiceWidgetList;
import google.louco.com.bakingapp.components.WidgetService;
import google.louco.com.bakingapp.mvp.model.ActionActivity;
import google.louco.com.bakingapp.mvp.presenter.BakingPresenterActivity;
import google.louco.com.bakingapp.mvp.view.BakingActivityView;
import google.louco.com.bakingapp.ui.widget.BakingWidget;

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
            long mPlantId = getIntent().getLongExtra(BakingWidget.ITEM_POSITION, -1);
            if(mPlantId != -1){
                presenterActivity.startActivityPosition((int) mPlantId);
            }else {
                Bundle bundleRecipes = intent.getExtras();
                if (bundleRecipes != null) {
                    String text = bundleRecipes.getString(Recipes.KEY_SERIALIZABLE);
                    if (text != null) {
                        presenterActivity.startActivity(Recipes.FromJson(text));
                    } else {

                    }
                }
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
    public void firstShowFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
        .replace(R.id.fl_baking, fragment)
                .commit();
    }

    @Override
    public void onAction(Fragment fragment) {
        presenterActivity.ActionActivity(fragment);
    }

}
