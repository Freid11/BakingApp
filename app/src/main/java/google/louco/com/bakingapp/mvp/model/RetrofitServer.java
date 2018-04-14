package google.louco.com.bakingapp.mvp.model;

import android.os.Build;

import java.util.List;

import javax.inject.Inject;

import google.louco.com.bakingapp.JsonObj.Recipes;
import google.louco.com.bakingapp.di.RetrofitAplication;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class RetrofitServer {

    @Inject
    Retrofit retrofit;

    public RetrofitServer() {
        RetrofitAplication.getComponent().inject(this);
    }

    public void getRecipes(DisposableObserver<List<Recipes>> disposableObserver){
        retrofit.create(ApiConnect.class)
                .getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }

}
