package google.louco.com.bakingapp.mvp.model;


import java.util.List;

import google.louco.com.bakingapp.JsonObj.Recipes;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiConnect {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipes>> getRecipes();
}
