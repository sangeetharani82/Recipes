package org.launchcode.demo.models;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=100)
    private String ingredientName;

    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;

    public Ingredient(){}

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getId() {
        return id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

}
