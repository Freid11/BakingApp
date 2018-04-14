package google.louco.com.bakingapp.mvp.model;


import java.util.List;

import google.louco.com.bakingapp.JsonObj.Recipes;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiConnect {
    @GET()
    Observable<List<Recipes>> getRecipes();
}
