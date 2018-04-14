package google.louco.com.bakingapp.JsonObj;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *  Список рецептов
 */
public class Recipes {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("ingredients")
    private List<Ingredient> ingredients = null;

    @SerializedName("steps")
    private List<Step> steps = null;

    @SerializedName("servings")
    private Integer servings;

    @SerializedName("image")
    private String image;

    public Recipes(Integer id, String name, List<Ingredient> ingredients, List<Step> steps, Integer servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public Integer getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
