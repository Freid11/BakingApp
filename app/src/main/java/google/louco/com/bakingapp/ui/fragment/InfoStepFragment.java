package google.louco.com.bakingapp.ui.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.JsonObj.Step;
import google.louco.com.bakingapp.R;


public class InfoStepFragment extends MvpFragment {

    private Recipes recipes;
    private Step step;

    @BindView(R.id.tb_info_step)
    Toolbar toolbar;

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
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();

        toolbar.setTitle(recipes.getName());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DescriptionFragment fragment = new DescriptionFragment();
        fragment.setText(step.getDescription());

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fl_description, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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


}
