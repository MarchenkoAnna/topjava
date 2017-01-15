package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.*;

import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Marchelaga on 15.01.2017.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})

@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {
    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Обед", 1300);
        Meal created = service.save(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(Meal2, Meal1, newMeal), service.getAll(ADMIN_ID));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(100000, ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(Meal2), service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(100009, ADMIN_ID);
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(100003, USER_ID);
        MATCHER.assertEquals(Meal4, meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(100009, ADMIN_ID);
    }

    @Test
    public void testGetBetween() throws Exception {
        Collection<Meal> list = service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 30), ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(Meal1), list);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Meal> all = service.getAll(ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(Meal2, Meal1), all);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(Meal1);
        updated.setDateTime(LocalDateTime.of(2015, Month.MAY, 30, 11, 0));
        updated.setCalories(330);
        service.update(updated, ADMIN_ID);
        MATCHER.assertEquals(updated, service.get(updated.getId(), ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testGetForeignMeal() throws Exception {
        service.get(100000, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteForeignMeal() throws Exception {
        service.delete(100000, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateForeignMeal() throws Exception {
        Meal meal = service.get(100000, ADMIN_ID);
        meal.setDateTime(LocalDateTime.of(2015, Month.MAY, 30, 11, 0));
        meal.setCalories(330);
        service.update(meal, USER_ID);
    }
}