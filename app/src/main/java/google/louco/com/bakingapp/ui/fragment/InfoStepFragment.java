package google.louco.com.bakingapp.ui.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.JsonObj.Step;
import google.louco.com.bakingapp.R;
import google.louco.com.bakingapp.mvp.presenter.InfoStepPresenter;


public class InfoStepFragment extends MvpFragment implements google.louco.com.bakingapp.mvp.view.InfoStepFragmentView {

    private Recipes recipes;
    private Step step;
    private VideoFragment videoFragment;
    private View orientationView;

    @InjectPresenter
    InfoStepPresenter presenter;

    @BindView(R.id.tb_info_step)
    Toolbar toolbar;

    @BindView(R.id.bt_back)
    Button btBack;

    @BindView(R.id.bt_next)
    Button btNext;

    @BindView(R.id.tv_count_step)
    TextView CountStep;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.info_step_fragment, container, false);
        orientationView = view.findViewById(R.id.bt_back);
        if (orientationView != null) ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();

        if (savedInstanceState == null) {
            presenter.SaveParam(recipes, step, orientationView == null);

            if (orientationView != null) toolbar.setTitle(recipes.getName());
        }
        if (orientationView != null) {
            appCompatActivity.setSupportActionBar(toolbar);
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            btBack.setOnClickListener(v -> presenter.ClickBack());
            btNext.setOnClickListener(v -> presenter.ClickNext());
        }
    }

    private void fillFragment(Step step) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (!step.getVideoURL().isEmpty()) {
            videoFragment = new VideoFragment();
            videoFragment.setURL(step.getVideoURL());

            if (orientationView == null) videoFragment.setFull(true);

            fragmentTransaction.replace(R.id.fl_Video, videoFragment);
        } else {
            if (videoFragment != null) fragmentTransaction.remove(videoFragment);
        }

        DescriptionFragment descriptionFragment = new DescriptionFragment();
        descriptionFragment.setText(step.getDescription());
        fragmentTransaction.replace(R.id.fl_description, descriptionFragment);

        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFragmentManager().popBackStack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setRecipes(Recipes recipes) {
        this.recipes = recipes;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    @Override
    public void showStepInf(Step step) {
        fillFragment(step);
    }

    @Override
    public void showStep(String text) {
        if(orientationView!=null) CountStep.setText(text);
    }

    @Override
    public void showBack(boolean show) {
        if (show) btBack.setVisibility(View.VISIBLE);
        else btBack.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNext(boolean show) {
        if (show) btNext.setVisibility(View.VISIBLE);
        else btNext.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showTitle(String name) {
        toolbar.setTitle(name);
    }
}
