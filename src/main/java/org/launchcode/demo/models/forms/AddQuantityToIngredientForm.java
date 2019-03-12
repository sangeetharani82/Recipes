package org.launchcode.demo.models.forms;

import org.launchcode.demo.models.Ingredient;
import org.launchcode.demo.models.Recipe;

import javax.validation.constraints.NotNull;

public class AddQuantityToIngredientForm {

    private Recipe recipe;

    private Ingredient ingredient;

    @NotNull
    private String amount;

    @NotNull
    private int ingredientId;

    @NotNull
    private int recipeId;

    public AddQuantityToIngredientForm(Recipe recipe, Ingredient ingredient, String amount) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public AddQuantityToIngredientForm() {
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
