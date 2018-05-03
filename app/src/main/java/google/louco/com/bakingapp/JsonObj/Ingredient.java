package google.louco.com.bakingapp.JsonObj;

import com.google.gson.annotations.SerializedName;

/**
 * Ингридиенты
 */
class Ingredient {

    @SerializedName("quantity")
    private Float quantity;

    @SerializedName("measure")
    private String measure;

    @SerializedName("ingredient")
    private String ingredient;

    public Ingredient(Float quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return String.valueOf(quantity) + "/" + measure + " - " + ingredient;
    }
}
