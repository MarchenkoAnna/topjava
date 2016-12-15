package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Marchelaga on 10.12.2016.
 */
@WebServlet("/mealServlet")
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    private MealRepository repository;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        repository = new InMemoryMealRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action==null){
            LOG.info("getAll");
            request.setAttribute("exceededMeal", MealsUtil.getWithExceeded(repository.getAll(),MealsUtil.DEFAULT_CALORIES_PER_DAY) );
            //request.setAttribute("exceededMeal", MealsUtil.getWithExceeded(MealsUtil.MEAL_LIST,2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }else if (action.equals("delete")){
            int id = getId(request);
            LOG.info("Delete {}", id);
            repository.delete(id);
            response.sendRedirect("meals");
        }else if ("create".equals(action) || "update".equals(action)){
            final Meal meal = action.equals("create") ?
                    new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "",  1000) :
                    repository.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }

    }
    private int getId(HttpServletRequest request ){
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String description = request.getParameter("description");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id), dateTime, description, calories);

        LOG.info(meal.isNew()? "Create {}" : "Update {}", meal);
    repository.save(meal);
    response.sendRedirect("meals");
        //request.getRequestDispatcher("/meals").forward(request, response);

    }
}
