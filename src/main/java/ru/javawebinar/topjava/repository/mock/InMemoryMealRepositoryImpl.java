package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;
import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.USER_ID;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map <Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, USER_ID));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 650), ADMIN_ID);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Integer mealId = meal.getId();
        if (meal.isNew()) {
            mealId = counter.incrementAndGet();
            meal.setId(mealId);
        }
        else if (get(mealId, userId) == null){
            return null;
        }
        Map <Integer, Meal> userMeals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        userMeals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id, int userId) {
        Map <Integer, Meal> userMeals = repository.get(userId);
        userMeals.remove(id);
    }

    @Override
    public Meal get(int id, int userId) {
        Map <Integer, Meal> userMeals = repository.get(userId);
        return userMeals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        Map <Integer, Meal> userMeals = repository.get(userId);
        Collection <Meal> meals = userMeals.values();
        List <Meal> mealList = new ArrayList<>(meals);
        mealList.sort((Meal m1, Meal m2) -> (m2.getDateTime().compareTo(m1.getDateTime())));
        return mealList;
    }

    @Override
    public void deleteAll(int userId) {
        repository.put(userId,new ConcurrentHashMap<>());
    }


    @Override
    public List<Meal> getBetween(LocalDate startTime, LocalDate endTime, int userId) {
        Map <Integer, Meal> userMeals = repository.get(userId);
        Collection <Meal> meals = userMeals.values();
        return meals.stream().filter(m  -> MealsUtil.isBetweenDate(m.getDate(), startTime, endTime)).collect(Collectors.toList());

    }
}

