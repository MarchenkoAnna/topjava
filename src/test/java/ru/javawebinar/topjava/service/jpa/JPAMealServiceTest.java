package ru.javawebinar.topjava.service.jpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.Profiles.JPA;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Marchelaga on 30.01.2017.
 */
@ActiveProfiles(JPA)
public class JPAMealServiceTest extends MealServiceTest {

}
