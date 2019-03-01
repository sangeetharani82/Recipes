package org.launchcode.demo.Controllers;


import org.launchcode.demo.models.Category;
import org.launchcode.demo.models.Course;
import org.launchcode.demo.models.Ingredient;
import org.launchcode.demo.models.Recipe;
import org.launchcode.demo.models.data.CategoryDao;
import org.launchcode.demo.models.data.CourseDao;
import org.launchcode.demo.models.data.IngredientDao;
import org.launchcode.demo.models.data.RecipeDao;
import org.launchcode.demo.models.forms.AddIngredientsToRecipeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("recipe")
public class RecipeController {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    IngredientDao ingredientDao;

    // Request path: /recipe
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("recipes", recipeDao.findAll());
        model.addAttribute("title", "All recipes");
        return "recipe/index";
    }

    // add/create a recipe
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddRecipeForm(Model model) {
        model.addAttribute("title", "Add recipes");
        model.addAttribute(new Recipe());
        model.addAttribute("courses", courseDao.findAll());
        model.addAttribute("categories", categoryDao.findAll());
        return "recipe/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddRecipeForm(Model model, @ModelAttribute @Valid Recipe newRecipe,
                                       Errors errors, @RequestParam int courseId, @RequestParam int categoryId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create a recipe");
            model.addAttribute("courses", courseDao.findAll());
            model.addAttribute("categories", categoryDao.findAll());
            return "recipe/add";
        }

        Course cor = courseDao.findOne(courseId);
        Category cat = categoryDao.findOne(categoryId);
        newRecipe.setCourse(cor);
        newRecipe.setCategory(cat);
        recipeDao.save(newRecipe);

        model.addAttribute("message", "Recipe added successfully!");
        model.addAttribute("title", newRecipe.getRecipeName());
        //return "addIngredients/view";
        return "redirect:view/" + newRecipe.getId();
        // return "redirect:single/"+newRecipe.getId();
    }

    @RequestMapping(value="view/{id}", method = RequestMethod.GET)
    public String viewMenu(@PathVariable int id, Model model){
        Recipe recipe = recipeDao.findOne(id);
        model.addAttribute("title", recipe.getRecipeName());
        model.addAttribute("recipe", recipe);
        model.addAttribute("id", id);
        return "recipe/view";
    }

    @RequestMapping(value="add-ingredients/{recipeId}", method = RequestMethod.GET)
    public String displayAddIngredients(@PathVariable int recipeId, Model model){

        Recipe recipe = recipeDao.findOne(recipeId);
        AddIngredientsToRecipeForm form = new AddIngredientsToRecipeForm(recipe, ingredientDao.findAll());
        model.addAttribute("ingredients", ingredientDao.findAll());
        model.addAttribute("title", "Add Ingredients to " + recipe.getRecipeName());
        model.addAttribute("form", form);
        return "recipe/add-ingredients";
    }

    @RequestMapping(value="add-ingredients", method = RequestMethod.POST)
    public String processAddIngredients(@ModelAttribute @Valid AddIngredientsToRecipeForm form, Errors errors,
                          Model model){
        if (errors.hasErrors()){
            model.addAttribute("form", form);
            return "recipe/add-ingredients";
        }
        List<Ingredient> recipeIngredients = new ArrayList<>();
        Recipe theRecipe = recipeDao.findOne(form.getRecipeId());
        for (int id : form.getIngredientIds()){
            Ingredient theIngredient = ingredientDao.findOne(id);
            recipeIngredients.add(theIngredient);
        }
        theRecipe.addIngredients(recipeIngredients);
        recipeDao.save(theRecipe);
        return "redirect:view/" + theRecipe.getId();
    }

    //view single recipe
    @RequestMapping(value="single/{id}", method = RequestMethod.GET)
    public String singleRecipe(@PathVariable int id, Model model){
        Recipe recipe = recipeDao.findOne(id);
        model.addAttribute("title", recipe.getRecipeName());
        model.addAttribute("course", recipe.getCourse());
        model.addAttribute("category", recipe.getCategory());
        model.addAttribute("recipe", recipe);
        model.addAttribute("title", recipe.getRecipeName());
        model.addAttribute("ingredientLists", recipe.getIngredients());
        return "recipe/single";
    }

    // delete each recipe
    @RequestMapping(value = "delete/{recipeId}")
    public String delete(@PathVariable int recipeId, Model model){
        recipeDao.delete(recipeId);
        model.addAttribute("message", "Recipe deleted successfully!");
        return "recipe/message";
    }

    //Edit a recipe
    @RequestMapping(value="edit/{recipeId}", method = RequestMethod.GET)
    public String displayEditRecipeForm(Model model, @PathVariable int recipeId){
        model.addAttribute(recipeDao.findOne(recipeId));
        model.addAttribute("title", "Edit " + recipeDao.findOne(recipeId).getRecipeName());
        model.addAttribute("courses", courseDao.findAll());
        model.addAttribute("categories", categoryDao.findAll());
        return "recipe/edit";
    }

    @RequestMapping(value = "edit/{recipeId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int recipeId, @RequestParam String recipeName,
                                  @RequestParam int courseId, @RequestParam int categoryId,
                                  @RequestParam int servingSize, @RequestParam String prepTime,
                                  @RequestParam String cookTime, Model model,
                                  @RequestParam String direction){
        Recipe edited = recipeDao.findOne(recipeId);
        edited.setRecipeName(recipeName);
        edited.setServingSize(servingSize);
        edited.setPrepTime(prepTime);
        edited.setCookTime(cookTime);
        edited.setDirection(direction);


        Course cor = courseDao.findOne(courseId);
        edited.setCourse(cor);

        Category cat = categoryDao.findOne(categoryId);
        edited.setCategory(cat);

        recipeDao.save(edited);

        model.addAttribute("message", "Successfully edited!");

        //List<AddIngredientsDao> lists = edited.getAddIngredientsToRecipes();
        model.addAttribute("title", "Ingredient needed for " + edited.getRecipeName());
        //model.addAttribute("ingredientLists", lists);

        return "addIngredients/viewWithMsg";
    }

    //recipes in a course
    @RequestMapping(value = "course", method = RequestMethod.GET)
    public String course(Model model, @RequestParam int id){
        Course cor = courseDao.findOne(id);
        List<Recipe> recipes = cor.getRecipes();
        model.addAttribute("recipes", recipes);
        model.addAttribute("title", "Recipes in Course "+ cor.getCourseName());
        return "recipe/index";
    }
    //recipes in a category
    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id){
        Category cat = categoryDao.findOne(id);
        List<Recipe> recipes = cat.getRecipes();
        model.addAttribute("recipes", recipes);
        model.addAttribute("title", "Recipes in Category" + cat.getCategoryName());
        return "recipe/index";
    }
}
