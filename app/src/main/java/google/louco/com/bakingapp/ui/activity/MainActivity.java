package google.louco.com.bakingapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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

public class MainActivity extends MvpAppCompatActivity implements MainActivityView {

    @InjectPresenter
    MainPresenterActivity presenterActivity;

    @BindView(R.id.rv_list_recipes)
    RecyclerView recyclerView;

    private RecyclerView recyclerViewLand;

    @BindDimen(R.dimen.count_span)
    int COUNT_SPAN;

    private RVListRecipes rvListRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewLand = (RecyclerView) findViewById(R.id.rv_list_recipes_large_land);
        rvListRecipes = new RVListRecipes(new ClickRecipe());
        if (recyclerViewLand == null) {
            ButterKnife.bind(this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(rvListRecipes);
        }else{
            recyclerViewLand.setLayoutManager(new GridLayoutManager(this,2));
            recyclerViewLand.setAdapter(rvListRecipes);
        }
    }

    @Override
    public void showListRecipes(List<Recipes> recipes) {
        rvListRecipes.updateList(recipes);
    }

    @Override
    public void NewActivity(Activity activity, Recipes recipes) {
        Bundle bundle = new Bundle();
        bundle.putString(Recipes.KEY_SERIALIZABLE, recipes.ToJson());

        Intent intent = new Intent(this, activity.getClass());
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private class ClickRecipe implements RVListRecipes.OnClickRecipe {

        @Override
        public void onClick(Recipes recipes) {
            presenterActivity.ClickRecipe(recipes);
        }
    }
}
