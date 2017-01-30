package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    private static final Sort SORT_DATA_TIME = new Sort(Sort.Direction.DESC, "dateTime");


    @Autowired
    private CrudMealRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Meal save(Meal meal, int userId) {
        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }
        User user = crudUserRepository.getOne(userId);
        meal.setUser(user);
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId)!= 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.findOne(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAll(userId, SORT_DATA_TIME);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.getBetween(startDate, endDate, userId);
    }
    @Override
    @Transactional
    public Meal getWithUser(Integer mealId, Integer userId){
        Meal meal = get(mealId, userId);
        meal.setUser(crudUserRepository.findOne(userId));
        return meal;
    }
}
