package qqai.design.builderPattern.builder;

import design.builderPattern.abs.extend.ChickenBurger;
import design.builderPattern.abs.extend.Coke;
import design.builderPattern.abs.extend.Pepsi;
import design.builderPattern.abs.extend.VegBurger;

/**
 * 描述：builder对象
 *
 * @author qqai
 * @createTime 2020-09-14 18:11
 */

public class MealBuilder {
    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
