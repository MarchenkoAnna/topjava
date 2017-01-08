package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    Meal save(Meal Meal, int userId);

    void delete(int id, int userId);

    Meal get(int id, int userId);

    List<Meal> getAll(int userId);

    void deleteAll(int userId);

    List<Meal> getBetween(LocalDate startTime, LocalDate endTime, int userId);

}
