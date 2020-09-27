package qqai.design.builderPattern;

import qqai.design.builderPattern.builder.Meal;
import qqai.design.builderPattern.builder.MealBuilder;

/**
 * 描述：构建者模式
 *
 * @author qqai
 * @createTime 2020-09-14 17:39
 */

public class Main {

    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("Non-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }

}
