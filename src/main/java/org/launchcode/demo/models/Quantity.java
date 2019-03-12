package org.launchcode.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Quantity {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @ManyToOne
    private Recipe recipe;

    @NotNull
    @ManyToOne
    private Ingredient ingredient;

    @NotNull
    @Size(min = 1, message = "Specify the quantity")
    private String amount;

    public Quantity() {
    }

    public Quantity(Recipe recipe, Ingredient ingredient, String amount) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getId() {
        return id;
    }
}
