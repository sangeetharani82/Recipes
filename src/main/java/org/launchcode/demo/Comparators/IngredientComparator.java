package org.launchcode.demo.Comparators;

import org.launchcode.demo.models.Ingredient;

import java.util.Comparator;

public class IngredientComparator implements Comparator<Ingredient> {
    @Override
    public int compare(Ingredient o1, Ingredient o2){
        return o1.getIngredientName().compareToIgnoreCase(o2.getIngredientName());
    }
}
