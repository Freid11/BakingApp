package google.louco.com.bakingapp.di;

import javax.inject.Singleton;

import dagger.Component;
import google.louco.com.bakingapp.mvp.model.RetrofitDate;
import google.louco.com.bakingapp.mvp.model.RetrofitServer;

/**
 * Created by louco on 14.04.2018.
 */

@Component(modules = {RetrofitDate.class})
@Singleton
public interface RetrofitComponent {
    void inject(RetrofitServer retrofitServer);
}
