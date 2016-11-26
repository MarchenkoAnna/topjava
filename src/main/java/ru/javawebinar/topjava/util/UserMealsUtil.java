package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    private static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

         Map<LocalDate, Integer> listCaloriesPerDay = mealList.stream()
                .collect(Collectors.toMap(date -> date.getDateTime().toLocalDate(), calories -> calories.getCalories(), (oldValue, newValue) -> oldValue+newValue));

//        Map<LocalDate, Integer> listCaloriesPerDay = new HashMap();
//        for (UserMeal meal : mealList){
//            LocalDate date = meal.getDateTime().toLocalDate();
//            if (listCaloriesPerDay.containsKey(date)){
//                listCaloriesPerDay.put(date, listCaloriesPerDay.get(date)+ meal.getCalories());
//            }
//            else {
//                listCaloriesPerDay.put(date, meal.getCalories());
//            }
//        }


        List<UserMealWithExceed> returnList = mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map(meal -> listCaloriesPerDay.get(meal.getDateTime().toLocalDate())> caloriesPerDay ?  new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), true) : new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), false))
                .collect(Collectors.toList());

//        List<UserMealWithExceed> returnList = new ArrayList();
//        for (UserMeal meal: mealList)
//        {
//           LocalTime time = meal.getDateTime().toLocalTime();
//           LocalDate date = meal.getDateTime().toLocalDate();
//
//           if (TimeUtil.isBetween(time, startTime, endTime)){
//               if (listCaloriesPerDay.get(date) > caloriesPerDay){
//                   returnList.add (new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), true));
//               }
//               else{
//                   returnList.add (new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), false));
//               }
//           }
//       }
        return returnList;
    }
}
