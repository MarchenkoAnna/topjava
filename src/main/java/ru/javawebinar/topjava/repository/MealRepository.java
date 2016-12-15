package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * Created by Marchelaga on 15.12.2016.
 */
public interface MealRepository {
    Meal save(Meal userMeal);

    void delete(int id);

    Meal get(int id);

    Collection<Meal> getAll();
}
