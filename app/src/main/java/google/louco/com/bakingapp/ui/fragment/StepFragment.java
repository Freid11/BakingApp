package google.louco.com.bakingapp.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.JsonObj.Step;
import google.louco.com.bakingapp.R;
import google.louco.com.bakingapp.adapter.RVStepList;
import google.louco.com.bakingapp.mvp.model.ActionActivity;
import google.louco.com.bakingapp.mvp.presenter.StepFragmentPresenter;
import google.louco.com.bakingapp.mvp.view.StepFragmentView;

public class StepFragment extends MvpFragment implements StepFragmentView {

    @InjectPresenter
    StepFragmentPresenter presenter;

    private Recipes recipes;
    private RVStepList rvStepList;
    private ActionActivity actionFragmentListener;
    private View fragment;
    private VideoFragment videoFragment;
    private DescriptionFragment descriptionFragment;

    @BindView(R.id.tb_baking)
    Toolbar toolbar;
    @BindView(R.id.rv_step)
    RecyclerView rvStep;
    @BindView(R.id.tv_ingredient)
    TextView ingredients;

    public StepFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.recipes_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        actionFragmentListener = (ActionActivity) getActivity();

        fragment = view.findViewById(R.id.v_orient);

        presenter.setOrientation(fragment == null);

        if(recipes!=null) {
            presenter.StartFragment(recipes, fragment == null);
        }

        rvStepList = new RVStepList(new OnClickStep());

        rvStep.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStep.setAdapter(rvStepList);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setRecipes(Recipes recipes) {
        this.recipes = recipes;
    }

    @Override
    public void ShowNameBaking(String name) {
        toolbar.setTitle(name);
    }

    @Override
    public void ShowIngredient(String ingredient) {
        ingredients.setText(ingredient);
    }

    @Override
    public void ShowStep(List<Step> steps) {
        rvStepList.UpdateList(steps);
    }

    @Override
    public void onClickStep(Step step) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (!step.getVideoURL().isEmpty()) {
            videoFragment = new VideoFragment();
            videoFragment.setURL(step.getVideoURL());

            fragmentTransaction.replace(R.id.fl_Video_recipes, videoFragment);
        } else {
            if (videoFragment != null) fragmentTransaction.remove(videoFragment);
        }

        descriptionFragment = new DescriptionFragment();
        descriptionFragment.setText(step.getDescription());
        descriptionFragment.setThumbnailURL(step.getThumbnailURL());
        fragmentTransaction.replace(R.id.fl_description_recipes, descriptionFragment);

        fragmentTransaction.commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        CleaFragment();
    }

    private void CleaFragment(){
        if(fragment!=null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(videoFragment!=null) fragmentTransaction.replace(R.id.fl_Video_recipes, videoFragment);
            if(descriptionFragment!=null) fragmentTransaction.replace(R.id.fl_description_recipes, descriptionFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                CleaFragment();
                getFragmentManager().popBackStack();
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void newFragmentInfo(Fragment fragment) {
        actionFragmentListener.onAction(fragment);
    }

    private class OnClickStep implements RVStepList.OnClickStepListener {

        @Override
        public void onClick(Step step) {
            presenter.setOrientation(fragment == null);
            presenter.ClickStep(step);
        }
    }
}
