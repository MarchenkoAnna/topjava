package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {

    Meal get(int id, int userId);

    void delete (int id, int userId);

    List<Meal> getAll(int userId);

    void deleteAll(int userId);

    void update (Meal meal, int userId);

    Meal create (Meal meal, int userId);

    List<Meal> getBetween (LocalDate startDate, LocalDate endDate, int userId);


}
