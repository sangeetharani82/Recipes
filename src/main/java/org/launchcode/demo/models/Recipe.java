package org.launchcode.demo.models;


import org.launchcode.demo.models.forms.AddIngredientsToRecipeForm;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Pattern(regexp = "[^0-9]*")
    @Size(min = 3, max = 100)
    private String recipeName;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Category category;

    @NotNull
    private int servingSize;

    @NotNull
    @Size(min = 3, message = "Prep time needed")
    private String  prepTime;

    @NotNull
    @Size(min = 3, message = "Cook time needed")
    private String cookTime;

    @NotNull
    @Size(min=3, max = 3000000)
    private String direction;

    @ManyToMany
    private List<Ingredient> ingredients;

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private List<Quantity> quantities;

    public Recipe(String recipeName, int servingSize, String prepTime, String cookTime, String direction) {
        this();
        this.recipeName = recipeName;
        this.servingSize = servingSize;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.direction = direction;
    }

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredients(List<Ingredient> ingredientList){
        for (Ingredient ingredient : ingredientList){
            ingredients.add(ingredient);
        }
    }

    public List<Quantity> getQuantities() {
        return quantities;
    }
    public void addQuantities(Quantity quantity){
        quantities.add(quantity);
    }
}

