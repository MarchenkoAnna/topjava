package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final Meal Meal1 = new Meal(100000, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);

    public static final Meal Meal2 = new Meal(100001, LocalDateTime.of(2015, Month.MAY, 31, 14, 0), "Обед", 1500);

    public static final Meal Meal3 = new Meal(100002, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 550);

    public static final Meal Meal4 = new Meal(100003, LocalDateTime.of(2015, Month.MAY, 30, 14, 0), "Обед", 1600);

    public static final Meal Meal5 = new Meal(100004, LocalDateTime.of(2015, Month.MAY, 30, 21, 0), "Ужин", 850);

    public static final Meal Meal6 = new Meal(100005, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 550);

    public static final Meal Meal7 = new Meal(100006, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1100);

    public static final Meal Meal8 = new Meal(100007, LocalDateTime.of(2015, Month.MAY, 31, 21, 0), "Ужин", 400);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                    )
    );
}
