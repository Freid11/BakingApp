package google.louco.com.bakingapp.mvp.presenter;

import android.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.mvp.model.RetrofitServer;
import google.louco.com.bakingapp.mvp.view.BakingActivityView;
import google.louco.com.bakingapp.ui.fragment.StepFragment;
import io.reactivex.observers.DisposableObserver;

@InjectViewState
public class BakingPresenterActivity extends MvpPresenter<BakingActivityView>{

    public BakingPresenterActivity() {
    }

    public void startActivity(Recipes recipes){
        StepFragment stepFragment = new StepFragment();
        stepFragment.setRecipes(recipes);
        getViewState().showFragment(stepFragment);
    }

    public void startActivityPosition(int position){
        new RetrofitServer().getRecipes(new RequestRecipes(position));
    }

    class RequestRecipes extends DisposableObserver<List<Recipes>> {
        int position;

        public RequestRecipes(int position) {
            this.position = position;
        }

        @Override
        public void onNext(List<Recipes> recipes) {
            StepFragment stepFragment = new StepFragment();
            stepFragment.setRecipes(recipes.get(position));
            getViewState().firstShowFragment(stepFragment);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("Louco", "error" + e.getMessage());
        }

        @Override
        public void onComplete() {
        }
    }

    public void ActionActivity(Fragment fragment){
        getViewState().showFragment(fragment);
    }
}
