package org.launchcode.demo.models.forms;

import org.launchcode.demo.models.Ingredient;
import org.launchcode.demo.models.Recipe;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AddIngredientsToRecipeForm {

    private Recipe recipe;
    private Iterable<Ingredient> ingredients;

    @NotNull
    private int recipeId;

    @NotNull
    private List<Integer> ingredientIds;

    public AddIngredientsToRecipeForm(Recipe recipe, Iterable<Ingredient> ingredients) {
        this.recipe = recipe;
        this.ingredients = ingredients;
    }

    public AddIngredientsToRecipeForm() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Iterable<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public List<Integer> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(List<Integer> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }

}
