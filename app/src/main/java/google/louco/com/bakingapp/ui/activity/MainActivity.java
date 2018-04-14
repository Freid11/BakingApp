package google.louco.com.bakingapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.R;
import google.louco.com.bakingapp.adapter.RVListRecipes;
import google.louco.com.bakingapp.mvp.presenter.MainPresenterActivity;
import google.louco.com.bakingapp.mvp.view.MainActivityView;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView{

    @InjectPresenter
    MainPresenterActivity presenterActivity;

    @BindView(R.id.rv_list_recipes)
    RecyclerView recyclerView;

    @BindDimen(R.dimen.count_span)
    int COUNT_SPAN;

    private RVListRecipes rvListRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rvListRecipes = new RVListRecipes(new ClickRecipe());
        recyclerView.setLayoutManager(new GridLayoutManager(this, COUNT_SPAN));
        recyclerView.setAdapter(rvListRecipes);
    }

    @Override
    public void showListRecipes(List<Recipes> recipes) {
        rvListRecipes.updateList(recipes);
    }

    private class ClickRecipe implements RVListRecipes.OnClickRecipe{

        @Override
        public void onClick(Recipes recipes) {
            Toast.makeText(getBaseContext(), recipes.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
