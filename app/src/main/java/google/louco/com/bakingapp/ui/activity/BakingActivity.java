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
import google.louco.com.bakingapp.mvp.presenter.BakingPresenterActivity;
import google.louco.com.bakingapp.mvp.view.BakingActivityView;

public class BakingActivity extends MvpAppCompatActivity implements BakingActivityView{

    @InjectPresenter
    BakingPresenterActivity presenterActivity;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baking_activity);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            presenterActivity.startActivity(Recipes.FromJson(bundle.getString(Recipes.KEY_SERIALIZABLE)));
        }

        fragmentManager = getFragmentManager();
    }

    @Override
    public void showFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fl_baking, fragment)
                .commit();
    }
}
