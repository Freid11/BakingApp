package google.louco.com.bakingapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.R;

public class StepFragment extends MvpFragment {

    public StepFragment() {
    }

    private Recipes recipes;

    @BindView(R.id.tb_baking)
    Toolbar toolbar;

    @BindView(R.id.ctb_baking)
    CollapsingToolbarLayout colToolbar;

    @BindView(R.id.tv_ingredient)
    TextView ingredients;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.recipes_fragment, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(recipes!=null){
            colToolbar.setTitle(recipes.getName());
            ingredients.setText(recipes.getIngredients());
        }

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    public void setRecipes(Recipes recipes) {
        this.recipes = recipes;
    }

}
