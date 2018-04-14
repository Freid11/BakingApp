package google.louco.com.bakingapp.JsonObj;

import com.google.gson.annotations.SerializedName;

/**
 * Ингридиенты
 */
class Ingredient {

    @SerializedName("quantity")
    private Integer quantity;

    @SerializedName("measure")
    private String measure;

    @SerializedName("ingredient")
    private String ingredient;

    public Ingredient(Integer quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
