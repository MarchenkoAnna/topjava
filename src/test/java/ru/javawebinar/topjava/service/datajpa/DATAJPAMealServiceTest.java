package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Marchelaga on 30.01.2017.
 */
@ActiveProfiles(DATAJPA)
public class DATAJPAMealServiceTest extends MealServiceTest {

    @Test
    public void testGetWithUser(){
        Meal meal  = service.getWithUser(MEAL1.getId(), USER_ID);
        MATCHER.assertEquals(MEAL1, meal);
        UserTestData.MATCHER.assertEquals(UserTestData.USER, meal.getUser());

    }
}
