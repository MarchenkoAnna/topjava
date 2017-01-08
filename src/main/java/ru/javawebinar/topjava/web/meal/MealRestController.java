package ru.javawebinar.topjava.web.meal;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import org.slf4j.Logger;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    @Autowired
    private MealService service;
    private static final Logger LOG = LoggerFactory.getLogger(MealRestController.class);

    public Meal get(int id){
        int userId = AuthorizedUser.id();
        LOG.info("get meal {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id){
        int userId = AuthorizedUser.id();
        LOG.info("delete meal {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<Meal> getAll(){
        int userId = AuthorizedUser.id();
        LOG.info("get all for User {}",  userId);
        return service.getAll(userId);
    }

    public void deleteAll(){
        int userId = AuthorizedUser.id();
        LOG.info("delete all for User {}", userId);
        service.deleteAll(userId);
    }

    public void update(Meal meal, int userId){
        LOG.info("update meal {} for User {}", meal, userId);
        service.update(meal, userId);
    }

    public Meal create(Meal meal){
        int userId = AuthorizedUser.id();
        LOG.info("create meal {} for User {}", meal, userId);
        return service.create(meal, userId);
    }

    public List<MealWithExceed> getBetween (LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        int userId = AuthorizedUser.id();
        LOG.info("get meal between dates{} and {}  for time between {} and {} for User {}", startDate, endDate, startTime, endTime, userId);
        return MealsUtil.getFilteredWithExceeded(service.getBetween (startDate, endDate,  userId), startTime, endTime, AuthorizedUser.getCaloriesPerDay()) ;
    }


}
