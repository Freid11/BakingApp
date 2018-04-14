package google.louco.com.bakingapp.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.mvp.model.RetrofitServer;
import google.louco.com.bakingapp.mvp.view.MainActivityView;
import io.reactivex.observers.DisposableObserver;

@InjectViewState
public class MainPresenterActivity extends MvpPresenter<MainActivityView> {

    public MainPresenterActivity() {
        new RetrofitServer().getRecipes(new RequestRecipes());
    }

    class RequestRecipes extends DisposableObserver<List<Recipes>> {

        @Override
        public void onNext(List<Recipes> recipes) {
            getViewState().showListRecipes(recipes);
            for (Recipes re : recipes) {
                Log.d("Louco", "Image: "+ re.getImage());
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.e("Louco", "error" + e.getMessage());
        }

        @Override
        public void onComplete() {
        }
    }
}
